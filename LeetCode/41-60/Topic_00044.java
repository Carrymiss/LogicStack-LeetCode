package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-10
 * @Time: 15:37
 * @Description: 题目44 ：通配符匹配
 */
public class Topic_00044 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return matchString(s, 0, p, 0, memo, visited);
    }

    public boolean matchString(String s, int sindex, String p, int pindex, boolean[][] memo, boolean[][] visited) {
        if (pindex == p.length()) {
            return sindex == s.length();
        }
        if (sindex == s.length()) {
            return allStart(p, pindex);
        }
        if (visited[sindex][pindex]) {
            return memo[sindex][pindex];
        }

        char sFirestChar = s.charAt(sindex);
        char pFirestChar = p.charAt(pindex);
        boolean match;

        if (pFirestChar != '*') {
            match = compareChar(sFirestChar, pFirestChar) &&
                    matchString(s, sindex + 1, p, pindex + 1, memo, visited);
        } else {
            match = matchString(s, sindex, p, pindex + 1, memo, visited) ||
                    matchString(s, sindex + 1, p, pindex, memo, visited);
        }

        memo[sindex][pindex] = match;
        visited[sindex][pindex] = true;
        return match;
    }

    public boolean allStart(String p, int pindex) {
        for (int i = pindex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean compareChar(char sFirestChar, char pFirestChar) {
        return (sFirestChar == pFirestChar || pFirestChar == '?');
    }
}
