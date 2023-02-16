package practise;

import java.util.HashMap;

/**
 * @Author: ZTY
 * @Create: 2023/2/16
 * @Time: 9:25
 * @Description: 题目1218 ：
 */
public class Topic_01218 {
    /**
     * 题目1：贪心+序列DP
     */
    public int longestSubsequence01(int[] arr, int difference) {
        int length = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[][] a = new int[length][2];
        // 初始化默认值是0
        a[0][1] = 1;
        map.put(arr[0], 0);
        for (int i = 1; i < length; i++) {
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);
            a[i][1] = 1;
            int prve = arr[i] - difference;
            if (map.containsKey(prve)) {
                a[i][1] = Math.max(a[i][1], a[map.get(prve)][1] + 1);
            }
            map.put(arr[i], i);
        }
        return Math.max(a[length - 1][0], a[length - 1][1]);
    }

    /**
     * 方法二 ：优化方法1
     */
    public int longestSubsequence02(int[] arr, int difference) {
        int length = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int a : arr) {
            map.put(a, map.getOrDefault(a - difference, 0) + 1);
            ans = Math.max(ans, map.get(a));
        }
        return ans;
    }
}
