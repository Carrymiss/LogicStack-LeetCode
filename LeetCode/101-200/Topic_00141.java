package leetcode_BasicAlgorithm.linkedList;

import practise.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2022-10-14
 * @Time: 8:39
 * @Description: 题目141 : 环形链表
 */
public class Topic_00141 {
    /**
     * 方法1 ： 快慢指针
     * * 具体思路可以画图，不论环形结点是奇数还是偶数，
     * *    快慢指针都会相遇
     */
    public boolean hasCycle01(ListNode head) {
        // 结点的值可能为null，太坑了题目里面说好的取值范围呢 还能有null？
        if (head == null) {
            return false;
        }
        // 指定快慢指针
        ListNode fast = head;
        ListNode slow = head;
        // 这里循环快指针
        // 因为如果不是环，快指针已经指向null，而慢指针还在路上走着
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法2 ： SET集合
     */
    public boolean hasCycle02(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            //如果重复出现说明有环
            if (set.contains(head)) {
                return true;
            }
            //否则就把当前节点加入到集合中
            set.add(head);
            head = head.next;
        }
        // 执行到这说明没有环
        return false;
    }

    /**
     * 方法3 ： 递归
     * * 思路：
     * *     删除链表的头节点
     * *     1. 创建一个temp=head.next
     * *     2. head.next=head  这里让头结点指向自己 相当于删除了头节点
     * *     3. head=temp 这个步骤做成递归
     * *         出口就是 找到重复结点 或者遍历到最后一个
     */
    public boolean hasCycle03(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        // 删除头结点
        ListNode temp = head.next;
        head.next = head;
        return hasCycle03(temp);
    }

    /**
     * 方法4 ： 先反转在比较
     * *    思路：
     * *    这个不画图是真没法理解
     * *    这个构思很巧妙：
     * *        “如果有环，那么链表反转之后，原来的头结点和反转之后的头结点一定是同一个”
     * *    写了一大段，想想还是算了 画图吧 画图一下就清晰了
     */
    public boolean hasCycle04(ListNode head) {
        ListNode res = reverseList(head);
        if (head != null && head.next != null && res == head) {
            return true;
        }
        return false;
    }

    public ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            ListNode temp = head.next;
            /**
             * 每次访问的原链表节点都会成为新链表的头结点，
             * 其实就是把新链表挂到访问的原链表节点的
             * 后面就行了
             */
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }
}
