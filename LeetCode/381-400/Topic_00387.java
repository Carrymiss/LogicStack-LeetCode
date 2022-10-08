package practise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZTY
 * @Create: 2022-10-07
 * @Time: 11:23
 * @Description: 题目387 ：字符串中的第一个唯一字符
 */
public class Topic_00387 {
    /**
     * 方法1 ：两次遍历
     */
    public int firstUniqChar01(String s) {
        int len = s.length();
        // 一共26个英文字母
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        // 统计字符个数
        for (int i = 0; i < len; i++) {
            // 字符每出现一次就+1
            count[chars[i] - 'a']++;
        }
        //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
        for (int i = 0; i < len; i++) {
            if (count[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法2 ：使用map 两次遍历
     */
    public int firstUniqChar02(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        // 统计字符个数
        for (int i = 0; i < len; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        // 或者改成foreach
        /*for (char ch:chars) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }*/
        //然后在遍历字符串s中的字符，如果出现次数是1就直接返回
        for (int i = 0; i < len; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 方法3 ：使用Java的api 类似于双指针
     * * 一个从前查找，一个从后查找，如果下标相等，说明只出现了一次
     */
    public int firstUniqChar03(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
