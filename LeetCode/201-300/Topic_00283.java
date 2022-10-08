package practise;

/**
 * @Author: LQ
 * @Create: 2022-04-06
 * @Time: 15:18
 * @Description: 题目283 ：移动零
 */
public class Topic_00283 {
    /**
     * 方法一 ：单指针
     * *思路：
     * *   1.把0依次替换成别的元素
     * *   2.把指针后面的元素都替换成0
     */
    public void moveZeroes01(int[] nums) {
        int len = nums.length,
                index=0;
        // 步骤1
        for (int i = 0; i < len; i++) {
            if(nums[i]!=0){
                nums[index++]=nums[i];
            }
        }
        // 步骤2
        while (index<len){
            nums[index++]=0;
        }
    }


    /**
     * 方法二 ：双指针
     * * 重点：下面一共有两个指针 index 和 index - count
     * * 思路：
     * *    index 是遍历数组中的每个元素
     * *    count 是元素为零位置的索引地址
     * *    index - count 是当前最左端为零元素的索引
     * * 注： 如果感觉思路绕，也可以用普通双指针做
     */
    public void moveZeroes02(int[] nums) {
        int len = nums.length;
        // 统计前面0的个数
        int count = 0;
        for (int index = 0; index < len; index++) {
            if (nums[index] == 0) {
                //如果当前数字是0就不替换
                count++;
            } else if (count != 0) {
                //否则，把当前数字放到最前面那个0的位置，然后再把
                //当前位置设为0
                nums[index - count] = nums[index];
                nums[index] = 0;
            }
        }
    }

    // 普通双指针方法
    /**
     * 双指针
     * 使用双指针，
     * 左指针指向当前已经处理好的序列的尾部，
     * 右指针指向待处理序列的头部。
     *
     * 右指针不断向右移动，每次右指针指向非零数，
     * 则将左右2个指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     * 1.左指针左边均为非零数；
     * 2.右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，
     * 且非零数的相对顺序并未改变。
     */
    public void moveZeroes03(int[] nums) {
        int len=nums.length;
        int left=0,right=0;
        while (right<len){
            if(nums[right]!=0){
                permutation(nums,left,right);
                left++;
            }
            right++;
        }
    }

    public void permutation(int[] nums,int a,int b){
        int temp=nums[a];
        nums[a]=nums[b];
        nums[b]=temp;
    }
}
