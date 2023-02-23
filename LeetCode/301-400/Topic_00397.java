package practise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZTY
 * @Create: 2023/2/23
 * @Time: 9:22
 * @Description: 题目397：整数替换
 */
public class Topic_00397 {
    /**
     * 方法1：递归枚举
     */
    public int integerReplacement01(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement01(n / 2);
        }
        return 2 + Math.min(integerReplacement01(n / 2 + 1), integerReplacement01(n / 2));
    }

    /**
     * 方法二：递归+记忆化搜索
     */
    Map<Integer, Integer> map = new HashMap<>();

    public int integerReplacement02(int n) {
        if (n == 1) {
            return 0;
        }
        if (!map.containsKey(n)) {
            if (n % 2 == 0) {
                map.put(n, 1 + integerReplacement02(n / 2));
            } else {
                map.put(n, 2 + Math.min(integerReplacement02(n / 2 + 1), integerReplacement02(n / 2)));
            }
        }
        return map.get(n);
    }

    /**
     * 方法三：贪心
     */
    public int integerReplacement03(int a) {
        long n = a;
        int ans = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n >>= 1;
            } else {
                /*
                    (n >> 1) & 1 处理次低位
                    是因为偶数只需要处理一次(除以2)
                    奇数需要处理2次(变成偶数再除以2)
                    为了得到最小替换次数，尽可能的让n每次处理过都是偶数
                    也就是
                    01(二进制)这种情况我们就减1
                    00(二进制)这种情况我们就加1
                    让n的二进制，尽可能的出现0
                 */
                if (n != 3 && ((n >> 1) & 1) == 1) {
                    n++;
                } else {
                    n--;
                }
            }
            ans++;
        }
        return ans;
    }
}
