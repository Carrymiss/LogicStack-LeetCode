package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-04
 * @Time: 16:17
 * @Description: 题目704 ：二分查找
 */
public class Topic_00704 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
