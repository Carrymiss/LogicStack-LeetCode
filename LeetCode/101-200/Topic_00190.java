package leetcode_BasicAlgorithm.other;

/**
 * @Author: ZTY
 * @Create: 2022-10-30
 * @Time: 17:05
 * @Description: 题目190 ：颠倒二进制位
 */
public class Topic_00190 {
    /**
     * 方法1 ： 循环截取
     * <p> 每次循环的时候把n的最后一位数字（二进制的）截取掉，
     * <p> 放到一个新的数字中的末尾
     */
    public int reverseBits01(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            /**
             * res先往左移一位，把最后一个位置空出来，
             * 用来存放n的最后一位数字
             *
             * 这个不能放到后面，放到后面n循环完了，它还左移一次
             * 放在前面相当于 第一次时候 多左移一次。
             * 但是没关系，因为它初始值是0 左移还是等于0 没影响
             */
            res <<= 1;
            /**
             * res加上n的最后一位数字
             *
             * |= 位或运算
             * 在同一位上数字相同时结果为0,不同十为1
             */
            res |= n & 1;
            //n往右移一位，把最后一位数字去掉
            n >>= 1;
        }
        return res;
    }

    /**
     * 方法2 : 利用int 32为性质
     * <p> 在java中int类型是32位的，这里用第1位和第32位交换，第2位和第31位交换……
     * <p> 最终数字n的高16位会变为低16位，低16位会变为高16位
     */
    public int reverseBits02(int n) {
        int res = 0;
        //把低16位移到高16上
        for (int i = 0; i < 16; i++) {
            res |= (n & (1 << i)) << (31 - i * 2);
        }
        //把高16位移到低16位上
        for (int i = 16; i < 32; i++) {
            res |= (n & (1 << i)) >>> (i * 2 - 31);
        }
        return res;
    }
}
