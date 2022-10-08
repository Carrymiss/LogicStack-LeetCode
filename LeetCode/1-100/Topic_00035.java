package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-03
 * @Time: 17:04
 * @Description: 题目35 ：搜索插入位置
 */
public class Topic_00035 {
    public int searchInsert(int[] nums, int target) {
        int n=nums.length;
        int r=n-1;
        int l=0;
        while (l<=r){
            int mid=(r-l)/2+l;
            if(nums[mid]<target){
                l=mid+1;
            }else {
               r=mid-1;
            }
        }
        return l;
    }
}
