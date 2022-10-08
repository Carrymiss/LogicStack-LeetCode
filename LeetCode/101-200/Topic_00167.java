package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-06
 * @Time: 16:02
 * @Description: 题目167 ：两数之和 II - 输入有序数组
 */
public class Topic_00167 {
    /**
     * 双指针
     */
    public int[] twoSum(int[] numbers, int target) {
        int len=numbers.length;
        int left=0,right=len-1;
        while (left<right){
            int i=numbers[left]+numbers[right];
            if(i==target){
                return new int[]{left+1,right+1};
            }else if(i<target){
                left++;
            }else {
                right--;
            }
        }
        return new int[]{-1,-1};
    }
}
