package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 9:51
 * @Description: 题目122 ：买卖股票的最佳时机 II
 */
public class Topic_00122 {
    // 方法1：贪心算法
    public int maxProfit_01(int[] prices) {
        int len = prices.length,
                res = 0;
        if (len == 1) {
            return 0;
        }
        for (int i = 0; i < len - 1; i++) {
            prices[i] = prices[i + 1] - prices[i];
            if (prices[i] > 0) {
                res = res + prices[i];
            }
        }
        return res;
    }

    // 方法2：动态规划
    public int maxProfit_02(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        /**
         * 递推公式
         *  1.当天交易完之后手里没有股票可能有两种情况：
         *      一种是当天没有进行任何交易，
         *          又因为当天手里没有股票，所以当天没有股票的利润只能取前一天手里没有股票的利润
         *      一种是把当天手里的股票给卖了，
         *          既然能卖，说明手里是有股票的，所以这个时候当天没有股票的利润要取前一天手里有股票的利润加上当天股票能卖的价格。
         *  dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);
         *
         *  2.当天交易完之后手里持有股票也有两种情况:
         *      一种是当天没有任何交易,
         *          又因为当天手里持有股票，所以当天手里持有的股票其实前一天就已经持有了
         *      一种是当天买入了股票,
         *          当天能买股票，说明前一天手里肯定是没有股票的
         *  dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i]);
         */
        int[][] dp = new int[len][2];
        /**
         * 第一天有2种情况：
         *      1.买了股票，
         *          花钱了所以利润为负
         *      2.没买股票
         */
        dp[0][1] = -prices[0];
        dp[0][0] = 0;

        for (int i = 1; i < len; i++) {
            // 递推公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 最后一天肯定是手里没有股票的时候，利润才会最大
        return dp[len - 1][0];
    }

    // 方法3：优化上面的动态规划
    public int maxProfit_03(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        /**
         * 优化思路：
         *      因为递推公式里面只需要前一天的数组值，可以用一个变量取代
         */
        int hold=-prices[0];
        int nohold=0;

        for (int i = 1; i < len; i++) {
            // 递推公式
            // 没持有股票
            nohold = Math.max(nohold, hold + prices[i]);
            // 持有股票
            hold = Math.max(hold, nohold - prices[i]);
        }
        // 最后一天肯定是手里没有股票的时候，利润才会最大
        return nohold;
    }
}
