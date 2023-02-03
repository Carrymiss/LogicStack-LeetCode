package practise;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: ZTY
 * @Create: 2023/2/3
 * @Time: 9:27
 * @Description: 题目768 ：最多能完成排序的块 II
 */
public class Topic_00768 {
    /**
     * 方法1 ：贪心
     */
    public int maxChunksToSorted01(int[] arr) {
        // 克隆的升序数组
        int[] clone = arr.clone();
        Arrays.sort(clone);
        // 使用map比较效率更高
        HashMap<Integer, Integer> map = new HashMap<>();
        // 定义长度和最多块数
        int length = arr.length, count = 0;

        // 词频比较
        // 一方加，一方减，如果词频为0就是一个最优子集
        for (int i = 0, frequency = 0; i < length; ++i) {
            int a = arr[i], b = clone[i];
            if (map.getOrDefault(a, 0) == -1) {
                frequency--;
            } else if (map.getOrDefault(a, 0) == 0) {
                frequency++;
            }
            map.put(a, map.getOrDefault(a, 0) + 1);

            if (map.getOrDefault(b, 0) == 1) {
                frequency--;
            } else if (map.getOrDefault(b, 0) == 0) {
                frequency++;
            }
            map.put(b, map.getOrDefault(b, 0) - 1);
            // 找到最优子集，块数加一
            if (frequency == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * 方法2 ：贪心
     */
    public int maxChunksToSorted02(int[] arr) {
        int ans = 1;
        int max = -1;
        int[] helpArr = new int[arr.length];
//        Stack<Integer> stack = new Stack<>();
        int min = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            min = Math.min(min, arr[i]);
            helpArr[i] = min;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, arr[i]);
            if (max <= helpArr[i + 1]) {
                ans++;
            }
        }
        return ans;
    }
}
