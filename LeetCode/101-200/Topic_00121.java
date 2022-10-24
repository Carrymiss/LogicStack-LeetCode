package leetcode_BasicAlgorithm.dynamicProgramming;

import java.util.Stack;

/**
 * @Author: ZTY
 * @Create: 2022-10-23
 * @Time: 11:05
 * @Description: 题目121 ：买卖股票的最佳时机
 */
public class Topic_00121 {
    /**
     * 方法1 ：暴力求解
     */
    public int maxProfit01(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int maxPrice = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxPrice = Math.max(maxPrice, prices[j] - prices[i]);
            }
        }
        return maxPrice;
    }

    /**
     * 方法2 ：动态规划 DP
     * <p>思路：
     * <p>     动态规划还是那常见的几个步骤
     * <p>         1.确定状态
     * <p>         2.找到转移公式
     * <p>         3.确定初始条件以及边界条件
     * <p>         4.计算结果
     * <p>
     * <p>     步骤一：
     * <p>        我们来定义一个二维数组dp[length][2]，
     * <p>        其中dp[i][0]表示第i+1天（i是从0开始的）结束的时候没持有股票的最大利润，
     * <p>        dp[i][1]表示第i+1天结束的时候持有股票的最大利润。
     * <p>     步骤二：
     * <p>        如果我们要求第i+1天结束的时候没持有股票的最大利润dp[i][0]，
     * <p>        那么会有两种情况：
     * <p>             1.第i+1天我们即没买也没卖，
     * <p>               那么最大利润就是第i天没持有股票的最大利润dp[i-1][0]
     * <p>             2.第i+1天我们卖了一支股票，
     * <p>               那么最大利润就是第i天持有股票的最大利润
     * <p>              （这个是负的，并且也不一定是第i天开始持有的，有可能在第i天之前就已经持有了）
     * <p>               加上第i+1天卖出股票的最大利润，dp[i-1][1]+prices[i]。
     */
    public int maxProfit02(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        int[][] dp = new int[len][2];
        // 边界条件
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            //递推公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        // 最后肯定是手里没持有股票利润才会最大，也就是卖出去了
        return dp[len - 1][0];
    }

    /**
     * 方法3 ：优化上面的DP
     * <p>  思路：
     * <p>      上面二维数组中计算当天的最大利润只和前一天的利润有关，
     * <p>      所以没必要使用二维数组，只需要使用两个变量即可，
     * <p>          一个表示当天持有股票的最大利润
     * <p>          一个表示当天没持有股票的最大利润
     */
    public int maxProfit03(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        // 没持有股票
        int noHold = 0;
        // 持有股票
        int hold = -prices[0];
        for (int i = 1; i < len; i++) {
            //递推公式
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, -prices[i]);
        }
        // 最后肯定是手里没持有股票利润才会最大，也就是卖出去了
        return noHold;
    }

    /**
     * 方法4 ：双指针
     * <p> 第一个指针：记录最小值
     * <p> 第二个指针：记录最大值
     * <p> 差值最大，就是最大利润
     */
    public int maxProfit04(int[] prices) {
        //记录最大利润
        int maxPrice = 0;
        //记录数组中访问过的最小值
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            // 此时卖出的最大利润
            maxPrice = Math.max(prices[i] - min, maxPrice);
        }
        return maxPrice;
    }

    /**
     * 方法5 ：栈
     * <p> 这个方法其实是上面双指针的变种方法
     * <p>     要始终保持栈顶元素是所访问过的元素中最小的，
     * <p>     如果当前元素小于栈顶元素，就让栈顶元素出栈，让当前元素入栈。
     * <p>     如果访问的元素大于栈顶元素，就要计算他和栈顶元素的差值，我们记录最大的即可
     */
    public int maxProfit05(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        int maxPrice = 0;
        for (int i = 1; i < len; i++) {
            //如果栈顶元素大于prices[i]，那么栈顶元素出栈，
            //把prices[i]压栈，要始终保证栈顶元素是最小的
            // peek()方法：查看此堆栈顶部的对象，而不将其从堆栈中移除。
            if (stack.peek() > prices[i]) {
                stack.pop();
                stack.push(prices[i]);
            } else {
                //否则如果栈顶元素不大于prices[i]，就要计算
                //prices[i]和栈顶元素的差值
                maxPrice = Math.max(maxPrice, prices[i] - stack.peek());
            }
        }
        return maxPrice;
    }

    /**
     * 方法6：参照最大子序和
     * <p>思路：
     * <p>  假设数组的值是[a,b,c,d,e,f]，我们用数组的前一个值减去后一个值，
     * <p>  得到的新数组如下:
     * <p>       [b-a,c-b,d-c,e-d,f-e]
     * <p>  我们在新数组中随便找几个连续的数字相加就会发现一个规律，
     * <p>  就是中间的数字都可以约掉，比如新数组中第1个到第4个数字的和是
     * <p>       b-a+c-b+d-c+e-d=e-a
     */
    public int maxProfit06(int[] prices) {
        int len = prices.length;
        if (len == 1) {
            return 0;
        }
        int cur = 0, maxPrice = 0;
        for (int i = 1; i < len; i++) {
            cur = Math.max(cur, 0) + prices[i] - prices[i - 1];
            //记录最大值
            maxPrice = Math.max(cur, maxPrice);
        }
        return maxPrice;
    }
}
