package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-04
 * @Time: 9:38
 * @Description: 题目6 ：Z 字形变换
 */
public class Topic_00006 {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        /**
         * 这里其实首行和末行都是等差数列 差值2*(n-1)
         * 其余行 差值 2*当前行数 与 2*(n-1)-2*当前行数 交替出现
         */
        StringBuilder res = new StringBuilder();
        int interval_1 = 2 * (numRows - 1),
                interval_2 = 0,
                index = 0,
                length = s.length();
        for (int i = 0; i < numRows; i++) {
            index = i;
            interval_2 = 2 * i;
            while (index < length) { // 这里index是从0开始，length是从1开始
                res.append(s.charAt(index));
                /**
                 * 这里太妙了
                 * 第一次 add = step - add , 也就是 step - 2 * i;
                 * 第二次 add = step - add , 就相当于 add = step - (step - add) , 也就是 add
                 */
                interval_2 = interval_1 - interval_2;
                // 这里判断是否为首行或末行
                index += (i == 0 || i == numRows - 1) ? interval_1 : interval_2;
            }
        }
        return res.toString();
    }
}
