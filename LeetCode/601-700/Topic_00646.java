package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/9
 * @Time: 8:49
 * @Description: 题目646：最长数对链
 */
public class Topic_00646 {
    /**
     * 方法一: 排序+贪心
     */
    public int findLongestChain(int[][] pairs) {
        // 按第一个元素，从小到大排序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int max = pairs[0][1], ans = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > max) {
                ans++;
                max = pairs[i][1];
            }
            max = Math.min(max, pairs[i][1]);
        }
        return ans;
    }


}
