package practise;

/**
 * @Author: ZTY
 * @Create: 2023/2/6
 * @Time: 8:59
 * @Description: 题目553 ：最优除法
 */
public class Topic_00553 {
    /**
     * 方法一：贪心
     */
    public String optimalDivision01(int[] nums) {
        int length = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (length == 1) {
                sb.append(nums[i]);
                break;
            }
            if (length == 2) {
                sb.append(nums[i] + "/" + nums[i + 1]);
                break;
            }
            if (i == length - 1) {
                sb.append(nums[i] + ")");
            } else if (i == 1) {
                sb.append("(" + nums[i] + "/");
            } else {
                sb.append(nums[i] + "/");
            }
        }
        return sb.toString();
    }

    /**
     * 方法二：优化方法一
     */
    public String optimalDivision02(int[] nums) {
        int length = nums.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(nums[i]);
            // 选择插入除号
            if (i + 1 < length) {
                sb.append("/");
            }
        }
        if (length > 2) {
            sb.insert(sb.indexOf("/") + 1, "(");
            sb.append(")");
        }
        return sb.toString();
    }
}
