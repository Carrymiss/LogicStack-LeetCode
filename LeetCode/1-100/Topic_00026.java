package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 9:50
 * @Description: 题目26 ：删除有序数组中的重复项
 */
public class Topic_00026 {
    public int removeDuplicates(int[] nums) {
        // 思路： 采用双指针
        int first = 0,
                len = nums.length;
        if (len == 1) {
            return len;
        }
        for (int second = 1; second < len; second++) {
            if (nums[first] != nums[second]) {
                nums[++first] = nums[second];
            }
        }
        return ++first;
    }
}
