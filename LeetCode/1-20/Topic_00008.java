package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-08
 * @Time: 15:10
 * @Description: 题目8 ：字符串转换整数 (atoi)
 */
public class Topic_00008 {
    public int myAtoi(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int idx = 0;
        // 去除前导空格，如果去完前导空格后无字符了，返回 0
        while (idx < len && chars[idx] == ' ') {
            idx++;
        }
        if (idx == len) {
            return 0;
        }
        // 检查第一个字符：可以为正负号/数字
        boolean isNeg = false;
        if (chars[idx] == '-') { // 符号是否为负号
            idx++;
            isNeg = true;

        } else if (chars[idx] == '+') { // 符号是否为正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) { // 是否为数字
            return 0;
        }
        // 走到这里，已经排除了 前导空格，正负号，而且剩下的是数字开头的
        int res = 0;
        // 循环遍历所有数字，直到遇到非数字或者循环到len
        while (idx < len && Character.isDigit(chars[idx])) {
            int cur = chars[idx++] - '0';
            // 判断是否溢出 如果移除就进行处理
            if (res > (Integer.MAX_VALUE - cur) / 10) {
                // 如果是负数溢出就取负数最小值，如果是正数溢出就取正数最大值
                return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 如果加上cur的值没有溢出，就给res赋值
            res = res * 10 + cur;
        }
        return isNeg ? -res : res;
    }
}
