package practise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: ZTY
 * @Create: 2023/2/21
 * @Time: 9:21
 * @Description: 题目421：数组中两个数的最大异或值
 */
public class Topic_00421 {
    /**
     * 方法一：贪心+数学性质
     * <p> 先确定高位，再确定低位（有点贪心算法的意思），才能保证这道题的最大性质
     * <p> 一位接着一位去确定这个数位的大小
     * <p> 利用性质： a ^ b = c ，则 a ^ c = b，且 b ^ c = a
     */
    public int findMaximumXOR(int[] nums) {
        int res = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            // 注意点1：注意保留前缀的方法，mask 是这样得来的
            // 用异或也是可以的 mask = mask ^ (1 << i);
            mask |= (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // 注意点2：这里使用 & ，保留前缀的意思（从高位到低位）
                set.add(num & mask);
            }
            // 这里先假定第 n 位为 1 ，前 n-1 位 res 为之前迭代求得
            int temp = res | (1 << i);
            for (Integer prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }
}
