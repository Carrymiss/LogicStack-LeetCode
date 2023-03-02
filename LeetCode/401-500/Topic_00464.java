package practise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZTY
 * @Create: 2023/3/2
 * @Time: 9:04
 * @Description: 题目464：我能赢吗
 */
public class Topic_00464 {
    /**
     * 方法一：记忆化搜索+状态压缩
     * <p>Sprague-Grundy定理
     * <p>https://zhuanlan.zhihu.com/p/20611132
     */
    int n, t;
    int[] f = new int[1 << 20];

    public boolean canIWin(int _n, int _t) {
        n = _n;
        t = _t;
        /*
            判断边界条件
                如果desiredTotal比总和还大说明没有人赢，直接输出false
         */
        if ((1 + _n) * _n / 2 < _t) {
            return false;
        }
        if (t == 0) return true;
        /*
            1 true
            -1 false
         */
        return dfs(0, 0) == 1;
    }

    public int dfs(int state, int tot) {
        /*
            遇到已经用过的数直接输出
         */
        if (f[state] != 0) {
            return f[state];
        }
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1){
                continue;
            } else if (tot + i + 1 >= t) {
                return f[state] = 1;
            }
            else if (dfs(state | (1 << i), tot + i + 1) == -1) {
                return f[state] = 1;
            }
        }
        /*
            将用过的数赋值
         */
        return f[state] = -1;
    }
}
