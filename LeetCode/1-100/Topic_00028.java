package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-08
 * @Time: 16:46
 * @Description: 题目28 ：实现strStr()
 */
public class Topic_00028 {
    /**
     * 1. 使用java api的方法
     */
    public int strStr01(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * 方法2：逐个判断 双指针
     */
    public int strStr02(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            // 逐个匹配字符
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                // 如果不匹配就退回，从第一次匹配的下一个开始
                i = i - j + 1;
                j = 0;
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;
    }

    /**
     * 方法3 ：不断截取主串 进行比较
     */
    public int strStr03(String haystack, String needle) {
        int len = needle.length();
        int total = haystack.length() - len;
        // 这里start是从0开始所以要循环到<=total
        for (int start = 0; start <= total; start++) {
            if (haystack.substring(start, start + len).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * 方法4 ：KMP算法
     */
    public int strStr04(String haystack, String needle) {
        int len = needle.length();
        if (len == 0) {
            return 0;
        }
        int i = 0,
                j = 0;
        /**
         * 数组next表示pattern指定的下标前具有相同的字符串数量，语言组织能力不好，可能不是太好理解，我举个例子吧
         * ，比如ABCABA，数组next[0]是-1，这个是固定的，因为第一个A前面是没有字符的，next[1]是0，因为B的前面就一个A，没有
         * 重复的，所以是0,同理next[2]也是,next[3]也是0,而next[4]是1，因为next[4]所指向的是第二个B，第二个B前面有一个A和
         * 第一个A相同，所以是1,next[5]是2，因为next[5]所指向的是最后一个Ａ，因为前面的Ａ对比成功，并且Ｂ也对比成功，所以是２，
         * 也就是ＡＢ两个字符串匹配成功,再举个例子，比如WABCABA，数组除了第一个为-1，其他的都是为0，因为只有第一个匹配成功之后
         * 才能匹配后面的，虽然后面的AB和前面的AB匹配成功，但是后面AB的前面是C和前面AB的前面一个W不匹配，所以后面的匹配都是0.
         * 要记住只有指定字符前面的字符和第一个字符匹配成功的时候才能往后匹配，否则后面的永远都是先和第一个匹配。
         */
        int[] next = new int[len];
        // needle 为模式串 len为模式串长度
        getNext(needle, len, next);
        while (i < haystack.length() && j < len) {
            /**
             * 这里j等于-1的时候也只有在下面next数组赋值的时候才会出现，并且只有在数组next[0]的时候才会等于-1，
             其他时候是没有的，这一点要谨记，待会下面求next数组的时候就会用到。这里可以这样来理解，如果j不等于-1，
             并且下标i和j所指向的字符相等，那么i和j分别往后移一位继续比较，这个很好理解，那么如果j==-1的时候，就表示
             就表示前面没有匹配成功的，同时i往后移一位，j置为0（j==-1的时候，j++为0），再从0开始比较。
             */
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                /**
                 * i = i - j + 1;
                 j = 0;
                 返回到指定的位置，不是返回到匹配失败的下一个位置，这里都好理解，重点是求数组next。
                 这里只要j等于0，在next[j]赋值的之后，j就会等于-1；因为next[0]等于-1
                 */
                j = next[j];// j回到指定位置
            }
            if (j == len) {
                return i - j;
            }
        }
        return -1;
    }

    private void getNext(String p, int len, int next[]) {
        int i = 0, j = -1;
        //这个默认的
        next[0] = -1;
        /**
         * 匹配的时候是当前字符的前一个和前面的匹配，
         * 所以最后一个是不参与匹配的，可以看Str方法的注释，
         */
        while (i < len - 1) {
            if (j == -1 || p.charAt(i) == p.charAt(j)) {
                /**
                 * 如果j不等于-1，指定的字符相等，那么i和j要往后移一位，这点很好理解，如果j为-1的时候，i往后移移位，j置为0
                 * 重新开始匹配。next[i]是匹配成功的数量
                 */
                i++;
                j++;
                next[i] = j;
            } else {
                /**
                 * 关键是这里不好理解，为什么匹配失败要让next[j]等于j，要记住这里的next[j]是指匹配成功的数量，有可能为0，也有可能是其他数.比如
                 * 字符串ABCABXYABCABATDM,对应的next数组为{-1	0	0	0	1	2	0	0	1	2	3	4	5	1	0	0	}
                 */
                j = next[j];
            }
        }
    }

    /**
     * 方法5 ：KMP算法
     * 宫水三叶版
     */
    // KMP 算法
    // ss: 原串(string)  pp: 匹配串(pattern)
    public int strStr05(String ss, String pp) {
        if (pp.isEmpty()) {
            return 0;
        }

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        // 这里是m + 1 是因为前面加了哨兵(空格)
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) {
                j++;
            }
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) {
                j = next[j];
            }
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) {
                j++;
            }
            // 整一段匹配成功，直接返回下标
            if (j == m) {
                return i - m;
            }
        }
        return -1;
    }
}
