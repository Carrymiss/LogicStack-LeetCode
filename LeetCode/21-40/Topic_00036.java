package practise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2022-10-06
 * @Time: 10:40
 * @Description: 题目36 ：有效的数独
 */
public class Topic_00036 {
    /**
     * 方法一：数组
     * * 重点：
     * *    1.推倒公式：
     * *        小方块编号和行列的关系为：
     * *        int idx = i / 3 * 3 + j / 3;
     * *    2.char值与0的ascii码相减 正好为char值所表示的int数字
     * *    3.三条默认规则：
     * *        (1) 在同一行中该数字唯一
     * *        (2) 在同一行中该数字唯一
     * *        (3) 在3*3的小区块中该数字唯一
     * *    4.数组采用Boolean 方便进行判断
     */
    public boolean isValidSudoku01(char[][] board) {
        // 行
        boolean[][] row = new boolean[10][10];
        // 列
        boolean[][] col = new boolean[10][10];
        // 区块
        boolean[][] area = new boolean[10][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 循环取值
                char c = board[i][j];
                if (c == '.') {
                    // 如果没有数字，跳出一次循环
                    continue;
                }
                // 不知道为什么，但是题解上大家都默认知道这个公式 哈哈哈
                // 这个就是把9*9 宫格分为9个区域 编号0-8
                int idx = i / 3 * 3 + j / 3;
                // 如果是数字，它与0的ascii码相减 正好为它表示的数字
                int a = c - '0';
                // 判断这个数字是否重复
                if (row[i][a] || col[j][a] || area[idx][a]) {
                    // 有重复直接false
                    return false;
                }
                // 执行到这里，说明数字是第一次出现
                // 赋值
                row[i][a] = col[j][a] = area[idx][a] = true;
            }
        }
        return true;
    }

    /**
     * 方法二：哈希表
     * * 重点再写一次：
     * *    小方块编号和行列的关系为：
     * *       int idx = i / 3 * 3 + j / 3;
     */
    public boolean isValidSudoku02(char[][] board) {
        /**
         * 大体与”方法一“思路一致
         * * 使用Set集合的特性，将重复的直接输出
         */
        Map<Integer, Set<Integer>> row = new HashMap<>(),
                col = new HashMap<>(),
                area = new HashMap<>();
        // 根据“方法一”的三条默认规则，创建hashSet
        for (int i = 0; i < 9; i++) {
            row.put(i, new HashSet<>());
            col.put(i, new HashSet<>());
            area.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int idx = i / 3 * 3 + j / 3;
                int a = c - '0';
                if (row.get(i).contains(a) || col.get(j).contains(a) || area.get(idx).contains(a)) {
                    return false;
                }
                row.get(i).add(a);
                col.get(j).add(a);
                area.get(idx).add(a);
            }
        }
        return true;
    }

    /**
     * 方法三：位运算   只能说学数学的牛逼
     * * 重点写三次：
     * *    小方块编号和行列的关系为：
     * *       int idx = i / 3 * 3 + j / 3;
     * *
     * * 思路：
     * *    利用int数组(32位 二进制)的低9位 表示数组中是否存在这个数字
     * *    也就说区别方法一:
     * *        (1) 使用int数组
     * *        (2) 使用一维数组
     * *    检查数字  与运算
     * *    添加数字  或运算
     */
    public boolean isValidSudoku03(char[][] board) {
        int[] row = new int[9],
                col = new int[9],
                area = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int idx = i / 3 * 3 + j / 3;
                int a = c - '0';
                if (((row[i] >> a) & 1) == 1 || ((col[j] >> a) & 1) == 1 || ((area[idx] >> a) & 1) == 1) {
                    return false;
                }
                row[i] |= (1 << a);
                col[j] |= (1 << a);
                area[idx] |= (1 << a);
            }
        }
        return true;
    }
}
