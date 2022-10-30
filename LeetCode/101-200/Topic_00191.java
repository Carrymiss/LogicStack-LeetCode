package leetcode_BasicAlgorithm.other;

/**
 * @Author: ZTY
 * @Create: 2022-10-30
 * @Time: 14:49
 * @Description: 题目191 ：位1的个数
 */
public class Topic_00191 {
    /**
     * 方法1 ： 把n往右移32次，每次都和1进行与运算
     */
    public int hammingWeight01(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if(((n>>i)&1)==1){
                count++;
            }
        }
        return count;
    }

    /**
     * 方法2 ：优化上面的代码
     * <p>  如果一个数往右移了几步之后结果为0了，
     * <p>  就没必要在计算了，所以代码我们还可以在优化一点
     */
    public int hammingWeight02(int n) {
        int count=0;
        while (n!=0){
            count+=n&1;
            /**
             * 算术右移和逻辑右移的区别。>>是算术右移，算术右移高位补充的是符号位，
             * 负数的情况下，高位不是补0，而是补符号位1,
             * >>>逻辑右移的情况下，高位直接补0.
             *
             * 这里如果是算数右移，n不会由=0的时候，会死循环
             */
            n>>>=1;
        }
        return count;
    }

    /**
     * 方法3 ： java工具类
     */
    public int hammingWeight03(int n) {
        // 返回指定int值的二进制补码表示形式中的1位数。
        // 此函数有时称为人口计数（population count）
        return Integer.bitCount(n);
    }

    /**
     * 方法4 ：移动的是1
     */
    public int hammingWeight04(int n) {
        int count=0;
        for (int i = 0; i < 32; i++) {
            // 注意这里运算符的优先级  != 的优先级高于 &
            if((n&(1<<i))!=0){
                count++;
            }
        }
        return count;
    }

    /**
     * 方法5 ：通过运算的结果是否是1来判断也是可以的，
     * 我们只需要把往左移的1和n运算完之后再往右移即可
     */
    public int hammingWeight05(int n) {
        int count=0;
        for (int i = 0; i < 32; i++) {
            // 把往左移的1和n运算完之后再往右移即可
            // 注意这里运算符的优先级  != 的优先级高于 >>
            // 这里要使用>>>逻辑运算符 原因和上面方法中一样
            if(((n&(1<<i))>>>i)==1){
                count++;
            }
        }
        return count;
    }

    /**
     * 方法6 ：利用位运算规律
     * <p> 规律： n&(n-1)可以把n最右边的1给消掉
     * <p> 例如 n=12  1 1 0 0
     * <p> n-1=11    1 0 0 1
     * <p> n&(n-1)   1 0 0 0
     */
    public int hammingWeight06(int n) {
        int count=0;
        while (n!=0){
            // 牛逼
            n&=n-1;
            count++;
        }
        return count;
    }

    /**
     * 方法7 ： 查表
     * <p>  我们先要把0到15转化为二进制，
     * <p>  记录下每个数字包含1的个数再构成一张表，
     * <p>  然后再把数字n每4位进行一次计算
     */
    public int hammingWeight07(int n) {
        //table是0到15转化为二进制时1的个数
        int table[] = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
        int count = 0;
        //通过每4位计算一次，求出包含1的个数
        while (n!=0){
            /**
             * 0xf  0x是16位的 f 是16进制的15 相当于二进制的 1111
             * n & 0xf 相当于 n & 1111
             */
            count+=table[n & 0xf];
            // 这里注意使用逻辑右移 要不n不会等于0 陷入死循环
            n>>>=4;
        }
        return count;
    }

    /**
     * 方法8 ： 把方法6改成递归
     */
    public int hammingWeight08(int n) {
        return n==0?0:1+hammingWeight08(n&(n-1));
    }

}
