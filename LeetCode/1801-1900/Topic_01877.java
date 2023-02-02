package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/2
 * @Time: 9:08
 * @Description: 题目1877 ：数组中最大数对和的最小值
 */
public class Topic_01877 {
    /**
     * 方法1：模拟
     */
    public int minPairSum(int[] nums) {
        int length = nums.length;
        int max=0;
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < length/2; i++) {
            max=Math.max(nums[i]+nums[length-i-1],max);
        }
        return max;
    }

}
