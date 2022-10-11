package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-10
 * @Time: 19:27
 * @Description: 题目237 : 删除链表中的节点
 */
public class Topic_00237 {
    /**
     * 方法 ： 模拟
     * 思路： 这是算法题？？？？
     *      删除节点为 node，哈哈哈哈哈哈
     *      我还想了半天
     */
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}
