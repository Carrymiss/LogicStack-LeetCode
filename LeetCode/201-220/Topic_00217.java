package practise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 11:52
 * @Description: 题目217 ：存在重复元素
 */
public class Topic_00217 {
    /**
     * 方法一 ：排序+比较
     */
    public boolean containsDuplicate01(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return false;
        }
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二 ：使用Set集合
     *      add的时候，重复了会返回false
     */
    public boolean containsDuplicate02(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int num:nums) {
            // 有重复元素返回true
            if(!set.add(num)){
                return true;
            }
        }
        return false;
    }
}
