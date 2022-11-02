package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-02
 * @Time: 9:39
 * @Description: 题目213 ：打家劫舍 II
 */
public class Topic_00213 {
    /**
     * 方法1：动态规划(DP)
     */
    public int rob01(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // 可以偷第一家，但是不能偷最后一家
        int robFirst = robHelper01(nums, 0, len - 2);
        // 可以偷最后一家，但是不能偷第一家
        int robLast = robHelper01(nums, 1, len - 1);
        // 选择上面两个中的最大值
        return Math.max(robFirst, robLast);
    }

    public int robHelper01(int[] nums, int start, int end) {
        // 选择偷的值
        int steal = 0;
        // 选择不偷的值
        int noSteal = 0;
        for (int i = start; i <= end; i++) {
            // 记录上一户偷的钱数
            int temp = steal;
            // 如果这户选择偷 说明上一户一定没有偷
            // 这户偷的值就是 上一户没有偷的值加上这户的金额
            steal = noSteal + nums[i];
            // 如果这户选择不偷 说明上户 可偷 可不偷 取最大值即可
            // 此时的temp 是上一户偷的值
            noSteal = Math.max(noSteal, temp);
        }
        return Math.max(steal, noSteal);
    }




}
