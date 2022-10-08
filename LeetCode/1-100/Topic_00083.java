package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-02
 * @Time: 9:26
 * @Description: 题目83 ：删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。
 * 返回 已排序的链表 。
 */
public class Topic_00083 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node=head;
        if(node==null){
            return node;
        }
        while (node.next!=null){
            if(node.next.val==node.val){
                node.next=node.next.next;
            }else {
                node=node.next;
            }
        }

        return head;
    }
}
