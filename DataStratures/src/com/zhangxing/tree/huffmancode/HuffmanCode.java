package com.zhangxing.tree.huffmancode;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 * 思路：
 * 先创建对应的赫夫曼树
 * 生成赫夫曼编码和赫夫曼编码后的数据
 *
 * 数据解压也需要根据赫夫曼编码进行解码
 */
public class HuffmanCode {
    public static void main(String[] args) throws Exception {
        strZip();
    }

    public static void zipFigure() throws Exception {
        String srcFile = "D://Study//jdk//1.txt";
        String dstFile = "D://Study//jdk//1.zip";
        zipFile(srcFile, dstFile);
        System.out.println("OK~");
    }

    public static void unZipFigure(){
        String zipFile = "D://Study//jdk//1.zip";
        String dstFile = "D://Study//jdk//2.txt";
        unZipFile(zipFile, dstFile);
        System.out.println("解压OK~");
    }

    public static void unZipFile(String zipFile, String dstFile){

        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String> ) ois.readObject();
            //解码。原始bytes
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes数组写入目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void zipFile(String srcFile, String dstFile) throws Exception {
        //创建文件输入流
        FileInputStream is = new FileInputStream(srcFile);
        //创建一个和源文件大小一样的byte[]数组
        byte[] bytes = new byte[is.available()];
        //读取文件
        is.read(bytes);
        //直接对源文件进行压缩
        byte[] huffmanZipBytes = huffmanZip(bytes);
        //创建文件的输出流，存放压缩文件
        FileOutputStream os = new FileOutputStream(dstFile);
        //创建一个和文件输出流有关的ObjectOutPutStream
        ObjectOutputStream oos = new ObjectOutputStream(os);
        //这里以对象流的方式写入赫夫曼编码，是为了我们恢复源文件时使用
        //把赫夫曼编码后的字节数组写入压缩文件
        oos.writeObject(huffmanZipBytes);
        //注意，一定要把赫夫曼编码写入压缩文件
        oos.writeObject(huffmanCodes);
        //Close...
        oos.close();
        os.close();
        is.close();

    }

    private static void strZip() {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        byte[] huffmanZipBytes = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanZipBytes));
        byte[] source = decode(huffmanCodes, huffmanZipBytes);
        System.out.println("source：" + new String(source));
    }

    /**
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanByte对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte[]转为二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是否最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        System.out.println(Arrays.toString(huffmanBytes));
        System.out.println("解码：" + stringBuilder);
        //把字符串按照指定的赫夫曼编码进行解码
        //将赫夫曼编码表进行调换，因为需要反向查询
        HashMap<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            //开始匹配
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //此时list中就存放所有一开始压缩的字符
        //把list中的数据放到byte[]中
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;

    }

    /**
     * 将一个byte转为一个二进制的字符串
     *
     * @param b
     * @param flag
     * @return 是该b对应的二进制的字符串。
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;
        //b的最后一个字节无需补高位
        if (flag) {
            temp |= 256;
        }
        String string = Integer.toBinaryString(temp);
        if (flag) {
            return string.substring(string.length() - 8);
        } else {
            return string;
        }
    }

    /**
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<CodeNode> list = getCodeNodes(bytes);
        //创建赫夫曼树
        CodeNode treeRoot = createTree(list);
        //生成对应的赫夫曼编码、
        Map<Byte, String> huffmanCodes = getCodes(treeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] zip = zip(bytes, huffmanCodes);
        return zip;
    }

    /**
     * @param bytes 接收字节数组
     * @return 返回包含ASCII数值和出现次数的codeNode集合
     */
    private static List<CodeNode> getCodeNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<CodeNode> codeNodes = new ArrayList<CodeNode>();
        //遍历bytes，存储每个byte出现的次数-->map
        HashMap<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        //把每个键值对转成CodeNode对象，并加入codeNodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            codeNodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        return codeNodes;
    }

    private static CodeNode createTree(List<CodeNode> codeNodes) {
        while (codeNodes.size() > 1) {
            //排序
            Collections.sort(codeNodes);
            //取出前2个
            CodeNode leftLode = codeNodes.get(0);
            CodeNode rightLode = codeNodes.get(1);
            //创建一个新的二叉树，它的根结点没有data。字符只放在叶子结点
            CodeNode parentNode = new CodeNode(null, leftLode.weight + rightLode.weight);
            parentNode.left = leftLode;
            parentNode.right = rightLode;
            //删除list中的刚刚2个结点。并加入新的二叉树结点
            codeNodes.remove(leftLode);
            codeNodes.remove(rightLode);
            codeNodes.add(parentNode);
        }
        return codeNodes.get(0);
    }

    private static void preOrder(CodeNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println();
        }
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 存放在Map<Byte,String>形式
     * 在遍历过程中，需要拼接路径。StringBuilder存储某个叶子结点的路径
     */
    static StringBuilder stringBuilder = new StringBuilder();
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();

    private static Map<Byte, String> getCodes(CodeNode codeNode) {
        getCodes(codeNode.left, "0", stringBuilder);
        getCodes(codeNode.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的codeNode结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param codeNode      传入结点
     * @param code          路径
     * @param stringBuilder 拼接路径
     */
    private static void getCodes(CodeNode codeNode, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (codeNode != null) {
            //判断当前node是叶子结点还是非叶子结点。
            if (codeNode.data == null) {
                //左递归
                getCodes(codeNode.left, "0", stringBuilder2);
                //左递归
                getCodes(codeNode.right, "1", stringBuilder2);
            } else {
                //进入此判断则是叶子结点
                huffmanCodes.put(codeNode.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 编写一个方法，将字符串对应的byte[]数组，通过生成的赫夫曼编码表返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        这是原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例:"i like like like java do you like a java"对应的byte[]数组
     * 返回的是应该转化的字符串所对应的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用赫夫曼编码表，将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("编码：" + stringBuilder);
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffmanCodeByte = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                //最后不够
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeByte[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeByte;
    }


}


class CodeNode implements Comparable<CodeNode> {
    @Override
    public int compareTo(@NotNull CodeNode o) {
        return this.weight - o.weight;
    }

    Byte data;
    int weight;
    CodeNode left;
    CodeNode right;

    public CodeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


}
