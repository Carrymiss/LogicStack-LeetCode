package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-07
 * @Time: 10:29
 * @Description: 题目7 ：整数反转
 */
public class Topic_00007 {
    /**
     * 方法1 使用reverse()方法 翻转
     * * 这里需要注意 要处理 NumberFormatException 异常
     * * 也就是说 如果一串纯数字字符串塞不进一个int说明超出了int范围，直接返回0
     */
    public int reverse01(int x) {
        if (x == 0) {
            return x;
        }
        int flag = x > 0 ? 1 : -1;
        x = Math.abs(x);
        int res = 0;
        try {
            res = Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        return res * flag;
    }

    /**
     * 方法2 手写翻转
     */
    public int reverse02(int x) {
        int res = 0;
        while (x != 0) {
            // 余数
            int t = x % 10;
            int newRes = res * 10 + t;
            //如果数字溢出，直接返回0
            if ((newRes - t) / 10 != res) {
                return 0;
            }
            res = newRes;
            x = x / 10;
        }
        return res;
    }

    /**
     * 方法3 优化方法2
     */
    public int reverse03(int x) {
        // 这里要用 long类型
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        // 如果为真要输出(int) res，因为之前的res是long类型的，这里需要强制类型转换
        return (int) res == res ? (int) res : 0;
    }
}
