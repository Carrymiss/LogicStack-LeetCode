package practise;

import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: LQ
 * @Create: 2022-04-06
 * @Time: 16:47
 * @Description: 题目19 ：删除链表的倒数第 N 个结点
 */
public class Topic_00019 {
    // 定义节点
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        return delete_node(head, n);
    }

    public ListNode delete_node(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int len = get_length(head);
        ListNode cur = dummy;
        for (int i = 1; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public static int get_length(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
