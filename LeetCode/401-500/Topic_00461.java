package leetcode_BasicAlgorithm.other;

/**
 * @Author: ZTY
 * @Create: 2022-10-30
 * @Time: 16:52
 * @Description: 题目461 ：汉明距离
 */
public class Topic_00461 {
    /**
     * 方法1 : 使用java包装类 + 异或
     */
    public int hammingDistance01(int x, int y) {
        return Integer.bitCount(x^y);
    }

    /**
     * 方法2 : 将异或的结果不断向右逻辑位移
     */
    public int hammingDistance02(int x, int y) {
        int n=x^y;
        int len=0;
        while (n!=0){
            len+=n&1;
            // 这里使用逻辑运算符 使用算数运算符会让n永远不等于0 陷入死循环
            n>>>=1;
        }
        return len;
    }

    /**
     * 方法3 ：利用位运算规律
     * <p> 规律： n&(n-1)可以把n最右边的1给消掉
     * <p> 例如 n=12  1 1 0 0
     * <p> n-1=11    1 0 0 1
     * <p> n&(n-1)   1 0 0 0
     */
    public int hammingDistance03(int x, int y) {
        int n=x^y;
        int len=0;
        while (n!=0){
            len+=1;
            n&=n-1;
        }
        return len;
    }
}
