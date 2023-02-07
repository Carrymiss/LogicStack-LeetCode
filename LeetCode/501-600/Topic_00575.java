package practise;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2023/2/7
 * @Time: 10:48
 * @Description: 题目575 ：分糖果
 */
public class Topic_00575 {
    /**
     * 方法一：使用Set
     */
    public int distributeCandies01(int[] candyType) {
        Set a = new HashSet();
        int n = candyType.length;
        for (int i = 0; i < n; i++) {
            a.add(candyType[i]);
        }
        return Math.min(n / 2, a.size());
    }

    /**
     * 方法二：使用计数
     */
    public int distributeCandies02(int[] candyType) {
        int n = candyType.length;
        int half = n / 2;
        byte[] status = new byte[200001];
        for (int type : candyType) {
            n -= status[type + 100000];
            status[type + 100000] = 1;
        }
        return Math.min(half, n);
    }
}
