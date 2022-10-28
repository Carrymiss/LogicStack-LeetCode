package leetcode_BasicAlgorithm.math;

/**
 * @Author: ZTY
 * @Create: 2022-10-28
 * @Time: 8:27
 * @Description: 题目204 : 计数质数
 */
public class Topic_00204 {
    /**
     * 方法 ： 模拟
     * <p>思路：
     * <p>     首先题目让 返回 所有小于非负整数 n 的质数的数量
     * <p>  1.我们可以找出此时符合条件的质数
     * <p>        循环可以从2开始，因为3之前符合条件的数量为0
     * <p>  2.找出之后，因为质数*N=合数，所以我们把此时能找出的合数放入集合
     * <p>  3.然后下次进行比较n是否是合数，不是就是质数，让计数器加1
     */
    public int countPrimes(int n) {
        // 利用boolean的初始值为false
        boolean[] composite = new boolean[n];
        int count = 0;
        // 这里要注意看题目 返回所有 小于 非负整数 n 的质数的数量
        for (int i = 2; i < n; i++) {
            // 此时的值在合数集合中 直接跳出此次循环
            if (composite[i]) {
                continue;
            }
            count++;
            // 找出此时质数倍数的合数 这个会有重复找出，不过没关系
            for (int j = i; j < n; j += i) {
                composite[j] = true;
            }
        }
        return count;
    }
}
