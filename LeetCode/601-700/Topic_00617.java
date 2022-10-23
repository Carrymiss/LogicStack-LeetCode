package practise;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZTY
 * @Create: 2022-10-21
 * @Time: 7:27
 * @Description: 题目617 ：合并二叉树
 */
public class Topic_00617 {
    /**
     * 方法1 : DFS
     */
    public TreeNode mergeTrees01(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 合并根节点
        root1.val += root1.val;
        // 递归合并左结点
        root1.left = mergeTrees01(root1.left, root2.left);
        // 递归合并右结点
        root1.right = mergeTrees01(root1.right, root2.right);
        return root1;
    }

    /**
     * 方法2 ：优化上面DFS
     * <p>  思路：
     * <p>     不改变原来的树形结构，创建一个新的树
     */
    public TreeNode mergeTrees02(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 创建新树,并给根节点赋值
        TreeNode newNode = new TreeNode(root1.val + root2.val);
        // 合并左结点
        newNode.left = mergeTrees02(root1.left, root2.left);
        // 合并右结点
        newNode.right = mergeTrees02(root1.right, root2.right);
        return newNode;
    }

    /**
     * 方法3 ： BFS
     * <p>  思路：
     * <p>     一层层遍历，在root1树中进行合并
     * <p>          这里要注意2点：
     * <p>              1.入队与出出队的顺序
     * <p>              2.如果一个结点为空，它对应另一个树的结点不为空（为空也没关系）
     * <p>                  不管它有没有子结点，只需要把他整体迁移过来即可
     */
    public TreeNode mergeTrees03(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        //队列中两棵树的节点同时存在
        Queue<TreeNode> queue = new LinkedList<>();
        //把这两棵树的节点同时入队
        queue.add(root1);
        queue.add(root2);
        while (!queue.isEmpty()) {
            //两棵树的节点同时出队
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            //把这两个节点的值相加，然后合并到第1棵树的节点上
            node1.val += node2.val;
            if (node1.left == null) {
                //如果node1左子节点为空
                //我们直接让node2的左子结点成为node1的左子结点，
                node1.left = node2.left;
            } else {
                //执行到这一步，说明node1的左子节点不为空，
                //如果node2的左子节点为空就不需要合并了，
                //只有node2的左子节点不为空的时候才需要合并
                if (node2.left != null) {
                    queue.add(node1.left);
                    queue.add(node2.left);
                }
            }

            //原理同上，上面判断的是左子节点，这里判断的是右子节点
            if (node1.right == null) {
                node1.right = node2.right;
            } else {
                if (node2.right != null) {
                    queue.add(node1.right);
                    queue.add(node2.right);
                }
            }
        }
        //把第2棵树合并到第1棵树上，所以返回的是第1棵树
        return root1;
    }
}
