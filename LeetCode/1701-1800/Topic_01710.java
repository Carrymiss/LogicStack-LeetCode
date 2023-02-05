package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/5
 * @Time: 13:29
 * @Description: 题目1710 ：卡车上的最大单元数
 */
public class Topic_01710 {
    /**
     * 方法一：贪心
     */
    public int maximumUnits01(int[][] boxTypes, int truckSize) {
        // 按照第二个元素的大小，给数组排序
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        for (int[] boxType : boxTypes) {
            if (boxType[0] >= truckSize) {
                res += truckSize * boxType[1];
                break;
            } else {
                res += boxType[0] * boxType[1];
                truckSize -= boxType[0];
            }
        }
        return res;
    }

    /**
     * 方法二：计数排序
     */
    public int maximumUnits02(int[][] boxTypes, int truckSize) {
        int[] boxes = new int[1001];
        for (int i = 0; i < boxTypes.length; i++) {
            boxes[boxTypes[i][1]] += boxTypes[i][0];
        }
        int ans = 0;
        for (int i = 1000; i > 0; i--) {
            if (boxes[i] <= truckSize) {
                ans += boxes[i] * i;
                truckSize -= boxes[i];
            } else if (truckSize > 0 && boxes[i] > truckSize) {
                ans += truckSize * i;
                truckSize = 0;
            }
        }
        return ans;
    }
}
