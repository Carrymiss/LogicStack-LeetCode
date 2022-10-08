package practise;

/**
 * @Author: ZTY
 * @Create: 2022-10-07
 * @Time: 10:47
 * @Description: 题目344 ：反转字符串
 */
public class Topic_00344 {
    /**
     * 方法1 : 使用reverse()翻转方法
     * * 不过这个力扣里面不让用  哈哈哈哈 平时工作可以这样写
     */
    public void reverseString01(char[] s) {
        s = new StringBuilder(String.valueOf(s)).reverse().toString().toCharArray();
    }

    /**
     * 方法2 : 翻转
     */
    public void reverseString02(char[] s) {
        int len = s.length;
        if (len == 1) {
            return;
        }
        for (int i = 0; i < len / 2; i++) {
            // 第一种翻转形式
            char temp = s[i];
            s[i] = s[len - i - 1];
            s[len - i - 1] = temp;
        }
    }

    /**
     * 方法3 : 双指针
     * * 这个就是把第二个方法改了改，思路一样
     */
    public void reverseString03(char[] s) {
        int len = s.length;
        if (len == 1) {
            return;
        }
        // 双指针
        int left = 0;
        int right = len - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    /**
     * 用这个方法的时候，注销其它三种方式
     */
    private void swap(char[] array, int left, int right) {
        // 第1种交换方式
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;

        // 第2种交换方式
        array[left] = (char) (array[left] + array[right]);
        array[right] = (char) (array[left] - array[right]);
        array[left] = (char) (array[left] - array[right]);

        // 第3种交换方式
        array[left] = (char) (array[left] - array[right]);
        array[right] = (char) (array[left] + array[right]);
        array[left] = (char) (array[right] - array[left]);

        // 第4种交换方式
        array[left] ^= array[right];
        array[right] ^= array[left];
        array[left] ^= array[right];
    }

    /**
     * 方法4 : 递归
     * * 这个就是把第二个方法改了改，思路一样
     */
    public void reverseString04(char[] s) {
        int len = s.length;
        if (len == 1) {
            return;
        }
        // 双指针
        int left = 0;
        int right = len - 1;
        recursion01(s, left, right);
    }

    private void recursion01(char[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(array, left++, right--);
        recursion01(array, left, right);
    }

    /**
     * 方法5 : 递归
     * * 这个就是把第4个方法改了改，先递归，回来的时候进行swap交换
     */
    private void recursion02(char[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        /**
         * 这里是重点
         * * X++/X-- 会超范围
         * * ++X/--X 无法交换第一个元素并且中间的元素也会有问题
         * * 所以这里用  left + 1, right - 1
         */
        recursion01(array, left + 1, right - 1);
        swap(array, left, right);
    }
}
