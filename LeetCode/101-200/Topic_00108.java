package leetcode_BasicAlgorithm.tree;

import practise.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZTY
 * @Create: 2022-10-19
 * @Time: 8:16
 * @Description: 题目108 ：将有序数组转换为二叉搜索树
 */
public class Topic_00108 {
    /**
     * 方法1 ： 递归
     * 思路：
     * 使用递归的方式，每次取数组中间的值比如m作为当前节点，
     * m前面的值作为他左子树的结点值，m后面的值作为他右子树的节点值
     */
    public TreeNode sortedArrayToBST01(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
        // 设置出口
        if (start > end) {
            return null;
        }
        // 取中间值
        // int mid=(start+end)/2;
        int mid = (start + end) >> 1;
        // 创建父节点。最后一次递归创建的是根节点
        TreeNode root = new TreeNode(num[mid]);
        // 递归创建左子树
        root.left = sortedArrayToBST(num, start, mid - 1);
        // 递归创建右子树
        root.right = sortedArrayToBST(num, mid + 1, end);
        // 返回此时的树
        return root;
    }

    /**
     * 方法2 ： BFS 广度优先
     * 思路：
     * 把数组不停的分为两部分，保存在队列中，然后不停的出队，创建结点
     */
    public TreeNode sortedArrayToBST02(int[] nums) {
        // 创建队列
        // 存左右子树区间的队列
        Queue<int[]> rangeQueue = new LinkedList<>();
        // 存左右子树元素的队列
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        // 获取中间值，以及起点和终点
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) >> 1;
        // 设置根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 入队
        rangeQueue.add(new int[]{start, mid - 1});
        rangeQueue.add(new int[]{mid + 1, end});
        // 这里因为下面跳出的条件是 rangeQueue为空，而rangeQueue每次放入2个值
        // 所以这里也要放入2次值，且2次值相同，因为下面每次循环2次，父节点都是相同的
        nodeQueue.add(root);
        nodeQueue.add(root);
        // 构建二叉搜索树
        while (!rangeQueue.isEmpty()) {
            // 出队
            int[] range = rangeQueue.poll();
            // 弹出当前父节点
            TreeNode currentNode = nodeQueue.poll();
            // 设置目前的起点和终点
            start = range[0];
            end = range[1];
            // 判断临界条件
            if (start > end) {
                continue;
            }
            // 取目前中间值
            mid = (start + end) >> 1;
            int midValue = nums[mid];
            // 创建子树的父节点，也就是上面父节点的子节点
            TreeNode newNode = new TreeNode(midValue);
            // 判断是左子树还是右子树
            if (midValue > currentNode.val) {
                currentNode.right = newNode;
            } else {
                currentNode.left = newNode;
            }
            // 如果还有其它子节点，按照上面的顺序入队
            if(start<end){
                rangeQueue.add(new int[]{start, mid - 1});
                rangeQueue.add(new int[]{mid + 1, end});
                // 和上面一样，当前父结点还是要放2次
                nodeQueue.add(newNode);
                nodeQueue.add(newNode);
            }
        }
        return root;
    }
}
