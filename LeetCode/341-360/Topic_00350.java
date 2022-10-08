package practise;

import java.util.*;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 16:57
 * @Description: 题目350 ：两个数组的交集 II
 */
public class Topic_00350 {
    /**
     * 方法一： 排序 + 双指针
     */
    public int[] intersect01(int[] nums1, int[] nums2) {
        // 排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 创建双指针
        int left = 0,
                right = 0;
        List<Integer> list = new ArrayList<>();
        while (left < nums1.length && right < nums2.length) {
            /**
             * 三种情况
             *      1. L>R  : R++
             *      2. L<R  : L++
             *      3. L=R  : L++ R++ list.add
             */
            if (nums1[left] > nums2[right]) {
                right++;
            } else if (nums1[left] < nums2[right]) {
                left++;
            } else {
                list.add(nums1[left]);
                left++;
                right++;
            }
        }
        // 把list转换成数组
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 方法二： map
     * *   思路：
     * *   1. 把数组1放到map中，key是num1的元素，value是元素出现的次数
     * *   2. 遍历数组2的元素，如果map中的key包含该元素，
     * *       判断 value>0
     * *          如果大于0，将此元素放入list中，并且value 减 1
     * *          如果小于0，则不放入
     * *   3.将list转化为数组
     */
    public int[] intersect02(int[] nums1, int[] nums2) {
        // 排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 第一步
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        // 第二步
        for (int i = 0; i < nums2.length; i++) {
            if (map.getOrDefault(nums2[i], 0) > 0) {
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        // 第三步
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
