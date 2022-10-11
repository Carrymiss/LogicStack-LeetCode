package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-11
 * @Time: 18:36
 * @Description: 题目19 : 删除链表的倒数第N个节点
 */
public class Topic_00019 {
    /**
     * 方法1 ：模拟
     * 思路：
     * 只需要找到删除节点的前节点 pre，
     * 使 pre.next=pre.next.next 即可
     */
    public ListNode removeNthFromEnd01(ListNode head, int n) {
        ListNode pre = head;

        int len = length01(head) - n;
        if (len == 0) {
            // 删除的是头节点，就返回头节点的下一个节点
            return head.next;
        }
        // 找到删除节点的前节点位置
        for (int i = 0; i < len - 1; i++) {
            pre = pre.next;
        }
        // 删除指定结点
        pre.next = pre.next.next;
        return head;
    }

    // 获取链表的长度
    public int length01(ListNode node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    /**
     * 方法2 ：双指针
     * 思路：
     * *    2个指针的间距为 n ,当第一个指针到达终点的时候，第二个指针到达n的起点
     * *    如果使用 pre.next=pre.next.next
     * *    则让一个指针的下一个结点为空， 第二个指针正好指向要删除的结点
     */
    public ListNode removeNthFromEnd02(ListNode head, int n) {
        ListNode right = head, left = head;
        // 把指针right向后移动n位
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        // 如果right指针位空，表示删除的是头节点
        if (right == null) {
            return head.next;
        }
        // 这里就是上面说的要用
        /**
         * 这里就是上面说的要用
         * pre.next=pre.next.next
         * 要让 right.next指向空而不是right指向空
         */
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        // 删除指定结点
        left.next = left.next.next;
        return head;
    }

    /**
     * 方法3 ：递归
     * 思路：
     * *    还是使用 pre.next=pre.next.next 用pre=pre.next 也可以
     * *    逆序递归，去的时候，首先执行链表长度次，此时node指向null，
     * *    回来的时候 当 pos == n + 1 时 node结点为要删除结点的前一位
     * * 使用 pre.next=pre.next.next删除
     * *    如果回来到头也就是 pos == n的时候，说明删除的是头节点，
     * *    直接指向头结点的下一个结点
     */
    public ListNode removeNthFromEnd03(ListNode head, int n) {
        int pos = length02(head, n);
        // 说明要删除的结点为头节点
        if(pos==n){
            head=head.next;
        }
        return head;
    }

    /**
     * 获取节点所在位置，逆序
     * * 这里无法处理 删除头节点的情况
     */
    public int length02(ListNode node, int n) {
        // 递归结束条件
        if (node == null) {
            return 0;
        }
        // 使node结点不断指向下一个结点
        int pos = length02(node.next, n) + 1;
        // 获取要删除结点的前一个结点
        if (pos == n + 1) {
            // 删除指定结点
            node.next = node.next.next;
        }
        return pos;
    }
}
