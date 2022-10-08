package practise;

/**
 * @Author: LQ
 * @Create: 2022-03-31
 * @Time: 14:03
 * @Description: 题目1266 ：访问所有点的最小时间
 */
public class Topic_01266 {
    public int minTimeToVisitAllPoints(int[][] points) {
        int sum=0,len;
        for(int i=0;i<points.length-1;i++){
            int x=Math.abs(points[i][0]-points[i+1][0]);
            int y=Math.abs(points[i][1]-points[i+1][1]);
            len=Math.max(x,y);
            sum=len+sum;
        }
        return sum;
    }
}
