package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-01
 * @Time: 8:00
 * @Description: 题目1662 ：检查两个字符串数组是否相等
 */
public class Topic_01662 {
    /**
     * 方法1 ：使用java中的方法
     */
    public boolean arrayStringsAreEqual01(String[] word1, String[] word2) {
        /**
         * join() 方法：
         * 返回由CharSequence元素的副本与指定分隔符的副本连接在一起的新字符串
         * 例子：
         * String message = String.join("-", "Java", "is", "cool");
         * message returned is: "Java-is-cool"
         */
        return String.join("", word1).equals(String.join("", word2));
    }

    /**
     * 方法2 ：模拟
     */
    public boolean arrayStringsAreEqual02(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (String s : word1) {
            sb1.append(s);
        }
        for (String s : word2) {
            sb2.append(s);
        }
        if (sb2.length() != sb1.length()) {
            return false;
        }
        return sb1.toString().equals(sb2.toString());
    }

    /**
     * 方法3 : 双指针
     */
    public boolean arrayStringsAreEqual03(String[] word1, String[] word2) {
        // p 是数组的索引 i，j是指针
        int p1 = 0, p2 = 0, i = 0, j = 0;
        // 如果循环到一个数组长度 就结束循环
        while (p1 < word1.length && p2 < word2.length) {
            // 判断对应的字符是否相等
            if (word1[p1].charAt(i) != word2[p2].charAt(j)) {
                return false;
            }
            i++;
            j++;
            // 当i的值等于字符串的长度，就让它等于0，从下一个元素开始
            if (i == word1[p1].length()) {
                p1++;
                i = 0;
            }
            if (j == word2[p2].length()) {
                p2++;
                j = 0;
            }
        }
        // 判断长度是否相等
        return p1 == word1.length && p2 == word2.length;
    }
}
