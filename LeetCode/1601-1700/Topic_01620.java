package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-02
 * @Time: 8:41
 * @Description: 题目1620 ：网络信号最好的坐标
 */
public class Topic_01620 {
    /**
     * 方法一 ： 模拟
     * <p>  思路：
     * <p>  1. 最优点坐标在 ：正方形闭区间 xmin,ymin，xmax，ymax中
     * <p>  2. 枚举所有所范围的坐标
     * <p>  3. 通过计算欧几里得距离得到与信号值点的距离，通过比较信号值 ，找到最大信号值
     * <p>  4. 如果最大信号值一样
     * <p>      通过字典序比较： x相同的比较y值，找到最小的字典序坐标
     */
    public int[] bestCoordinate(int[][] towers, int radius) {
        // 找到正方形的范围集合
        int xmin = 0, xmax = 0, ymin = 0, ymax = 0;
        for (int[] t : towers) {
            xmin = Math.min(xmin, t[0]);
            xmax = Math.max(xmax, t[0]);
            ymin = Math.min(ymin, t[1]);
            ymax = Math.max(ymax, t[1]);
        }
        int r2 = radius * radius;
        int strongest = 0, strongestX = 0, strongestY = 0;
        for(int x = xmin; x <= xmax; x++){
            for(int y = ymin; y <= ymax; y++){
                int quality = 0;
                for(int[] t: towers){
                    int dis2 = (t[0] - x) * (t[0] - x) + (t[1] - y) * (t[1] - y);
                    if(dis2 <= r2){
                        quality += Math.floor(t[2] / (1 + Math.sqrt(dis2)));
                    }
                }
                if(quality > strongest){
                    strongest = quality;
                    strongestX = x;
                    strongestY = y;
                }
                // 因为我们在迭代的时候，x和y都是递增的，所以quality = strongest的情况，已经被自动处理好了, 不用纠结复杂度。
            }
        }
        return new int[]{strongestX, strongestY};
    }
}
