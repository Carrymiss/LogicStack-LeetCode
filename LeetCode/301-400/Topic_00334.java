package practise;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2023/2/16
 * @Time: 10:52
 * @Description: 题目334：递增的三元子序列
 */
public class Topic_00334 {
    /**
     * 方法一：贪心
     * <p>为了找到递增的三元子序列，
     * <p>first 和 second 应该尽可能地小，
     * <p>此时找到递增的三元子序列的可能性更大
     */
    public boolean increasingTriplet01(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return false;
        }
        int first = nums[0], sencond = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            int tmp = nums[i];
            if (tmp > sencond) {
                return true;
            } else if (tmp > first) {
                sencond = tmp;
            } else {
                first = tmp;
            }
        }
        return false;
    }

    /**
     * 方法二：改进方法1
     */
    public boolean increasingTriplet02(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num:nums) {
            if(num<=second){
                second=num;
            } else if (num<=first) {
                first=num;
            }else {
                return true;
            }
        }
        return false;
    }
}
