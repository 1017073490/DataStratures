package com.zhangxing.datastratures.ds.bgw;

import java.io.*;

/**
 * @Author zhangxing
 * @Date 2021/8/24 20:39
 * @Version 1.0
 * @Description
 */
@SuppressWarnings("all")
public class NewInstanceFour implements Cloneable,Serializable{
    // 需要实现Serializable，否贼会抛出NotSerializableException
    // 将对象进行流化存储，需要流化存储时需要实现Serializable
    // 值得注意的是 ：如果需要使用clone方法，必需实现java.lang.Cloneable接口，
    // 否则会抛出java.lang.CloneNotSupportedException。
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (NewInstanceFour) super.clone();
    }

    public static void main(String[] args) throws
            CloneNotSupportedException,
            IllegalAccessException,
            InstantiationException,
            IOException,
            ClassNotFoundException {
        NewInstanceFour one = new NewInstanceFour();
        NewInstanceFour two = (NewInstanceFour) one.clone();
        NewInstanceFour three = NewInstanceFour.class.newInstance();
        //序列化过程
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("two.ser"));
        oos.writeObject(two);
        oos.close();
        //反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("two.ser"));
        NewInstanceFour four = (NewInstanceFour)ois.readObject();
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println("============================");
        System.out.println(four.equals(two));
        System.out.println(one.equals(two));




    }
}
