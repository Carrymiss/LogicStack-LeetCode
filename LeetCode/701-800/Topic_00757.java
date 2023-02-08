package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/8
 * @Time: 9:28
 * @Description: 题目757 ：设置交集大小至少为2
 */
public class Topic_00757 {
    /**
     * 方法一：贪心
     */
    public int intersectionSizeTwo(int[][] intervals) {
        // 从小到大排序
        // 如果第二个元素相等就按第一个元素的大小，从小到大排序
        Arrays.sort(intervals, (a, b) -> {
            return a[1] != b[1] ? a[1] - b[1] : b[0] - a[0];
        });
        int ans = 2;
        // 次大值和最大值
        int a = intervals[0][1] - 1;
        int max = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 非交集
            if (max < intervals[i][0]) {
                ans += 2;
                max = intervals[i][1];
                a = intervals[i][1] - 1;
            } else if (intervals[i][0] > a) {
                ans += 1;
                a = max;
                max = max = intervals[i][1];
            }
        }
        return ans;
    }
}
