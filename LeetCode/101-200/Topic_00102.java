package leetcode_BasicAlgorithm.tree;

import practise.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: ZTY
 * @Create: 2022-10-18
 * @Time: 8:26
 * @Description: 题目102 ：二叉树的层序遍历
 */
public class Topic_00102 {
    /**
     * 方法1 ： BFS 广度优先算法
     */
    public List<List<Integer>> levelOrder01(TreeNode root) {
        // 边界条件判断
        if (root == null) {
            return new ArrayList<>();
        }
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        // 根节点入队
        queue.add(root);
        // 如果队列不为空就继续循环
        while (!queue.isEmpty()) {
            // BFS打印，levelNum表示的是每层的结点数
            int levelNum = queue.size();
            // subList存储的是每层的结点值
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                //出队
                TreeNode node = queue.poll();
                subList.add(node.val);
                //左右子节点如果不为空就加入到队列中
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            //把每层的结点值存储在res中
            res.add(subList);
        }
        return res;
    }

    /**
     * 方法2 ：DFS
     * <p>这题让一层一层的打印，其实就是BFS，但使用DFS也是可以解决的
     */
    public List<List<Integer>> levelOrder02(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        levelHelper(res,root,0);
        return res;

    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        //边界条件判断
        if (root == null) {
            return ;
        }
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        // 这里我想了一下，==就可以了，所以改了下题解
        if(level==list.size()){
            list.add(new ArrayList<>());
        }
        //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
        list.get(level).add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list,root.left,level+1);
        levelHelper(list,root.right,level+1);
    }
}
