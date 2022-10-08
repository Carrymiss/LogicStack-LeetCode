package practise;

import java.util.Arrays;

/**
 * @Author: LQ
 * @Create: 2022-04-04
 * @Time: 17:35
 * @Description: 题目977 ：有序数组的平方
 */
public class Topic_00977 {
    /**
     * 数组中使用sort排序
     */
    public int[] sortedSquares01(int[] nums) {
        int len=nums.length;
        int[] new_array= new int[len];
        for(int i=0;i<len;i++){
            new_array[i]=nums[i]*nums[i];
        }
        Arrays.sort(new_array);
        return new_array;
    }

    /**
     * 双指针
     */
    public int[] sortedSquares02(int[] nums) {
        int len=nums.length;
        int l=0;
        int r=len-1;
        int mark=r;
        int[] new_array= new int[len];
        while (l<=r){
            int a=nums[r]*nums[r];
            int b=nums[l]*nums[l];
            if(a>=b){
                new_array[mark]=a;
                r--;
                mark--;
            }else {
                new_array[mark]=b;
                l++;
                mark--;
            }
        }
        return new_array;
    }
}
