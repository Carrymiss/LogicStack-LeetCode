package practise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: LQ
 * @Create: 2022-04-11
 * @Time: 15:28
 * @Description: 题目291 ：单词规律 II
 */
public class Topic_00291_plus {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> mapping = new HashMap<>();
        Set<String> used = new HashSet<>();
        return matchString(pattern, str, mapping, used);
    }

    public boolean matchString(String pattern, String str,
                               Map<Character, String> mapping, Set<String> used) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }

        Character ch = pattern.charAt(0);
        if (mapping.containsKey(ch)) {
            String word = mapping.get(ch);
            if (str.startsWith(word)) {
                return matchString(pattern.substring(1), str.substring(word.length()), mapping, used);
            }
            return false;
        }

        for (int len = 0; len < str.length(); len++) {
            String word = str.substring(0, len + 1);
            if(used.contains(word)){
                continue;
            }
            mapping.put(ch,word);
            used.add(word);
            if(matchString(pattern.substring(1), str.substring(word.length()), mapping, used)){
                return true;
            }
            mapping.remove(ch);
            used.remove(word);
        }
        return false;
    }

}
