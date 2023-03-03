package practise;

/**
 * @Author: ZTY
 * @Create: 2023/3/3
 * @Time: 10:29
 * @Description: 题目552：学生出勤记录 II
 */
public class Topic_00552 {
    /**
     * 方法1：动态规划
     */
    public int checkRecord(int n) {
        final int MOD = 1000000007;
        // 天数，A 的数量，结尾连续 L 的数量
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 以 P 结尾的数量
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 以 A 结尾的数量
            for (int k = 0; k < 3; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 以 L 结尾的数量
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }

        int sum = 0;
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 3; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }
}
