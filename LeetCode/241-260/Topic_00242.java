package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2022-10-07
 * @Time: 14:56
 * @Description: 题目242 ：有效的字母异位词
 */
public class Topic_00242 {
    /**
     * 方法1：排序然后比较
     */
    public boolean isAnagram01(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        // 排序
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        // 比较
        return Arrays.equals(chars1, chars2);
    }

    /**
     * 方法2：计算两个字符串中字符的差值
     */
    public boolean isAnagram02(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        // 只有26个字母
        int[] count = new int[26];
        // 统计chars1中字符的数量
        for (int i = 0; i < s.length(); i++) {
            count[chars1[i] - 'a']++;
        }
        // 与chars2字符进行相减
        for (int i = 0; i < t.length(); i++) {
            count[chars2[i] - 'a']--;
        }
        // 如果字符中有一个值不为0，返回false
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法3：一次遍历   想到这个方法的人是变态吗！！！
     * * 官方思路：
     * * 使用一个变量num记录新出现字符的个数，
     * * 然后同时遍历两个数组，如果出现了一个新的字符，
     * * num就加1，如果消失了一个字符，num就减1，最后判断num是否等于0即可。
     * * 我的思路：
     * * 1.根据条件，他们的长度是相等的，也就说进行相同的操作次数后，加减可以抵消
     * *    这个就解决了 aabbb bbaaa 的这种可能性
     * * 2.通过记录新字符 可以解决 aabbc aabbb 的这种可能性
     * * 总结：把简单题当成难题思考的人真的变态
     */
    public boolean isAnagram03(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int[] count = new int[26];
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            //出现了一个新的字符
            if (++count[chars1[i] - 'a'] == 1) {
                num++;
            }
            //消失了一个新的字符
            if (--count[chars2[i]-'a']==0) {
                num--;
            }
        }
        return num == 0;
    }
}
