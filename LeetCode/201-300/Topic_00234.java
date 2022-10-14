package leetcode_BasicAlgorithm.linkedList;

import practise.ListNode;

import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-13
 * @Time: 19:38
 * @Description: 题目234 : 回文链表
 */
public class Topic_00234 {
    /**
     * 方法1 ：快慢指针
     * 思路：
     * *    第一步： 找出中间结点
     * *    设置2个指针，fast一次走2步，slow一次走一步
     * *    当fast的到达终点时，走一步的刚好到达中间点
     * *
     * *    如果此时fast的下一个为空，而fast不为空
     * *    说明链表长度是奇数
     * *    fast为空 说明链表长度是偶数
     * *
     * *    第二步： 反转链表 进行比较
     * *    链表长度是偶数 直接从 slow处反转
     * *    链表长度是奇数 直接从 slow.next处反转
     * *    然后循环比较
     */
    public boolean isPalindrome01(ListNode head) {
        // 创建快慢指针
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数
        if (fast != null) {
            // 反转从slow的下一个结点开始
            slow = slow.next;
        }
        // 反转链表
        slow = reverse(slow);
        // 进行比较
        fast = head;
        // 这里用反转的链表循环
        while (slow != null) {
            // 判断节点值是否相等
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    //反转链表
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            // 反转每一个结点
            // 不理解可以自己画个图 画完就清晰
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 方法2 ： 栈
     * 思路：
     * *    先把链表的节点全部存放到栈中，然后再一个个出栈，
     * *    这样就相当于链表从后往前访问了
     */
    public boolean isPalindrome02(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        // 然后再出栈 进行判断
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 方法3 ：优化方法2
     * 思路：
     * *    上面的判断只需要判断一半就好了
     */
    public boolean isPalindrome03(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        // 链表的长度
        int len = 0;
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
            len++;
        }
        //len长度除以2
        len >>= 1;
        // 这里无论是奇数还是偶数，都只需要判断前半段即可
        // 然后再出栈 进行判断
        while (len-- >= 0) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 方法4 ：递归
     *      //  对链表逆序打印
     *     /*private void printListNode(ListNode head) {
     *         if (head == null)
     *             return;
     *         printListNode(head.next);
     *         System.out.println(head.val);
     *     }*/
    // 创建成员变量
    ListNode temp;
    public boolean isPalindrome04(ListNode head) {
        temp=head;
        return check(head);
    }


    private boolean check(ListNode head) {
        if (head == null) {
            return true;
        }
        // 递归回来的结果与值判断进行与运算
        boolean res=check(head.next)&&(head.val==temp.val);
        temp=temp.next;
        return res;
    }
}
