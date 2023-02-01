package practise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2023-02-01
 * @Time: 8:11
 * @Description: 题目1846 ：减小和重新排列数组后的最大元素
 */
public class Topic_01846 {
    /**
     * 方法1 ：模拟1
     */
    public int maximumElementAfterDecrementingAndRearranging01(int[] arr) {
        // 获取数组长度
        int length = arr.length;
        if (length == 1) {
            return 1;
        }
        // 排序 让1成为队头
        Arrays.sort(arr);
        int max = 0;
        // arr 中 第一个 元素必须为 1
        arr[0] = 1;
        for (int i = 1; i < length; i++) {
            if (Math.abs(arr[i - 1] - arr[i]) > 1) {
                arr[i] = arr[i - 1] + 1;
            }
            max = arr[i];
        }
        return max;
    }
    /**
     * 方法2 ：改进方法1
     */
    public int maximumElementAfterDecrementingAndRearranging02(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > ans){
                ans++;
            }
        }
        return ans;
    }

    /**
     * 方法3 ：计数排序+贪心
     */
    public int maximumElementAfterDecrementingAndRearranging03(int[] arr) {
        int length = arr.length;
        int[] nums = new int[length+1];
        for (int x : arr) {
            if(x<= length){
                nums[x]++;
            }else {
                nums[length]++;
            }
        }
        //miss为需要补上的数
        int miss = 0;
        for(int i = 1; i <= length; i++){
            if(nums[i] == 0){
                //不含i这个元素，用后面的补
                miss++;
            }
            else{
                miss = miss - Math.min(nums[i] - 1 , miss);
            }
        }
        return length-miss;
    }
}
