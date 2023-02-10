package practise;

/**
 * @Author: ZTY
 * @Create: 2023/2/10
 * @Time: 14:13
 * @Description: 题目807 ：保持城市天际线
 */
public class Topic_00807 {
    /**
     * 方法一：贪心
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int length = grid.length;
        int[] rowMax = new int[length];
        int[] colMax = new int[length];
        // 记录最大值
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }
}
