package com.zhangxing.datastratures.ds.questions;

import java.util.*;

/**
 * @Author zhangxing
 * @Date 2021/9/4 13:37
 * @Version 1.0
 * @Description
 */
class T12 {
    public static void main(String[] args) {
        HashMap map = new HashMap(16);
        map.put("zx", 2);
        Object zx = map.put("zx", 33);
        System.out.println(zx.toString());
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 重点：链表的头节点 head 有可能需要被删除
        // 因此创建哑节点，dummyHead.next=head
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 而用 temp 往后遍历
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    public static ListNode reverseList(ListNode head) {
        // 迭代
        // 想要反转，重点是将指向改变。
        ListNode pre = null;
        ListNode cur = head;
        // 最终，当前指向为null时，结束。
        // 不过，最终返回的是cur的前一指向pre
        while (cur != null) {
            // 首先要记录下cur当前的下一指向，用于将cur稍后指向这一指向
            // 这样才能一直往后迭代
            ListNode temp = cur.next;
            // 将当前的next指向前面一个，这就完成了反转
            cur.next = pre;
            // 但是，结束反转。要加当前cur变为pre，用于下一轮的指向
            pre = cur;
            // 同理，要将当前cur变为刚刚存储的原来的下一个指向，这样就实现了往后迭代
            cur = temp;
        }
        return pre;

//        // 递龟
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode newHead = reverseList(head.next);
//        // 产生倒置转化在这一步，head.next是原来的下一指向
//        // 将它的下一指向，指向现在的节点，那是不是就完成了倒置？
//        head.next.next = head;
//        // 此外！我们没有操作当前的下一指向，也就是说
//        // head.next还是指向原来的next，这就会产生环
//        head.next = null;
//        return newHead;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        reverseList(head);
        if (head == null) {
            return null;
        }
        // 题目已经说明了按升序排列
        // 第一个元素肯定不需要删除，所以不需要虚拟头节点
        ListNode last = head;
        // 这样不访问到最后一个元素，不然会有空指针
        // 此外，这样也能检查到最后一个数是不是与其前一个数相同
        while (last.next != null) {
            if (last.val == last.next.val) {
                // 删除
                last.next = last.next.next;
            } else {
                // 将当前值加入，同时往后遍历
                last = last.next;
            }
        }
        return head;
    }

    public boolean isValid(String s) {
        byte a1 = 2, a2 = 4, a3;
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack stack = new Stack();
        // 用各种if else太蠢，精巧的设计：
        // 为了快速判断括号的类型可以使用哈希表存储每一种括号。
        // 哈希表的键为右括号，值为相同类型的左括号。
        // 想要用左括号匹配有括号，那么每次需要判断栈中有无左括号匹配右括号
        Map<Character, Character> map = new HashMap<>(16);
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (map.containsKey(curChar)) {
                // 如果当前栈中无内容，肯定返回false，因为来的是右括号。
                // 如果栈顶的无法匹配上相应的右括号，也返回false，因为不服题意。
                if (stack.size() == 0 || stack.peek() != map.get(curChar)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.add(curChar);
            }
        }
        return stack.size() == 0;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public List<Integer> preorderTraversalIteration(TreeNode root) {
        // 首先，要明确。前序遍历的迭代写法是用栈存放，然后按照中左右的顺序来弹出
        // 迭代过程中要始终保持这个顺序（循环迭代中自动帮忙保持）
        // Start!
        // 首先构造一个栈，用于遍历存放
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        // 首先将根节点放入
        stack.push(root);
        // 循环迭代，因为迭代中是不停的弹出，然后入栈操作，只要不为空，就不结束
        while (!stack.isEmpty()) {
            // 每次迭代先取出栈顶元素
            TreeNode curNode = stack.pop();
            // 2个操作，第一，将这个值输出；第二，将它的右左子树入栈（如果有）
            list.add(curNode.val);
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }

    public List<Integer> inorderTraversalIteration(TreeNode root) {
        // 首先，还是通过栈来遍历迭代。
        // 一个特点是：对任何当前节点，都要一直先去找它的左节点。并不断加入栈（压在下面）
        // 直到左节点为空，才退出这个小迭代
        List<Integer> list = new ArrayList();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack();
        // 首先定义当前节点，并加入栈
        TreeNode curNode = root;
        // 进行大的迭代：1. 栈不为空 2. cur结点仍有指向
        // 因为用例证明[1,null,2,3]证明：1先入栈，然后弹出，没有新节点入栈。
        // 但是curNode指向了root的右节点，需要下一轮迭代。
        // 仅仅满足栈不为空不符合实际。
        while (!stack.isEmpty() || curNode != null) {
            // 一直找左节点！并加入
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            // 结束了就先POP一个出来，这个也就是最左侧完整左子树的左节点，第一个
            TreeNode node = stack.pop();
            list.add(node.val);
            // 如果当前节点有右子树，继续这样的迭代。类似模拟递归，保证右子树正常压入
            // 如果没有的话，下一伦迭代将弹出下一个应该弹出的值，直到结束
            if (node.right != null) {
                curNode = node.right;
            }
        }
        return list;
    }

    public List<Integer> postTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    private void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }

    public List<Integer> postorderTraversalIteration(TreeNode root) {
        // 总体思路：使用2个栈，一个栈暂时保持，一个栈输出结果。
        // 因为输出结果栈必然要是左右中的输出，所以压入的时候要中右左压入。
        // 那么中间栈需要弹出按照中右左弹出
        // 因为我们在一开始时已经压入根节点，然后迭代开始时弹出这个根，再压入左右子树
        // 还是有一种模拟递归的感觉，每次迭代的时候都是先弹出最上层，也就是将根节点先压入结果栈
        // 然后再压入其左右子树。
        // 初始化操作
        Stack<TreeNode> tempStack = new Stack<>();
        Stack<TreeNode> resStack = new Stack<>();
        List<Integer> resList = new ArrayList<>();
        if (root == null) {
            return resList;
        }
        // 先压入根节点
        tempStack.push(root);
        // 开始迭代，临时栈为空退出
        while (!tempStack.isEmpty()) {
            // 压入结果栈，按照递归的思想就相当于：在结果栈中先压根，再右，再左。
            TreeNode curNode = tempStack.pop();
            resStack.push(curNode);
            // 在临时栈中压入左和右，弹出并压入结果栈的操作交由上面2行
            if (curNode.left != null) {
                // 不用担心节点是否需要移动，因为栈能准确弹出目标节点并指向
                tempStack.push(curNode.left);
            }
            if (curNode.right != null) {
                tempStack.push(curNode.right);
            }
        }
        while (!resStack.isEmpty()) {
            TreeNode resNode = resStack.pop();
            resList.add(resNode.val);
        }
        return resList;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 定义一个队列用于遍历
        Queue<TreeNode> queue = new ArrayDeque();
        // 定义一个List用于存放结果，针对每一层都是一个list
        List<List<Integer>> res = new ArrayList<>();
        //先将根节点放到队列中，然后不断遍历队列。
        queue.add(root);
        // 队列不为空就一直遍历
        while (!queue.isEmpty()) {
            // 每一次的遍历都需要初始化2个东西
            // 1. 当前队列的长度，也就是一层元素的数量：
            // 也就是本层需要循环的次数，本层有子节点的需要加入
            // 2. 新的list用于存放结果
            int curSize = queue.size();
            List<Integer> curList = new ArrayList();
            for (int i = 0; i < curSize; i++) {
                // 每次弹出队列的一个元素
                TreeNode curNode = queue.poll();
                // 先将结果放入list
                curList.add(curNode.val);
                // 检查其左右子树
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            // 每一层结束，res加入新的结果
            res.add(curList);
        }
        return res;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode curLeft = root.left;
        TreeNode curRight = root.right;
        root.left = invertTree(curRight);
        root.right = invertTree(curLeft);
        return root;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 这样递归才有终止条件
        if (root == null) {
            return false;
            // 题目的诉求：根节点到叶子节点 的路径，所以要走到其没有左右子树
        } else if (root.left == null && root.right == null) {
            // 走到叶子节点这判断该条路是否为正确路径
            return targetSum == root.val;
        } else {
            int curVal = targetSum - root.val;
            return hasPathSum(root.left, curVal) || hasPathSum(root.right, curVal);
        }
    }


}
