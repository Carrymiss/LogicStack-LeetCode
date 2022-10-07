package practise;

/**
 * @Author: LQ
 * @Create: 2022-03-31
 * @Time: 11:16
 * @Description: 题目3 ：无重复字符的最长子串
 */
public class Topic_00003 {
    public int lengthOfLongestSubstring01(String s) {
        int len = 0;
        int i = 0;
        int result = 0;
        int flag = 0;
        while (i < s.length()) {
            int post = s.indexOf(s.charAt(i), flag);
            if (post < i) {
                if (result < len) {
                    result = len;
                }
                if (result >= s.length() - post - 1) {
                    return result;
                }
                len = i - post - 1;
                flag = post + 1;
            }
            len++;
            i++;
        }
        return len;
    }


    public int lengthOfLongestSubstring02(String s) {
        int len=0;
        int flag=0;
        int post;
        int result=0;
        for(int i=0;i<s.length();i++){
            post=s.indexOf(s.charAt(i),flag);
            if(post<i){
                if(result<len){
                    result =len;
                }
                if(result>=s.length()-post-1){
                    return result;
                }
                len=i-post-1;
                flag=post+1;
            }
            len++;
        }
        return len;
    }
}
