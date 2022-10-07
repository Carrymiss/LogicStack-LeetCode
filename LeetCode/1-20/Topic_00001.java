package practise;

import java.util.HashMap;

/**
 * @Author: LQ
 * @Create: 2022-03-30
 * @Time: 21:39
 * @Description: 题目1 ：两数之和
 */
public class Topic_00001 {

    public int[] twoSum01(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int k = i + 1; k < len; k++) {
                if (nums[i] + nums[k] == target) {
                    return new int[]{i, k};
                }
            }
        }
        return new int[]{};
    }


    public int[] twoSum02(int[] nums, int target) {
        int[] result = {0, 1};
        if (nums.length <= 2) {
            return result;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            result[0] = i;
            int x = target - nums[i];
            for (int k = i + 1, j = nums.length - 1; j >= k; j--,
                    k++) {
                if (nums[k] == x) {
                    result[1] = k;
                    return result;
                }
                if (nums[j] == x) {
                    result[1] = j;
                    return result;
                }
            }
        }
        return result;
    }


    public int[] twoSum03(int[] nums, int target) {
        int[] result = {0, 1};
        if (nums.length <= 2) {
            return result;
        }
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            if (nums[i] + nums[j] == target) {
                result[0] = i;
                result[1] = j;
                return result;
            }
            int x = target - nums[i];
            int y = target - nums[j];
            for (int k = i + 1, m = j - 1; m >= k; k++,
                    m--) {
                result[0] = i;
                if (nums[k] == x) {
                    result[1] = k;
                    return result;
                } else if (nums[m] == x) {
                    result[1] = m;
                    return result;
                }

                result[1] = j;
                if (nums[k] == y) {
                    result[0] = k;
                    return result;
                } else if (nums[m] == y) {
                    result[0] = m;
                    return result;
                }
            }
        }
        return result;
    }


    public int[] twoSum04(int[] nums, int target) {
        int[] result = new int[2];
        int temp;
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            if (hashMap.containsKey(temp)) {
                result[0] = hashMap.get(temp);
                result[1] = i;
                return result;
            }
            hashMap.put(nums[i], i);
        }
        return result;
    }

}
