package practise.upload;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/20
 * @Time: 9:20
 * @Description: 题目1833：雪糕的最大数量
 */
public class Topic_practise_01833 {
    /**
     * 方法1：计数+贪心
     */
    public int maxIceCream01(int[] costs, int coins) {
        int length = costs.length;
        int[] cost = new int[10000 + 1];
        int ans = 0;
        for (int a : costs) {
            cost[a]++;
        }
        for (int i = 1; i < cost.length; i++) {
            if (coins - i < 0) {
                return ans;
            } else if (coins - i * cost[i] < 0) {
                ans += coins / i;
                return ans;
            }
            coins -= i * cost[i];
            ans += cost[i];
        }
        return ans;
    }

    /**
     * 方法2：贪心
     */
    public int maxIceCream02(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0, length = costs.length;
        for (int i = 0; i < length; i++) {
            int cost = costs[i];
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * 方法3：优化方法1
     */
    public int maxIceCream03(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i < freq.length; i++) {
            if (coins >= i) {
                /*
                  这里面有两种情况：
                   1.全取
                   2.取部分值
                 */
                // 关键代码
                int curCount = Math.min(freq[i], coins / i);

                coins -= i * curCount;
                count += curCount;
            } else {
                break;
            }
        }
        return count;
    }

}
