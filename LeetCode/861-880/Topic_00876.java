package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-06
 * @Time: 17:54
 * @Description: 题目876 ：链表的中间结点
 */
public class Topic_00876 {
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

    public ListNode middleNode01(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        int len = get_length(head);
        int mid = len / 2;
        while (mid > 0) {
            dummy = dummy.next;
            mid--;
        }
        return dummy;
    }

    public int get_length(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    /**
     * 快慢指针法
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。
     * 那么当 fast 到达链表的末尾时，slow 必然位于中间。
     */
    public ListNode middleNode02(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
