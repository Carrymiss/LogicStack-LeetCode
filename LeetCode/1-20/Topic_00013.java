package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-04
 * @Time: 8:25
 * @Description: 题目13 ：罗马数字转整数
 */
public class Topic_00013 {
    public int romanToInt(String s) {
        // 获取转换成数字的数组
        int n = s.length();
        int num = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'I':
                    num = 1;
                    break;
                case 'V':
                    num = 5;
                    break;
                case 'X':
                    num = 10;
                    break;
                case 'L':
                    num = 50;
                    break;
                case 'C':
                    num = 100;
                    break;
                case 'D':
                    num = 500;
                    break;
                case 'M':
                    num = 1000;
                    break;
            }
            nums[i] = num;
        }
        // 若较小的数字在较大的数字前面就加负号  这个思路太秀了
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 最后一位不用判断
            if (i < nums.length - 1 && nums[i] < nums[i + 1]) {
                nums[i] = -nums[i];
            }
            res += nums[i];
        }
        return res;
    }
}
