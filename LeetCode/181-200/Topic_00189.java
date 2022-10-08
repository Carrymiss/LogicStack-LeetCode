package practise;

import java.util.Arrays;

/**
 * @Author: LQ
 * @Create: 2022-04-04
 * @Time: 18:43
 * @Description: 题目189 ：轮转数组
 */
public class Topic_00189 {
    /**
     * 方法1：使用临时数组
     */
    public void rotate01(int[] nums, int k) {
        int len = nums.length;
        // 给k值取模
        k %= len;
        if (len == 1 || k == 0) {
            return;
        }
        // 把原数组克隆到临时数组
        int[] temp = Arrays.copyOf(nums, len);
        for (int i = 0; i < len; i++) {
            nums[(i + k) % len] = temp[i];
        }
    }

    /**
     * 方法2：三次反转
     * 第一步：全反转
     * 第二步：反转0到k-1
     * 第三步：发转k到len-1
     */
    public void rotate02(int[] nums, int k) {
        int len = nums.length;
        // 给k值取模
        k %= len;
        if (len == 1 || k == 0) {
            return;
        }
        // 进行三次反转
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (end > start) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
            /**
             * 也可以直接写成
             * nums[start++] = nums[end];
             * nums[end--] = temp;
             */
        }
    }

    /**
     * 方法3：环形旋转
     * 注意 如果数组长度len是k的倍数，
     * 会出现原地打转现象
     */
    public void rotate03(int[] nums, int k) {
        int len = nums.length;
        // 给k值取模
        k %= len;
        if (len == 1 || k == 0) {
            return;
        }
        int strat = 0;
        for (int i = 0; i < len; ) {
            int pointer = strat;
            int pre = nums[strat];
            do {
                /**
                 * 主要做两件事
                 *      1. 赋值 1->2->3->1
                 *      2. 将指针指向修改的索引
                 */
                int next = (pointer + k) % len;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                pointer = next;
                i++;
            } while (strat != pointer); // 原地打转就从数组的下一个值循环
            strat++;
        }
    }
}

