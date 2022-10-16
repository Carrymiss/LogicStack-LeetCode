package leetcode_BasicAlgorithm.tree;


import practise.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-15
 * @Time: 12:53
 * @Description: 题目104 : 二叉树的最大深度
 */
public class Topic_00104 {
    /**
     * 方法1 ：递归
     */
    public int maxDepth01(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 最后要加上1
        return Math.max(maxDepth01(root.left), maxDepth01(root.right)) + 1;
    }

    /**
     * 方法2：优化上面递归
     * <p> 给它写成一行
     */
    public int maxDepth02(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth02(root.left), maxDepth02(root.right)) + 1;
    }

    /**
     * 方法3 ：BFS 广度优先算法
     * <p> 就是一层一层遍历
     */
    public int maxDepth03(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //创建一个双向链表 Deque 是双向队列
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int count = 0;
        while (!deque.isEmpty()) {
            //每一层的个数
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                // 从这个deque表示的堆栈中弹出一个元素。
                // 换句话说，删除并返回这个deque的第一个元素。
                TreeNode temp = deque.pop();
                if (temp.left != null) {
                    // 如果可以在不违反容量限制的情况下立即插入指定元素，
                    // 则在此deque末尾插入该元素，如果当前没有可用空间，
                    // 则抛出IllegalStateException。
                    // 当使用容量受限的deque时，通常最好使用offerLast方法。
                    deque.addLast(temp.left);
                }
                if (temp.right != null) {
                    deque.addLast(temp.right);
                }
            }
            count++;
        }
        return count;
    }

    /**
     * 方法4 ：DFS 深度优先算法
     * 思路：
     * <P>    使用两个栈，一个记录节点的stack栈，一个记录节点所在层数的level栈，
     * <P>    stack中每个节点在level中都会有一个对应的值，
     * <P>    并且他们是同时出栈，同时入栈
     */
    public int maxDepth04(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //stack记录的是节点，而level中的元素和stack中的元素
        //是同时入栈同时出栈，并且level记录的是节点在第几层
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> level = new Stack<>();
        stack.push(root);
        level.push(1);
        int max = 0;
        // 我感觉和上面那个方法差不多,这个是先获取最大深度，然后向下继续
        // BFS那个是先确定能不能继续向下，再获取深度
        while (!stack.isEmpty()) {
            //stack中的元素和level中的元素同时出栈
            TreeNode tempS = stack.pop();
            int tempL = level.pop();
            // 获取最大深度
            max = Math.max(max, tempL);
            // 这里不是tempL++
            // tempL+1，即无论是左右哪个有，或者都有，最后结果增加一次
            if(tempS.left!=null){
                //同时入栈
                stack.push(tempS.left);
                level.push(tempL+1);
            }
            if(tempS.right!=null){
                //同时入栈
                stack.push(tempS.right);
                level.push(tempL+1);
            }
        }
        return max;
    }

}
