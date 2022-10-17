package leetcode_BasicAlgorithm.tree;

import practise.TreeNode;

import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-16
 * @Time: 10:06
 * @Description: 题目
 */
public class Topic_00098 {
    /**
     * 方法1 ：中序遍历
     * <p> 思路：
     * <p>  中序遍历后，输出的结点正好为 左 中 右
     * <p>  按照题目要求，如果这个顺序的输出正好是从小到大则返回true
     * <p>  如果其中右一步右问题则返回fasle
     */
    //前一个结点，全局的
    TreeNode prev;

    public boolean isValidBST01(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归访问左子树
        if (!isValidBST01(root.left)) {
            // 这里如果上面返回的是true 说明是二叉搜索树
            // 所以如果返回fasle，让它直接返回fasle，不做下一步递归
            return false;
        }
        // 判断这个结点的是否 大于 他的上一个结点
        // prev!=null 是因为获取到树的最左端的结点时，它的前结点是null
        /**
         * 题解这里写的有些别扭
         *  if (prev != null && prev.val >= root.val)
         *         return false;
         * 它的意思其实是，反正最后都return true，那我让每一步都输出fasle
         *
         * 而且还不能else 因为这样就没法递归右子树
         *
         * 所以把这个 if (prev != null && prev.val < root.val)
         * 改成 if (prev != null && prev.val >= root.val)
         */
        if (prev != null && prev.val >= root.val) {
            return false;
        }
        // 给前节点pre赋值
        // 整体逻辑就是中序遍历，
        // 这里就是让前结点prev通过递归不断等于它的下一个结点
        prev = root;
        // 递归访问右子树
        if (!isValidBST01(root.right)) {
            return false;
        }
        return true;
    }

    /**
     * 方法2 : 递归写法
     * <p> 思路：
     * <p>  1.递归左右子树
     * <p>  2.递归时加上范围判断，也就说 左 < 中 < 右
     * <p>  3.一定注意，不要只判断 左 < 中 或者 中 < 右
     * <p>  3.如果想只判断一步 可以参考 方法1 中序遍历
     * <p>  4.左为：1 右为：4  那么中的范围就是： 2<=中<=3
     */
    public boolean isValidBST02(TreeNode root) {
        // 根节点的比较范围是：Long.MIN_VALUE >= root >= Long.MAX_VALUE
        return isValidBST02(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST02(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        //每个节点如果超过这个范围，直接返回false
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        //这里再分别以左右两个子节点分别判断，
        //左子树范围的最小值是minVal，最大值是当前节点的值，也就是root的值，因为左子树的值要比当前节点小
        //右子数范围的最大值是maxVal，最小值是当前节点的值，也就是root的值，因为右子树的值要比当前节点大
        return isValidBST02(root.left, minVal, root.val) && isValidBST02(root.right, root.val, maxVal);
    }

    /**
     * 方法3 ：中序遍历非递归
     * <p> 利用栈的FILO 将左子树的所有节点压栈，
     * <p> 然后分别弹出，弹出时比较它后一个节点的值
     * <p>      此时的root就是它的下一个节点
     * <p>      下一个节点，就是二叉搜索树中，大于它的最小节点
     */
    public boolean isValidBST03(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            // 将左子树结点入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 弹出一个目前最左结点
            root = stack.pop();
            // 进行比较，如果不符合范围，直接返回fasle
            if (prev != null && root.val <= prev.val) {
                return false;
            }
            //保存前一个访问的结点
            prev = root;
            // 这个节点，就是二叉搜索树中，大于它的最小节点，可也能为null
            root = root.right;
        }
        return true;
    }
}
