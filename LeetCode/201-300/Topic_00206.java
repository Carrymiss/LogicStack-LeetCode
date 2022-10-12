package leetcode_BasicAlgorithm.linkedList;

import practise.ListNode;

import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-11
 * @Time: 19:48
 * @Description: 题目206 : 反转链表
 */
public class Topic_00206 {
    /**
     * 方法 1 ： 使用栈结构
     */
    public ListNode reverseList01(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        // 把所有结点放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        // 排除头节点为空的情况
        if (stack.isEmpty()) {
            return head;
        }
        // 重新构建链表
        ListNode node = stack.pop();
        ListNode newHead = node;
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        // 这里head相当于变成了尾结点，尾结点都是为空的
        // 否则会构成环状链表
        node.next = null;
        return newHead;
    }

    /**
     * 方法 2 ： 双链表
     * * 思路：
     * *    第一个链表从头删除元素
     * *    删除的元素为第二个链表的头节点
     */
    public ListNode reverseList02(ListNode head) {
        // 第二个链表
        ListNode newHead = null;
        while (head != null) {
            // 取出第一个链表的新头节点
            ListNode temp = head.next;
            // 将第一个链表的原头节点指向目前第二张链表的头节点
            head.next = newHead;
            // 更新第二个链表的头节点
            newHead = head;
            // 更新第一个链表的头节点
            head = temp;
        }
        return newHead;
    }

    /**
     * 方法 3 ： 递归
     */
    public ListNode reverseList03(ListNode head) {
        // 终止条件
        if(head==null||head.next==null){
            return head;
        }
        // 这个结点为递归的参数
        ListNode next=head.next;
        // 也就是每次从头结点的下一个结点递归
        ListNode reverse=reverseList03(next);
        // 要想上面返回了一条的链表
        // 需要将head结点挂到 next的后方
        next.next=head;
        // head结点成了尾结点
        // 尾结点的下一个结点应该尾空，
        // 这样相当于每次递归回来取得最后一个结点，都让它的下一个结点为空
        head.next=null;
        return reverse;
    }

    /**
     * 方法 4 ： 尾递归
     * * 思路：
     * *    处理完之后直接返回递归的结果
     */
    public ListNode reverseList04(ListNode head) {
        return reverseListInt01(head,null);
    }
    public ListNode reverseListInt01(ListNode head,ListNode newHead) {
        if(head==null){
            return newHead;
        }
        // 翻转链表
        ListNode next=head.next;
        head.next=newHead;
        return reverseListInt01(next,head);
    }

    /**
     * 方法 5：修改递归方法(类似于方法 3)
     */
    public ListNode reverseList05(ListNode head) {
        return reverseListInt02(head,null);
    }
    private ListNode reverseListInt02(ListNode head, ListNode newHead) {
        if(head==null){
            return newHead;
        }
        ListNode next=head.next;
        head.next=newHead;
        ListNode node = reverseListInt02(next, head);
        return node;
    }
}
