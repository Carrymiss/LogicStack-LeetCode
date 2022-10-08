package practise;

/**
 * @Author: LQ
 * @Create: 2022-03-31
 * @Time: 15:06
 * @Description: 题目687 ：最长同值路径
 */
public class Topic_00687 {
    int side;
    // 定义节点
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int longestUnivaluePath(TreeNode root) {
        side = 0;
        depth(root);
        return side;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxhigh = 0;
        int left = depth(node.left);
        int right = depth(node.right);
        if (node.left != null && node.left.val == node.val && node.right != null && node.right.val == node.val) {
            side = Math.max(side, left + right + 2);
        }
        if (node.left != null && node.left.val == node.val) {
            maxhigh = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            maxhigh = Math.max(maxhigh, right + 1);
        }
        side = Math.max(side, maxhigh);
        return maxhigh;
    }

    public static void main(String[] args) {
        TreeNode node4=new TreeNode(1);
        TreeNode node5=new TreeNode(2);
        TreeNode node6=new TreeNode(5);
        TreeNode node7=new TreeNode(6);
        TreeNode node3=new TreeNode(5,node6,node7);
        TreeNode node2=new TreeNode(4,node4,node5);
        TreeNode node1=new TreeNode(5,node2,node3);

        Topic_00687 test1=new Topic_00687();
        System.out.println(test1.longestUnivaluePath(node1));
    }
}
