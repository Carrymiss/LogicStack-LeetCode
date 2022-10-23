package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-22
 * @Time: 9:36
 * @Description: 题目70 ：爬楼梯
 */
public class Topic_00070 {
    /**
     * 方法1 ：递归
     * <p>思路：
     * <p>   当n等于1的时候，只需要跳一次即可，只有一种跳法，记f(1)=1
     * <p>   当n等于2的时候，可以先跳一级再跳一级，或者直接跳二级，共有2种跳法，记f(2)=2
     * <p>   当n等于3的时候，他可以从一级台阶上跳两步，也可以从二级台阶上跳一步上来，所以总共有f(3)=f(2)+f(1)；
     */
    public int climbStairs01(int n) {
        // n等于1 只有一种跳法
        // n等于2 先跳一级再跳一级，或者直接跳二级，共有2种跳法
        if (n < 3) {
            return n;
        }
        return climbStairs01(n - 1) + climbStairs01(n - 2);
    }

    /**
     * 方法2 ：尾递归
     * <p> 上面这种方法，当n比较大的时候会超时，所以不推荐，
     * <p> 但如果还想使用递归我们可以改为尾递归的方式
     * <p> 这个方法主要是利用了斐波那契数列的规律：
     * <p>      1 1 2 3 .........
     * <p>      设定2个值，a(用来记录f(n-2)) b(用来记录f(n-1))
     * <p>      由于斐波那契数列的规律可知：f(n)=f(n-1)+f(n-2)
     * <p>      思路就是 每次把 a,b相加，之后的值付给b
     * <p>          因为这时b的值，是新循环的f(n-1)的值，因为n=n+1
     * <p>      这里注意，这道题的值为 1 2 3 ......
     * <p>      所以要从原来斐波那契数列得出结果的第二个值开始取
     */
    public int climbStairs02(int n) {
        //初始化传参a=1,b=1，，因为斐波那契数列是1 1 2 3 ...开头
        return Fibonacci02(n, 1, 1);
    }

    public static int Fibonacci02(int n, int a, int b) {
        // 递归的出口
        if (n == 1) {
            return b;
        }
        // 递归（n-1，此处的a = b，此处的b = a + b）
        // 因为b是累计俩个相邻的和，赋值给a，再进行循环
        return Fibonacci02(n - 1, b, a + b);
    }

    /**
     * 方法3 ：非递归
     */
    public int climbStairs03(int n) {
        // 这里是因为如果n=1 后面的dp[2]索引就超范围了
        if (n == 1) {
            return 1;
        }
        // 后面为了好理解，从索引1开始
        // 这里需要把数组长度扩大1
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 方法4 ：非递归优化
     * <p>  我们看到上面的数组当前值是依赖他前面两个值的（前两个除外），
     * <p>  我们只需要用两个临时变量即可，不需要申请一个数组
     */
    public int climbStairs04(int n) {
        if (n <= 2) {
            return n;
        }
        int first = 1, second = 2, sum = 0;
        while (n-- > 2) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }

    /**
     * 方法5 ：公式计算
     * <p> 上面是按照规律计算
     * <p> 公式：https://bkimg.cdn.bcebos.com/formula/6a79d456d05de649023252cae49590da.svg
     * <p> 又称为“比内公式”，是用无理数表示有理数的一个范例
     */
    public int climbStairs05(int n) {
        double sqrt = Math.sqrt(5);
        return (int) ((Math.pow((1 + sqrt) / 2, n + 1) - Math.pow((1 - sqrt) / 2, n + 1)) / sqrt);
    }
}
