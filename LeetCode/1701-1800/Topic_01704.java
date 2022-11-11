package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-11
 * @Time: 8:09
 * @Description: 题目1704 ：判断字符串的两半是否相似
 */
public class Topic_01704 {
    /**
     * 方法1：模拟
     */
    public boolean halvesAreAlike01(String s) {
        int len = s.length();
        char[] chars = s.toLowerCase().toCharArray();
        int count = 0;
        String temp = "aeiou";
        // 一半加 一半减 如果count等于0则返回true 否则返回false
        for (int i = 0; i < len / 2; i++) {
            if (temp.indexOf(chars[i]) >= 0) {
                count++;
            }
        }
        for (int i = len / 2; i < len; i++) {
            if (temp.indexOf(chars[i]) >= 0) {
                count--;
            }
        }
        return count == 0;
    }
}
