package practise;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ZTY
 * @Create: 2023/2/10
 * @Time: 12:46
 * @Description: 题目781 ：森林中的兔子
 */
public class Topic_00781 {
    /**
     * 方法一：贪心
     */
    public int numRabbits01(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        // 将值放入map中
        for (int a : answers) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        AtomicInteger ans = new AtomicInteger();
        map.forEach((key, value) -> {
            if (key != 0) {
                if (key + 1 < value) {
                    int tmp = value % (key + 1) == 0 ? value / (key + 1) : value / (key + 1) + 1;
                    ans.addAndGet(tmp * (key + 1));
                } else {
                    ans.addAndGet(key + 1);
                }
            } else {
                ans.addAndGet(value);
            }
        });
        return ans.get();
    }

    /**
     * 方法二：贪心
     */
    public int numRabbits02(int[] answers) {
        int[] ans = new int[1000];
        int sum = 0;
        for (int answer : answers) {
            if (ans[answer] == 0) {
                sum += answer + 1;
                ans[answer] = answer;
            } else {
                ans[answer]--;
            }
        }
        return sum;
    }
}
