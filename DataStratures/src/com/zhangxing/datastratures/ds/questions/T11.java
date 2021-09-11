package com.zhangxing.datastratures.ds.questions;

import java.util.*;

/**
 * @Author zhangxing
 * @Date 2021/8/31 9:22
 * @Version 1.0
 * @Description
 */
public class T11 {
    public T11() {
        super();
    }

    public T11(int a) {
        this();
    }


//    public static void main(String[] args) {
//        ListNode node = new ListNode(1);
//        node.next = null;
//        hasCycle(node);
//        canConstruct("aa", "aab");
//        try {
//            int loveleetcode = firstUniqChar("leetcode");
//            System.out.println(loveleetcode);
//            HashMap map = new HashMap(16);
//            map.put("c", 1);
//            map.put("c", 2);
//            System.out.println(map);
//            int a = 10;
//            int b = a;
//            b = 9;
//            System.out.println(a);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        setZeroes(matrix);
//        HashMap map = new HashMap();
//        map.put(1, 2);
//        map.put(1, 3);
//        System.out.println(map);
//        generate(4);
//        int i = stringToInteger("-2147483648");
////        System.out.println(i);
//        int[][] nums1 = {{1, 2, 3, 4}};
//        int[] nums2 = {-3, -1, 0, 0, 0, 3, 3};
//        matrixReshape(nums1, 2, 2);
//        List list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(0, 4);
//        list.remove(2);
//        removeDuplicates(nums2);
//
////        int[] intersect = intersect(nums1, nums2);
////        Arrays.stream(intersect).forEach(System.out::println);
//    }

    public static int stringToInteger(String target) {

//        if ("-".equals(target.substring(0,1))){
//            target = target.substring(1,target.length());
//            int i = Integer.parseInt(target);
//            return -i;
//        }
//        Integer res = Integer.parseInt(target);
        return Integer.parseInt(target);
        // write your code here
//        char[] chars = target.toCharArray();
//        int temp = 1;
//        int res = 0;
//        for (int i = target.length() - 1; i > 0; i--) {
//            Integer val = Integer.valueOf(Character.toString(chars[i]));
//        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        // 先排序，无论给定是否有序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 双指针法
        // 找到一样的元素，就将其加入返回结果。否则就后退，直到一个下标越界。
        int length1 = nums1.length, length2 = nums2.length;
        int[] temp = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                temp[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }
        // 最后注意返回值，直接返回可能会有0元素在数组中
        // 返回，一个新的数组对象
        return Arrays.copyOfRange(temp, 0, index);
    }

    public int maxProfit(int[] prices) {
        // 暴力法
//        int max = 0;
//        for (int i = 0; i < prices.length - 1; i++) {
//            for (int j = i + 1; j < prices.length; j++) {
//                if ((prices[j] - prices[i]) > max) {
//                    max = prices[j] - prices[i];
//                }
//            }
//        }
//        if (max <= 0) {
//            return 0;
//        } else {
//            return max;
//        }
        // 只要今天不是最低点，我们就尝试卖！是最低点，尝试买入。
        int minPrice = Integer.MAX_VALUE;
        int maxProfile = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (maxProfile < prices[i] - minPrice) {
                maxProfile = prices[i] - minPrice;
            }
        }
        // 包含了不交易的情况，因为一直进入不到那个else的循环中
        return maxProfile;
    }

    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int length1 = mat.length;
        int length2 = mat[0].length;
        // 暴力解法
