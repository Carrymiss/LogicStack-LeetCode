package practise;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2022-10-05
 * @Time: 15:07
 * @Description: 题目136 ：只出现一次的数字
 */
public class Topic_00136 {
    /**
     * 方法一：异或 ^
     * * 异或有以下三个特性：
     * *    a^a=0；自己和自己异或等于0
     * *    a^0=a；任何数字和0异或还等于他自己
     * *    a^b^c=a^c^b；异或运算具有交换律
     */
    public int singleNumber01(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 方法二：使用Set集合
     */
    public int singleNumber02(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 添加失败证明已经存在该值
            if (!set.add(num)) {
                // 移除这个值
                set.remove(num);
            }
        }
        /**
         *  如果这个集合保证了迭代器返回元素的顺序，
         *  那么这个toArray方法必须以相同的顺序返回元素
         * * 下面输出的是集合的第一个值
         */
        return (int) set.toArray()[0];
    }
}
