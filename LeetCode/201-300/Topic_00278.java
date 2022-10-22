package leetcode_BasicAlgorithm.sortingAndSearching;

/**
 * @Author: ZTY
 * @Create: 2022-10-21
 * @Time: 8:38
 * @Description: 题目278 ：第一个错误的版本
 */
public class Topic_00278 {
    // 模拟判断版本号
    boolean isBadVersion(int version) {
        if (version == 4) {
            return true;
        }
        return false;
    }

    /**
     * 方法1 ： 二分法
     */
    public int firstBadVersion01(int n) {
        // 创建双指针
        int left = 1;
        int right = n;
        // 这里当left等于right的时候，说明此时这个值就是我们找的值
        while (left < right) {
            // 这里无符号右移 即使溢出也没关系
            int mid = (right + left) >>> 1;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 方法2 ： 二分法
     */
    public int firstBadVersion02(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            // 这个中间值会比上面方法的mid大1
            // 在下面进行判断 如果是坏版本就减一
            // 直到循环到 rigjt大于left
            // 此时
            /**
             * 这个中间值会比上面方法的mid大1
             * 在下面进行判断 如果是坏版本就减一，也就是只移动右指针
             * 直到循环到 rigjt大于left
             * 此时left的值刚好是最小版本
             */
            int mid = (left - right) / 2 + right;
            if (isBadVersion(mid)) {
                right=mid-1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    /**
     * 方法3 ：二分法
     */
    public int firstBadVersion03(int n) {
        int left=1,right=n;
        while (left<right){
            int minbad = (right-left)/2+left;
            if(isBadVersion(minbad)){
                right=minbad;
            }else {
                left=minbad+1;
            }
        }
        return left;
    }
}
