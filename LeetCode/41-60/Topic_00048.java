package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-06
 * @Time: 12:38
 * @Description: 题目48 ：旋转图像
 */
public class Topic_00048 {
    /**
     * 方法一 : 翻转
     * *    思路 ： 上下翻转 + 对角线翻转
     */
    public void rotate01(int[][] matrix) {
        int len = matrix.length;
        // 上下翻转
        for (int i = 0; i < len / 2; i++) {
            int[] temp=matrix[i];
            matrix[i]=matrix[len-i-1];
            matrix[len-i-1]=temp;
        }
        // 对角线翻转
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }
    }

    /**
     * 方法二 ：直接交换
     * *    思路：
     * *        规律1 : (i,j) -> (j, n-i-1) -> (n-i-1, n-j-1) -> (n-j-1, i) -> (i,j)
     * *           元素都是在这四个位置移动
     * *        规律2 : 先外圈转，然后内圈转 最后可以达到旋转90°的效果
     */
    public void rotate02(int[][] matrix) {
        int len = matrix.length;
        // 只需要循环一半的元素，另一半在旋转中自动交换
        for (int i = 0; i < len/2; i++) {
            // 一共需要循环的圈数
            for (int j = i; j < len-i-1; j++) {
                int temp=matrix[i][j];
                matrix[i][j]=matrix[len-j-1][i];
                matrix[len-j-1][i]=matrix[len-i-1][len-j-1];
                matrix[len-i-1][len-j-1]=matrix[j][len-i-1];
                matrix[j][len-i-1]=temp;
            }
        }
    }
}
