package leetcode_BasicAlgorithm.design;

import practise.ListNode1;
import practise.StackNode1;

import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-26
 * @Time: 20:57
 * @Description: 题目155 ： 最小栈
 */
public class Topic_00155 {
    /**
     * 方法 1 ：使用辅助类解决
     * <p> 使用：ListNode1
     */
    //链表头，相当于栈顶
    private ListNode1 head;

    // 无参构造函数
    /*public MinStack01() {

    }*/

    // 判断栈是否为空
    private boolean empty01() {
        return head == null;
    }

    //压栈，需要判断栈是否为空
    public void push01(int val) {
        if (empty01()) {
            // 压栈 让结点指向空
            head = new ListNode1(val, val, null);
        } else {
            head = new ListNode1(val, Math.min(head.min, val), head);
        }
    }

    // 出栈，相当于把链表头删除
    public void pop01() {
        if (empty01()) {
            throw new IllegalStateException("栈为空……");
        }
        head = head.next;
    }

    //栈顶的值也就是链表头的值
    public int top01() {
        if (empty01()) {
            throw new IllegalStateException("栈为空……");
        }
        return head.val;
    }

    // 链表中头结点保存的是整个链表最小的值，所以返回head.min也就是
    // 相当于返回栈中最小的值
    public int getMin01() {
        if (empty01()) {
            throw new IllegalStateException("栈为空……");
        }
        return head.min;
    }

    /**
     * 方法 2 ：使用辅助类解决
     * <p> 使用：官方提供的栈
     */
    private Stack<StackNode1> stack = new Stack<>();

    // 判断栈是否为空
    private boolean empty02() {
        return stack.isEmpty();
    }

    // 压栈
    public void push02(int val) {
        if (empty02()) {
            stack.push(new StackNode1(val, val));
        } else {
            stack.push(new StackNode1(val, Math.min(val, getMin02())));
        }
    }

    // 入栈
    public void pop02() {
        if (empty02()) {
            throw new IllegalStateException("栈为空……");
        }
        stack.pop();
    }

    // 栈顶的值也就是链表头的值
    public int top02() {
        if (empty02()) {
            throw new IllegalStateException("栈为空……");
        }
        return stack.peek().val;
    }


    // 返回栈中最小的值
    public int getMin02() {
        if (empty02()) {
            throw new IllegalStateException("栈为空……");
        }
        return stack.peek().min;
    }


    /**
     * 方法 3 ：使用单个栈解决
     * <p> 思路：
     * <p>  1.利用pop方法，只要执行就出栈
     * <p>  2.入栈时候比较大小 放入此时的最大值，并放入新元素
     * <p>    (也就是只要新元素没旧元素大，把之前的值压栈，再把新值压栈。更新最小值min)
     * <p>    如果新元素比旧元素大，直接压栈新元素
     * <p>  3.上面设计的巧妙处来了。主要体现在出栈：
     * <p>     出栈的时候，如果出栈的元素是此时的最小值，
     * <p>     利用pop方法出栈一个之前的最大值(这里不明白画图直接秒懂)给min赋值
     */
    // 这里设置成最大值 看了我上面的写的思路应该很清晰,题目给的区间就是这个最大值
    int min01 = Integer.MAX_VALUE;
    Stack<Integer> stack01 = new Stack<>();

    public void push03(int val) {
        //如果加入的值小于最小值，要更新最小值
        if (val <= min01) {
            stack01.push(min01);
            min01 = val;
        }
        stack01.push(val);
    }

    // 这个太巧妙了
    public void pop03() {
        //如果把最小值出栈了，就更新最小值
        // 只要这个if执行，就已经完成了出栈
        if (stack01.pop() == min01) {
            min01 = stack01.pop();
        }
    }

    public int top03() {
        return stack01.peek();
    }

    public int getMin03() {
        return min01;
    }

    /**
     * 方法4 ： 优化方法3的单个栈解决
     * <p> 思路：上面如果是新元素一直是小值，需要不停的压栈min，
     * <p>      直接简化这一流程 通过减法 比如a+x=b 我们只要知道a，b就能通过b-a算出x
     * <p>      b即使新元素的值，a就是min最小值，x就是我们压入栈中值
     */
    long min02;
    Stack<Long> stack02 = new Stack<>();

    public void push04(int val) {
        // 当第一次压栈时 压入0
        if (stack02.isEmpty()) {
            stack02.push(0L);
            // 第一次压栈 给min赋初始值
            min02 = val;
        } else {
            //这里入栈的是入栈的值和最小值的差值，有可能为负，也有可能为正。
            stack02.push(val - min02);
            if (val < min02) {
                min02 = val;
            }
        }
    }

    public void pop04() {
        if (stack02.isEmpty()) {
            throw new IllegalStateException("栈为空……");
        }
        // 这个出栈的值就是上面说的差值X
        long pop = stack02.pop();
        // 因为入栈的是差值，当出栈的为负数的时候，说明栈中最小值已经出栈了，
        // 这里要重新更新最小值
        if (pop < 0) {
            min02 -= pop;
        }
    }

    public int top04() {
        if (stack02.peek() > 0) {
            //栈顶元素如果是正的，说明栈顶元素压栈的时候是比栈中最小值大的，根据
            //top=x - min，可以计算x=top+min
            return (int) (stack02.peek() + min02);
        } else {
            //当栈顶元素是负数的时候，说明栈顶元素压栈的时候是比栈中最小值小的，
            //而压栈完之后他会更新最小值min，所以如果在使用上面公式肯定是不行
            //的。如果栈顶元素压栈的时候比最小值小，他会更新最小值，这个最小值
            //就是我们要压栈的值，所以这里直接返回min就行了。
            return (int) min02;
        }
    }

    public int getMin04() {
        return (int) min02;
    }

    /**
     * 方法5 ：采用双栈
     * 一个栈存元素，一个栈存最小值
     */
    //栈1存放的是需要压栈的值
    Stack<Integer> stack03 = new Stack<>();
    //栈2存放的是最小值
    Stack<Integer> stack04 = new Stack<>();

    public void push05(int val) {
        stack03.push(val);
        // 这里等于也需要放进栈里 因为元素相同的情况下，出栈时候出去一个 最小值应该还是不变的
        // 这里为了pop方便判断 所以设计成等于
        if (stack04.isEmpty() || val <= getMin05()) {
            stack04.push(val);
        }
    }

    public void pop05() {
        if (stack03.pop() == getMin05()) {
            stack04.pop();
        }
    }

    public int top05() {
        return stack03.peek();
    }

    public int getMin05() {
        return stack04.peek();
    }
}
