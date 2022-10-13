package leetcode_BasicAlgorithm.linkedList;

import practise.ListNode;

/**
 * @Author: ZTY
 * @Create: 2022-10-12
 * @Time: 20:37
 * @Description: 题目21 :合并两个有序链表
 */
public class Topic_00021 {
    /**
     * 方法1：非递归方法
     * * 思路：
     * *    加入第三个链表，将比较后的小的值放入第三个链表
     */
    public ListNode mergeTwoLists01(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        //引入第三个链表
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            //比较一下，哪个小就把哪个放到新的链表中
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            // 类似于第三个链表的指针不断后移
            curr = curr.next;
        }
        //然后把那个不为空的链表挂到新的链表中
        curr.next = list1 == null ? list2 : list1;
        // 这里注意一下，是dummy链表的第二个结点
        return dummy.next;
    }

    /**
     * 方法2：递归
     * * 思路：
     * *  通过递归去的时候，找到最大的元素或链表
     * *   回来的时候，把小于或等于(这里先赋的是链表1的值)的值指向新链表的头节点
     */
    public ListNode mergeTwoLists02(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val > list2.val) {
            list2.next = mergeTwoLists02(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoLists02(list1.next, list2);
            return list1;
        }
    }

    /**
     * 方法3：优化上面的递归
     */
    public ListNode mergeTwoLists03(ListNode list1, ListNode list2) {
        // 只要有一个为空，就返回另一个
        if (list1 == null || list2 == null) {
            return list2 == null ? list1 : list2;
        }

        // 让head指向小的值 递归回来的时候，小值就是头节点
        ListNode head = (list1.val > list2.val) ? list2 : list1;
        /**
         * 递归的时候，这2个参数，由于大小比较后
         * 可能全是链表1或者链表2
         * 将第一个值设成较小结点(头节点)的下一个结点
         * 后面设置成
         * *    如果这个结点等于头结点说明，上一次递归的head.next=它
         * *    也就是上一次递归的head.next的值可能是小值，
         * *    递归去的时候是找大值的，所以让它等于list2
         * *    也就是上次递归可能大的值
         * *    这个也可能是2个值相等，不过无所谓了，选可能大的就好了
         */
        head.next = mergeTwoLists03(head.next, head == list1 ? list2 : list1);
        return head;
    }
}
