package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2022-11-03
 * @Time: 8:40
 * @Description: 题目1668 ：最大重复子字符串
 */
public class Topic_01668 {
    /**
     * 方法1 ：模拟
     */
    public int maxRepeating01(String sequence, String word) {
        String s = word;
        int ans = 0;
        while (sequence.contains(s)) {
            ans++;
            s += word;
        }
        return ans;
    }

    /**
     * 方法2 ：简单枚举 + 动态规划
     * <p>思考：
     * <p>     动态规划二位数组： f[n][boolean]
     * <p>         n 是字符串 较长 字符串的长度 - 1 (这里的n是索引)
     * <p>             1.如果第一个字符串不是最长的字符串，那么根据题意直接返回结果就行
     * <p>                 如果按照循环走，其实也不会进行循环 所以这个判断是不是最长，其实不重要
     * <p>         boolean 是当前索引n是否能匹配 第二个字符串的值
     * <p>
     * <p>      状态转移公式:
     * <p>      当 f[i][true] = f[i-m][true] +1;
     * <p>             i是第二个字符串长度-1 (这里的i是索引)
     * <p>             *****这里就能看出，这个题目并不需要存储 boolean状态，只需要不断更新最后一次boolean就可以**
     * <p>
     * <p>    边界条件(布尔值为true时)：因为只需要不断更新boolean ，这里改成一维数组
     * <p>    f[m-1]=1；
     */
    public int maxRepeating02(String sequence, String word) {
        int n = sequence.length(), m = word.length();
        int[] f = new int[n];
        for (int i = m - 1; i < n; i++) {
            boolean valid = true;
            for (int j = 0; j < m; j++) {
                /**
                 * 这里看着比较绕 ，其实是这样的
                 * 比如 初次循环，我们要比较第一个字符串的第一个字符，与第二个字符串的第一个字符是否相等
                 * i=m-1, j=0
                 * 带入 ：sequence.charAt(i - m + j + 1)   相当于 （m-1-m+0+1）=（0）
                 * 也就是 sequence.charAt(0) != word.charAt(0)
                 * 然后让j每次加1即可
                 */
                if (sequence.charAt(i - m + j + 1) != word.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if(valid){
                /**
                 * 第一次的时候：
                 *      i= m - 1  这时 f[i]=0+1=1
                 * 下一次匹配成功的时候
                 *   加一即可
                 *
                 * 注意： 这只是第一次循环的时候，
                 * 下一次循环 虽然 i不会等于n-1但是 int数组，默认的初始值是0，
                 * 所以相当于从0开始
                 */
                f[i] = (i == m - 1 ? 0 : f[i - m]) + 1;
            }
        }
        // 最后找到数组中的最大值即可
        return Arrays.stream(f).max().getAsInt();
    }

    /**
     * 方法3 ：KMP 算法 + 动态规划
     */
    public int maxRepeating03(String sequence, String word) {
        int n = sequence.length(), m = word.length();
        int[] fail = new int[m];
        // 给数组分配值
        Arrays.fill(fail, -1);
        for (int i = 1; i < m; ++i) {
            int j = fail[i - 1];
            while (j != -1 && word.charAt(j + 1) != word.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == word.charAt(i)) {
                fail[i] = j + 1;
            }
        }

        int[] f = new int[n];
        int j = -1;
        for (int i = 0; i < n; ++i) {
            while (j != -1 && word.charAt(j + 1) != sequence.charAt(i)) {
                j = fail[j];
            }
            if (word.charAt(j + 1) == sequence.charAt(i)) {
                ++j;
                if (j == m - 1) {
                    f[i] = (i >= m ? f[i - m] : 0) + 1;
                    j = fail[j];
                }
            }
        }

        return Arrays.stream(f).max().getAsInt();
    }

    /**
     * 方法4：模拟
     * 和方法一类似，采用StringBuilder
     */
    public int maxRepeating04(String sequence, String word) {
        int cur = 0;
        String ans = word;
        while(sequence.contains(word)){
            StringBuilder sb = new StringBuilder(word);
            sb.append(ans);
            word = sb.toString();
            cur++;
        }
        return cur;
    }
}
