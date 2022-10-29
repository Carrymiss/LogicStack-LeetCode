package leetcode_BasicAlgorithm.math;

/**
 * @Author: ZTY
 * @Create: 2022-10-28
 * @Time: 9:03
 * @Description: 题目326 : 3的幂
 */
public class Topic_00326 {
    /**
     * 方法 1 ： 模拟
     */
    public boolean isPowerOfThree01(int n) {
        // 把0排除
        if (n == 0) {
            return false;
        }
        while (n % 3 == 0) {
            // 循环让n除以3 直到n=1的时候，这时候当初的n就是3的幂
            n /= 3;
        }

        return n == 1;
    }

    /**
     * 方法 2 ： 递归
     * <p> 和上面逻辑一样
     */
    public boolean isPowerOfThree02(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree02(n / 3)));
    }

    /**
     * 方法3： 来自评论大佬的思路
     * <p> 思路:
     * <p>  -2^31 <= n <= 2^31 - 1 中最大的3的幂次方是 1162261467 ,
     * <p>  如果n是一个3的幂次方,那么肯定可以被1162261467整除,
     * <p>  所以判断1162261467%n是不是等于0就可以了
     */
    public boolean isPowerOfThree03(int n) {
        if (n > 0) {
            return 1162261467 % n == 0;
        }
        return false;
    }

    public boolean isPowerOfThree04(int n) {
        // 把上面的改成一行
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 方法4 ： 使用log性质求解
     * <p> 思路:
     * <p> 1. 负数与0无对数
     * <p> 2. 1的对数是0
     * <p>
     * <p> 根据换底公式: log A M /log A N =log N M
     * <p> log a Y =x 就是 a的x次方等于Y
     */
    public boolean isPowerOfThree05(int n) {
        // 可以参考上面的换底公式
        double v = Math.log10(n) / Math.log10(3);
        /**
         * <p>情况1 ： 如果这个 v 是 整数 说明n是3的幂
         * <p>           整数中存在特殊情况，n是1 v的值为0
         * <p>情况2 ：n=0的时候 v的值是 -Infinity
         * <p>情况3 ：n<0的时候 v的值是 NaN
         * <p>情况4 ：n不为3的幂时，v是带有小数的
         * <p>
         * <p>综合上面的情况 ，只需要要让 v % 1 如果等于0 说明是 情况1
         * <p>     此时返回true即可 其它情况都是false
         */
        return v % 1 == 0;
    }

    // 把上面方法改成一行
    public boolean isPowerOfThree06(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
