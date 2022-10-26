package leetcode_BasicAlgorithm.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZTY
 * @Create: 2022-10-25
 * @Time: 8:48
 * @Description: 题目198 ：打家劫舍
 */
public class Topic_00198 {
    /**
     * 方法1 ：动态规划DP
     * <p>思路：
     * <p> 数组中的值表示的是存放的金额，小偷可以选择偷和不偷，
     * <p> 如果前一个偷了，那么下一个肯定是不能偷的，
     * <p> 因为相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p> 如果上一个没偷，那么下一个可以选择偷也可以选择不偷，视情况而定。
     *
     * <p>  这里可以定义一个二维数组dp[length][2]，
     * <p>  其中dp[i][0]表示第i+1（因为数组下标是从0开始的，所以这里是i+1）
     * <p>  家偷了的最大总金额，dp[i][1]表示的是第i+1家没偷的最大总金额。
     * <p>  那么我们找出递推公式：
     * <p>       1，dp[i][0]=max(dp[i-1][0],dp[i-1][1])
     * <p>           他表示如果第i+1家没偷，那么第i家有没有偷都是可以的，
     * <p>           我们取最大值即可。
     * <p>       2，dp[i][1]=dp[i-1][0]+nums[i]
     * <p>           他表示的是如果第i+1家偷了，那么第i家必须没偷，
     * <p>           这里nums[i]表示的是第i+1家偷的金额。
     * <p>  递推公式找出来之后我们再来看下边界条件，
     * <p>      第一家可以选择偷，也可以选择不偷，所以
     * <p>          1.dp[0][0]=0，第一家没偷
     * <p>          2.dp[0][1]=nums[0]，第一家偷了
     */
    public int rob01(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][2];
        // 设定边界条件 0表示没偷 1表示偷了
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            // 这家没偷 说明前面可能偷了，可能没偷 取最大值就行
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            // 这家偷了 说明前面没有偷
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        // 最后一家 可能偷了 也可能没偷 取最大值即可
        return Math.max(dp[len - 1][1], dp[len - 1][0]);
    }

    /**
     * 方法2 : 优化上面的DP
     * <p>  思路：
     * <p>      上面定义了一个二维数组，但每次计算的时候都只是用二维数组的前一对值，
     * <p>      在往前面的就永远使用不到了，
     * <p>      这样就会造成巨大的空间浪费，所以我们可以定义两个变量来解决
     */
    public int rob02(int[] nums) {
        int len = nums.length;
        int dp0 = 0;
        int dp1 = nums[0];
        for (int i = 1; i < len; i++) {
            int temp = Math.max(dp0, dp1);
            // 这家偷了 说明上家没偷
            // 这里设计还是很巧妙的，此时的dp0还是上家的数值
            dp1 = dp0 + nums[i];
            // 这家没偷 说明上家可偷可不偷 取最大值
            dp0 = temp;
        }
        return Math.max(dp0, dp1);
    }

    /**
     * 方法3 ：递归
     */
    public int rob03(int[] nums) {
        return robHelper03(nums, nums.length - 1);
    }

    private int robHelper03(int[] nums, int i) {
        // 终止条件
        if (i < 0) {
            return 0;
        }
        //偷上上家之前所能得到的最大值
        int lastLast = robHelper03(nums, i - 2);
        //偷上家之前所能得到的最大值
        int last = robHelper03(nums, i - 1);
        //偷上上家之前的还可以再偷当前这一家
        int cur = lastLast + nums[i];
        //然后返回偷当前这一家和不偷当前这一家的最大值
        return Math.max(cur, last);
    }

    /**
     * 方法4 ：优化上面的递归
     * <p> 上面的递归运行超时 存在大量重复运算
     * <p>      如果非要使用递归的方式解决，可以把计算的值先存起来，
     * <p>      下次用的时候如果有就直接去取，如果没有，再计算。
     */
    public int rob04(int[] nums) {
        return robHelper04(nums, nums.length - 1, new HashMap<>());
    }

    private int robHelper04(int[] nums, int i, Map<Integer, Integer> map) {
        // 终止条件
        if (i < 0) {
            return 0;
        }
        // 偷上上家的数额
        int lastLast = 0;
        // 偷上家的数额
        int last = 0;

        // 查看map中是否存在，如果存在就从map中取，不用再计算了
        if (map.containsKey(i - 2)) {
            lastLast = map.get(i - 2);
        } else {
            // 偷上上家之前所能得到的最大值
            lastLast = robHelper04(nums, i - 2, map);
            // 如果map中不存在就计算，计算完之后要存储在map中，下次用的
            // 时候直接从map中取，不用再计算了。
            map.put(i - 2, lastLast);
        }

        // 原理同上
        if (map.containsKey(i - 1)) {
            last = map.get(i - 1);
        } else {
            // 偷上家之前所能得到的最大值
            last = robHelper04(nums, i - 1, map);
            // 如果map中不存在就计算，计算完之后要存储在map中，下次用的
            // 时候直接从map中取，不用再计算了。
            map.put(i - 1, last);
        }

        // 偷上上家之前的还可以再偷当前这一家
        int cur = lastLast + nums[i];
        // 然后返回偷当前这一家和不偷当前这一家的最大值
        return Math.max(last, cur);
    }
}
