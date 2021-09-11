package com.zhangxing.datastratures.ds.questions;

import java.util.*;

/**
 * @Author zhangxing
 * @Date 2021/9/7 8:13
 * @Version 1.0
 * @Description
 */
public class T14 {
    private static HashMap<Integer, Integer> treeMap = new HashMap<>(16);

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 总体思路：根据先序遍历的根节点，定位到中序遍历的根节点
        // 中序遍历的左端点的下标与根节点的下标之差就是左子树的数量，反之是右子树
        // 每轮递归中，我们都：
        // 1 找到根节点
        // 2 按照数量左子树继续递归，作为当前根节点的left
        // 3 右子树继续递归，作为当前根节点的right


        // 记录中序遍历的k-v映射
        for (int i = 0; i < inorder.length; i++) {
            treeMap.put(inorder[i], i);
        }
        TreeNode treeNode = myBuildTree(preorder, inorder,
                0, preorder.length - 1, 0, inorder.length - 1);
        return treeNode;
    }

    /**
     * @param preorder      先序遍历
     * @param inorder       中序遍历
     * @param preOrderLeft  递归中：先序遍历的左端点
     * @param preOrderRight 递归中：先序遍历的右端点
     * @param inOrderLeft   递归中：中序遍历的左端点
     * @param inOrderRight  递归中：中序遍历的右端点
     */
    private TreeNode myBuildTree(int[] preorder, int[] inorder,
                                 int preOrderLeft, int preOrderRight, int inOrderLeft, int inOrderRight) {
        // 定义递归结束条件
        if (preOrderLeft > preOrderRight) {
            return null;
        }

        // 递归执行流程
        // 1 找到先序遍历中的根节点，即preOrderLeft
        int preOrderRoot = preOrderLeft;
        // 2 据此定位到中序遍历中的根节点
        int inOrderRoot = treeMap.get(preorder[preOrderRoot]);
        // 3 建立根节点，左递归指向其left，右递归指向其right
        TreeNode root = new TreeNode(preorder[preOrderRoot]);
        // 建立递归的重要参数是他们的参数变量值为多少
        // 根据中序遍历计算左子树个数
        int leftSize = inOrderRoot - inOrderLeft;
        // 递归构造左子树
        // 官方提示：先序遍历中「从 左边界+1 开始的 leftSize」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder,
                preOrderLeft + 1, preOrderLeft + leftSize, inOrderLeft, inOrderRoot - 1);
        // 递归构造右子树
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder,
                preOrderLeft + leftSize + 1, preOrderRight, inOrderRoot + 1, inOrderRight);

        // 最后返回root
        return root;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return null;
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        TreeNode curNode = root;
        while (curNode != null) {
            if (curNode.val > val) {
                // 这里的思路没问题：
                // 当确定了左右子树之后，
                // 我们要直接判断当前节点的左右子树是否为 null
                if (curNode.left == null) {
                    curNode.left = new TreeNode(val);
                    break;
                } else {
                    curNode = curNode.left;
                }
            } else {
                if (curNode.right == null) {
                    curNode.right = new TreeNode(val);
                    break;
                } else {
                    curNode = curNode.right;
                }
            }
        }
        return root;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode cur, long min, long max) {
        // 相当于递归结束条件
        if (cur == null) {
            return true;
        }
        // 下面2个是递归里的判断条件
        if (cur.val <= min || cur.val >= max) {
            return false;
        }
        // 最关键的地方：定义子节点的边界值
        // 左子树最大不得超过当前node.val，右子树不得小于当前node.val
        return isValidBST(cur.left, min, cur.val) && isValidBST(cur.right, cur.val, max);
    }

    public boolean findTarget(TreeNode root, int k) {
        // HashSet的特性，可以在无需使用value值时特殊处理
        // 其底层还是HashMap，只不过Value值默认
        HashSet set = new HashSet();
        return findTarget(root, k, set);

    }

    public boolean findTarget(TreeNode root, int k, HashSet set) {
        // 递归结束条件
        if (root == null) {
            return false;
        }
        if (set.contains(root.val)) {
            return true;
        }
        // 没有找到“目标和数”，就继续添加值
        set.add(k - root.val);
        // 递归查询其左右子树
        return findTarget(root.left, k, set) || findTarget(root.right, k, set);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 将2个节点放在一起讨论，当p q均小于或大于当前节点时，那么证明他们还在当前节点的某一子树中
        // 如果不满足上一行的条件，那么证明：他们产生分叉
        // 即他们的最近公共祖先
        TreeNode ancestor = root;
        while (true) {
            if (ancestor.val > p.val && ancestor.val > q.val) {
                ancestor = ancestor.left;
            } else if (ancestor.val < p.val && ancestor.val < q.val) {
                ancestor = ancestor.right;
            } else {
                // 找到了！
                return ancestor;
            }
        }
    }

    private int postIndex = 0;
    private HashMap<Integer, Integer> map = new HashMap(16);
    // 使用类变量将中后序遍历的结果存储起来，便于查询
    private int[] inOrder;
    private int[] postOrder;

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null && postorder == null) {
            return null;
        }
        this.inOrder = inorder;
        this.postOrder = postorder;
        for (int i = 0; i < inOrder.length; i++) {
            // 将后续遍历的结果存起来，用于在中序遍历中定位到根节点，以分开左右子树
            map.put(inOrder[i], i);
        }

        return buildTree1(0, inorder.length - 1);

    }

    private TreeNode buildTree1(int left, int right) {
        // 定义递归结束条件
        if (left > right) {
            return null;
        }
        // 定位根节点，这里是难点：

        // 然后可以根据根节点在中序遍历中构造左右子节点
        // 根据
        int val = map.get(postOrder[right]);
        TreeNode root = new TreeNode(val);
        return root;
    }

    public int singleNumber(int[] nums) {
        // 思路1：使用哈希表存储每个数字和该数字出现的次数。
        // 遍历数组即可得到每个数字出现的次数，并更新哈希表，最后遍历哈希表，得到只出现一次的数字。
        // 思路2：使用集合存储数字。这里集合可以使用List
        // 遍历数组中的每个数字，如果集合中没有该数字，则将该数字加入集合，如果集合中已经有该数字，则将该数字从集合中删除，最后剩下的数字就是只出现一次的数字。
        // 但是以上方法时间复杂度均为O(N)
        // 答案，使用异或运算：任何数和其自身做异或运算，结果是 0
        // 又因为异或运算满足交换律
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        return sum;
//        HashMap<Integer, Integer> map = new HashMap(16);
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(nums[i])) {
//                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//        for (Integer key : map.keySet()) {
//            if (map.get(key) == 1) {
//                return key;
//            }
//        }
//        return 0;
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        System.out.println(objects.get(0).getClass());
        int[] array = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = threeSum(array);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        // 特殊值判断
        if (nums == null || nums.length == 1 || nums.length == 2) {
            return resList;
        }
        // 先对nums进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // i最多扩展到倒数第三位
            // 因为已经排过序，如果num[i]>0则必然无解。
            if (nums[i] > 0) {
                return resList;
            }
            // ！！！且如果当前值与下一个值相同，则会存在与之前一样的重复解，跳过这个，上一个解已经记录
            // i=0时必然无法与前一个数比较
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 当前元素记录下来
            int cur = nums[i];
            // 定义双指针L，R
            int L = i + 1, R = nums.length - 1;
            while (L < R) {
                // 在遍历中寻找有无合适的值
                int temp = cur + nums[L] + nums[R];
                // 如果temp值大于 0，说明 nums[R] 太大，R 左移。不可能是L右移，temp只会更大。
                // 如果temp值小于 0，说明 nums[L] 太大，L 左移。同理。
                if (temp == 0) {
                    // 这里需要讨论几点：去重+移动
                    // 临时list添加元素，并加入resList中
                    List<Integer> list = new ArrayList<>();
                    list.add(cur);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    resList.add(list);
                    // 去重操作：判断左届或右界是否与相邻为重复，重复就需要跳过
                    while (L < R && nums[L] == nums[L + 1]) L++;
                    while (L < R && nums[R] == nums[R - 1]) R--;
                    // 否则正常向后遍历
                    L++;
                    R--;
                } else if (temp > 0) {
                    R--;
                } else if (temp < 0) {
                    L++;
                }
            }
        }
        return resList;
    }

    public int majorityElement(int[] nums) {
        // 常规遍历法
//        HashMap<Integer, Integer> map = new HashMap();
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(nums[i])) {
//                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//            } else {
//                map.put(nums[i], 1);
//            }
//        }
//        for (Integer integer : map.keySet()) {
//            if (map.get(integer) > nums.length / 2) {
//                return integer;
//            }
//        }
//        return 0;
        // 摩尔投票法
        // 初始选择num[0]作为候选人candidate，起始票数pollCount为1
        // 遇到与candidate一样的数字，其票数+1，遇到不一样的其票数-1
        // 当pollCount为0时更换候选人，遍历完毕最终的候选人candidate就是答案
        // 解释：因为题目保证了多数元素其个数一定大于n/2，在多数元素与非多数的抵消过程中
        // 最终多少元素的个数至少为1
        int pollCount = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                pollCount++;
            } else if ((--pollCount) == 0) {
                candidate = nums[i];
                pollCount = 1;
            }
        }
        return candidate;
    }

    public void sortColors(int[] nums) {
        // 常规排序算法
//        for (int cur = 0; cur < nums.length - 1; cur++) {
//            for (int j = 0; j < nums.length - cur - 1; j++) {
//                if (nums[j] > nums[j + 1]) {
//                    int temp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = temp;
//                }
//            }
//        }
        // 循环不变量
        // 首先是一些特值处理
        int len = nums.length;
        if (len < 2) {
            return;
        }
        // all in [0,zero) is 0
        // all in [zero,cur) is 1
        // all in [two,len-1) is
        // 循环中止条件是 cur==two，那么循环可以继续的条件为 cur<two
        // 保证初始化 [0,zero)为空，zero=0！重点：遍历到0，先交换，再++
        // 保证初始化 [two,len-1)为空，two=len！重点：遍历到2，先--，再交换（取不到len下标）
        int zero = 0;
        int two = len;
        int cur = 0;
        // i==two时覆盖全部区间
        while (cur < two) {
            if (nums[cur] == 0) {
                // 和前面的cur交换
                swap(nums, cur, zero);
                cur++;
                zero++;
            } else if (nums[cur] == 1) {
                // 直接往后移动
                cur++;
            } else {
                // 和当前最后面的下标交换
                two--;
                swap(nums, cur, two);
                // 为什么不需要cur++?
                // 因为交换完之后，当前index[cur]的值已经不是原来的2了
                // 这个值是否需要进一步修改它的位置仍需要遍历一遍。嗯，很准确。。。
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