//        BlockingQueue<Integer> temp = new ArrayBlockingQueue<Integer>(length1*length2);
//        for (int i = 0; i < length1; i++) {
//            for (int j = 0; j < length2; j++) {
//                temp.add(mat[i][j]);
//            }
//        }
//        if (temp.size() != r * c) {
//            return mat;
//        }
//        int[][] res = new int[r][c];
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                res[i][j] = (int) temp.poll();
//            }
//        }
//        return res;
        if (length1 * length2 != r * c) {
            return mat;
        }
        int[][] temp = new int[r][c];
        for (int x = 0; x < length1 * length2; x++) {
            // 关键！都是都列数进行操作的
            // 每列3个数：7/3=2,7%3=1,a[2][1]确实是
            temp[x / c][x % c] = mat[x / length2][x % length2];
        }
        return temp;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        // 第一个单独列出来
        temp.add(1);
        res.add(temp);
        for (int i = 1; i < numRows; i++) {
            // 从第2行开始有规律，第一个和最后一个为1
            // 中间的每个都是前一层同列+前一层前一列
            List<Integer> temp2 = new LinkedList<>();
            temp2.add(1);
            for (int j = 1; j < i; j++) {
                temp2.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            temp2.add(1);
            res.add(temp2);
        }
        return res;
    }

    public static int removeDuplicates(int[] nums) {
        // HashMap默认是无序的，可以采用LinkedHashMap
//        HashMap<Integer, Integer> map = new LinkedHashMap<>(16);
//        int length = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[i], i);
//                length++;
//            }
//        }
//        int index = 0;
//        // 对map的Key进行遍历
//        for (Integer value : map.keySet()) {
//            nums[index++] = value;
//        }
//        return length;
        // 常规双指针法
        int i = 1, j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }

    public boolean isValidSudoku(char[][] board) {
        // init
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>(9);
            columns[i] = new HashMap<>(9);
            boxes[i] = new HashMap<>(9);
        }

        // 遍历
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char cur = board[i][j];
                // char比较用''
                if (cur != '.') {
                    // 将当前数字先放置进来，然后比较。
                    int num = (int) cur;
                    int box_num = (i / 3) * 3 + (j / 3);
                    // 将num加入当前的map中，值为：
                    // 获取当前map中的当前值，如果有value就加一，如果没有就默认为零再加一
                    rows[i].put(num, rows[i].getOrDefault(num, 0) + 1);
                    columns[j].put(num, columns[j].getOrDefault(num, 0) + 1);
                    boxes[box_num].put(num, boxes[box_num].getOrDefault(num, 0) + 1);

                    // 判断
                    if (rows[i].get(num) > 1 || columns[j].get(num) > 1 || boxes[box_num].get(num) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void setZeroes(int[][] matrix) {
        // 如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置为 true
        int m = matrix.length, n = matrix[0].length;
        // 这里是使用这种方式存储代表某行某列应该置零
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // 很关键
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 这样进行判定置零！
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static int firstUniqChar(String s) {
        // 最关键的一点，在第二次循环时，检测的key应该是s串的从头到尾，检测这个串的值是不是1
        // 相当于，第一次循环，我们检查出了每个字符的个数
        // 第二次，依然从头开始循环，因为已经知道个数，只要检测到一个为1就返回
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        // 建立字母数组，26个，每出现一个字符，对应的下标++，也就是存放字母出现的次数
        // 这样就不用考虑下标，没有对应的字符就为零
        // 很极限这波
        int[] RN = new int[26];
        int[] MG = new int[26];
        char[] charsRN = ransomNote.toCharArray();
        char[] charsMG = magazine.toCharArray();
        // 加强型for循环
        for (char c : charsRN) {
            RN[c - 'a']++;
        }
        for (char c : charsMG) {
            MG[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (RN[i] > MG[i]) {
                return false;
            }
        }
        return true;
        // 还可以使用一个中间变量数组存放字母，只要这个变量对应的字符不够救赎信减去的，就返回false

    }

    public boolean isAnagram(String s, String t) {
        int[] numS = new int[26];
        int[] numT = new int[26];
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        for (char c : charsS) {
            numS[c - 'a']++;
        }
        for (char c : charsT) {
            numT[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (numS[i] != numT[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasCycle(ListNode head) {
//        HashMap<ListNode, Integer> map = new HashMap(16);
//        while (head != null) {
//            // 循环遍历，如果形成环就退出，或者指向为空退出
//            // 但是重点是，存放的是这个节点，而不是仅仅判定值。
//            if (map.containsKey(head)) {
//                return true;
//            } else {
//                // 将当前值加入HashMap，并指向下一个
//                map.put(head, 1);
//                head = head.next;
//            }
//        }
//        return false;
        // 解法2：快慢指针
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        // 直接将虚拟头节点复制，最后返回head.next正好是有数据的开始节点
        // 此外还因为last需要向后遍历，需要保存下来头节点的地址。
        ListNode last = head;
        // 这里应该是 && ，只要有一方为空了，就退出。
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                // 加入l2
                last.next = l2;
                l2 = l2.next;
            } else {
                last.next = l1;
                l1 = l1.next;
            }
            last = last.next;
        }
        // 有一方结束
        if (l1 != null) {
            last.next = l1;
        }
        if (l2 != null) {
            last.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args) {

    }

}
