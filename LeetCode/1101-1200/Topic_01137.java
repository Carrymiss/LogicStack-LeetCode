package practise;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ZTY
 * @Create: 2023/3/3
 * @Time: 9:46
 * @Description: 题目1137：第 N 个泰波那契数
 */
public class Topic_01137 {
    /**
     * 方法一：滚动数组
     */
    public int tribonacci01(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        // 初始化滚动数组元素
        int a = 0, b = 0, c = 1, res = 1;
        for (int i = 3; i <= n; i++) {
            a = b;
            b = c;
            c = res;
            res = a + b + c;
        }
        return res;
    }

    /**
     * 方法二：面向结果编程
     */
    public int tribonacci02(int n) {
        int[] res = {0, 1, 1, 2, 4, 7, 13, 24, 44, 81,
                149, 274, 504, 927, 1705, 3136, 5768, 10609, 19513, 35890,
                66012, 121415, 223317, 410744, 755476, 1389537, 2555757, 4700770, 8646064, 15902591,
                29249425, 53798080, 98950096, 181997601, 334745777, 615693474, 1132436852, 2082876103};
        return res[n];
    }

    /**
     * 方法三：递归+记忆化
     */
    int[] cache = new int[40];

    public int tribonacci03(int n) {
        // 这三个if都是终止条件
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        // 记忆化
        if (cache[n] != 0) return cache[n];
        // 如果没有终止就进行递归
        cache[n] = tribonacci03(n - 1) + tribonacci03(n - 2) + tribonacci03(n - 3);
        return cache[n];
    }
}
