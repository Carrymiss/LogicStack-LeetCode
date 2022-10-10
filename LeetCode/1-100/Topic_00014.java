package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-10
 * @Time: 18:39
 * @Description: 题目14 ：最长公共前缀
 */
public class Topic_00014 {
    /**
     * 方法： 模拟
     */
    public String longestCommonPrefix(String[] strs) {
        // 这里等于第一个字符串，是因为下面要indexOf
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            // 截取字符串进行比较
            while (strs[i].indexOf(pre) != 0){
                /**+
                 * 这样做是因为，公共前缀是共同有的，
                 * 所以第一个字符串肯定也有完整的公共前缀
                 * 所以不断截取它就行
                 *
                 * 也就是说，不断的将前面的公共字符串与下一个字符串比较
                 * 循环到最后一个则得到正确的公共前缀
                 * 也可能到最后pre是空的 即没有公共前缀
                 */
                pre=pre.substring(0,pre.length()-1);
            }
            i++;
        }
        return pre;
    }
}
