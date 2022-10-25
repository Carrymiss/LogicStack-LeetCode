package leetcode_BasicAlgorithm.dynamicProgramming;

/**
 * @Author: ZTY
 * @Create: 2022-10-23
 * @Time: 11:05
 * @Description: 题目53 ：最大子序和
 */
public class Topic_00053 {
    /**
     * 方法1 ： 通过规律解题
     * <p>参考P121题的方法6
     */
    public int maxSubArray01(int[] nums) {
        int cur = nums[0], max = cur;
        for (int i = 1; i < nums.length; i++) {
            /**
             * <p> 这里使用0和cur比较： 如果是元素都是负数。那么最大值就是元素本身，
             * <p> 也就是最大值就是最大的负数，
             * <p> 如果出现一个正数，那么最大值就是这个正数
             */
            cur = Math.max(cur, 0) + nums[i];
            max = Math.max(max, cur);
        }
        return max;
    }

    /**
     * 方法2 ：动态规划DP
     * <p> 动态规划的几个步骤
     * <p>     1，确定状态
     * <p>     2，找到转移公式
     * <p>     3，确定初始条件以及边界条件
     * <p>     4，计算结果。
     * <p>  最后一个不用看，只看前3个就行，因为前3个一旦确定，最后一个结果也就出来了
     *
     * <p>     1，定义dp[i]表示数组中下标i为右端点的连续子数组的最大和。（可以理解为滑动窗口）
     * <p>     2，如果要计算下标i为右端点的连续子数组的最大和，
     * <p>             也就是计算dp[i]，只需要判断dp[i-1]是大于0还是小于0。
     * <p>             如果dp[i-1]大于0，就继续累加，dp[i]=dp[i-1]+num[i]。
     * <p>             如果dp[i-1]小于0，我们直接把前面的舍弃，
     * <p>             也就是说重新开始计算，否则会越加越小的，直接让dp[i]=num[i]。
     * <p>          所以转移公式如下:
     * <p>                 dp[i]=num[i]+max(dp[i-1],0);
     * <p>     3，边界条件判断，当i等于0的时候，也就是前1个元素，他能构成的最大和也就是他自己，所以
     * <p>          dp[0]=num[0];
     */
    public int maxSubArray02(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        // 确定边界条件
        dp[0] = nums[0];
        // 当第一个值就是最大的，需要给max赋值
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            // 计算此时窗口的最大值
            dp[i] = Math.max(dp[i - 1], 0) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
