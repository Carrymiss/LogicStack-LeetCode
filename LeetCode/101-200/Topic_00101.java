package leetcode_BasicAlgorithm.tree;

import practise.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZTY
 * @Create: 2022-10-17
 * @Time: 9:09
 * @Description: 题目101 ：对称二叉树
 */
public class Topic_00101 {
    /**
     * 方法1 ：递归
     */
    public boolean isSymmetric01(TreeNode root) {
        //从两个子节点开始判断
        return isSymmetricHelper01(root.left, root.right);
    }

    public boolean isSymmetricHelper01(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null) {
            return true;
        }
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        // 这里画个图 逻辑还是很清晰的
        return isSymmetricHelper01(left.left, right.right) && isSymmetricHelper01(left.right, right.left);
    }

    /**
     * 方法2 ：非递归解决
     */
    public boolean isSymmetric02(TreeNode root) {
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 左子节点和右子节点同时入队
        queue.add(root.left);
        queue.add(root.right);
        // 如果队列不为空就继续循环
        while (!queue.isEmpty()) {
            // 每两个出队
            // poll方法：检索并移除此队列的头，如果此队列为空，则返回null。
            TreeNode left = queue.poll(), right = queue.poll();
            //如果都为空继续循环
            if (left == null && right == null) {
                continue;
            }
            //如果一个为空一个不为空，说明不是对称的，直接返回false
            // 使用异或
            if (left == null ^ right == null) {
                return false;
            }
            //如果这两个值不相同，也不是对称的，直接返回false
            if (left.val != right.val) {
                return false;
            }
            //这里要记住入队的顺序，他会每两个两个的出队。
            //左子节点的左子节点和右子节点的右子节点同时
            //入队，因为他俩会同时比较。
            //左子节点的右子节点和右子节点的左子节点同时入队，
            //因为他俩会同时比较
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }
}
