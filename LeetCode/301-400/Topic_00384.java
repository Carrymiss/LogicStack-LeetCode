package leetcode_BasicAlgorithm.design;

import java.util.Random;

/**
 * @Author: ZTY
 * @Create: 2022-10-26
 * @Time: 20:24
 * @Description: 题目384 ：打乱数组
 */
public class Topic_00384 {
    /**
     *  KnuthKnuth 洗牌算法
     */
    private int[] nums;
    private Random random;

    public Topic_00384(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    //重置数组，就是返回之前的数组
    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        // 克隆数组
        int[] clone = nums.clone();
        for (int j = 1; j < nums.length; j++) {
            // j+1 是因为nextInt，随机范围是左闭右开 [0，j+1}
            int i = random.nextInt(j + 1);
            swap(clone, i, j);
        }
        return clone;
    }

    private void swap(int[] a, int i, int j) {
        if (i != j) {
            a[i] = a[i] + a[j];
            a[j] = a[i] - a[j];
            // 此时a[j]是之前的a[i]
            a[i] = a[i] - a[j];
        }
    }

    //交换两个数字的值
//        private void swap(int[] a, int i, int j) {
//            if (i != j) {
//                a[i] ^= a[j];
//                a[j] ^= a[i];
//                a[i] ^= a[j];
//            }
//        }
}
