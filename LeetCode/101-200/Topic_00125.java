package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-07
 * @Time: 15:36
 * @Description: 题目125 ：验证回文串
 */
public class Topic_00125 {
    /**
     * 方法1 ：双指针
     */
    public boolean isPalindrome01(String s) {
        int len = s.length();
        if (len == 1) {
            return true;
        }
        int left = 0,
                right = len - 1;
        while (left < right) {
            // 除去左边的非字母数字
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 除去右边的非字母数字
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 比较字符是否一样
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 方法2 ：改进版双指针
     * * 思路：
     * *    比较之前字母全部转化为小写，
     * *    这里改为for循环的方式
     */
    public boolean isPalindrome02(String s) {
        int len = s.length();
        if (len == 1) {
            return true;
        }
        // 提前转换成全小写
        s = s.toLowerCase();
        // 使用for循环，和上面while一样，换种写法
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法3 ：正则匹配
     * * 思路：
     * *    1. 过滤
     * *    2. 翻转
     * *    3.判断
     */
    public boolean isPalindrome03(String s) {
        // 将除了数字字母外的字符替换成空
        String s1 = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        // 翻转字符串
        String rev = new StringBuilder(s1).reverse().toString();
        // 判断字符串是否相等
        return s1.equals(rev);
    }

    /**
     * 方法4 ：把方法1改成递归
     */
    public boolean isPalindrome04(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    public boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
        /**
         * 这里设计的很漂亮
         * * 通过while对双指针移动，去过到最后if成立，进行回来。如果中途发现不等，
         * * 因为return是与运算，一个为假则为假，也进行回来
         * * 递归的时候一定是++在前
         */
        return Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)) && isPalindromeHelper(s, ++left, --right);
    }
}
