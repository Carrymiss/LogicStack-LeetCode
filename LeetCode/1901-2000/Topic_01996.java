package practise;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/9
 * @Time: 9:21
 * @Description: 题目1996 ：游戏中弱角色的数量
 */
public class Topic_01996 {
    /**
     * 方法一：贪心
     */
    public int numberOfWeakCharacters(int[][] properties) {
        // 攻击降，防御升
        Arrays.sort(properties, (a, b) -> {
            return a[0] != b[0] ? b[0] - a[0] : a[1] - b[1];
        });
        int ans = 0;
        int maxDefense = -1; //保存当前位置之前的最大防御力
        for (int[] p : properties) {
            if (maxDefense > p[1]) {
                ans++;
            } else {
                maxDefense = p[1];
            }
        }
        return ans;
    }
}
