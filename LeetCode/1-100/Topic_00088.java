package leetcode_BasicAlgorithm.sortingAndSearching;

import java.util.Arrays;

/**
 * @Author: ZTY
 * @Create: 2022-10-21
 * @Time: 7:27
 * @Description: 题目88 ：合并两个有序数组
 */
public class Topic_00088 {
    /**
     * 方法1 ：使用java中API
     */
    public void merge01(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        for (int i = 0; m < len; i++) {
            nums1[m] = nums2[i];
            m++;
        }
        Arrays.sort(nums1);
    }

    /**
     * 方法2 : 使用java中API
     */
    public void merge02(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 方法3 ：参考归并排序
     * <p>思路：
     * <p>     1.创建一个新数组，把原来2个数组从小到大放入新数组
     * <p>     2.如果一个数组放完了，证明另一个数组如果有数，
     * <p>         一定大于或等于当前新数组的最大元素
     * <p>         所以只需要把有数的这部分全部放到新数组后面
     * <p>             前提已经告诉我们，原来2个数组是非递减数组，
     * <p>             所以直接排即可，不用判断是否大于新数组的最大元素
     */
    public void merge03(int[] nums1, int m, int[] nums2, int n) {
        // 创建新数组
        int[] temp = new int[m + n];
        // 创建新数组的索引下标
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                temp[index++] = nums1[i++];
            } else {
                temp[index++] = nums2[j++];
            }
        }
        while (i < m) {
            temp[index++] = nums1[i++];
        }
        while (j < n) {
            temp[index++] = nums2[j++];
        }
        //再把数组temp中的值赋给nums1
        for (int k = 0; k < m + n; k++) {
            nums1[k] = temp[k];
        }
    }

    /**
     * 方法4 ：换种思路的归并排序
     * <p>思路：
     * <p> 题目说nums1有足够的空间容纳nums2，
     * <p> 所以我们可以认为nums1的长度肯定大于nums2。
     * <p> 正常归并都是从小往大开始，这里我们可以换种思路，
     * <p> 从大往小开始
     */
    public void merge04(int[] nums1, int m, int[] nums2, int n) {
        // 这种方法就是从大到小排
        // nums1的索引下标
        int i = m - 1;
        // nums2的索引下标
        int j = n - 1;
        // 重新排序后的nums1的索引下标
        int index = m + n - 1;
        /**
         * <p>这里存在 种情况
         * <p>1.只有nums2存在最小元素，也就是nums1已经重新排好了(此时 i=-1)，
         * <p>     此时只需要把nums2中“剩余”元素按从大到小的顺序循环写入nums1中
         * <p>2.nums1中存在最小元素，或nums1与nums2共存最小元素
         * <p>     当循环到j=-1时，就不用循环了。因为nums1中已经符合题目要求
         */
        while (j >= 0) {
            nums1[index--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
    }
}
