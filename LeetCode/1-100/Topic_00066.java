package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 17:15
 * @Description: 题目66 ：加一
 */
public class Topic_00066 {
    /**
     * 思路：
     * *  1.如果数组中的所有元素都是9，类似9999，
     * *    加1之后肯定会变为10000，
     * *    也就是数组长度会增加1位
     * *  2.如果数组的元素只要有一个不是9，
     * *    加1之后直接返回即可。
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        // 反向遍历
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        // 如果能执行到这里，说明数组全是9
        int[] res = new int[len + 1];
        res[0] = 1;
        return res;
    }
}
