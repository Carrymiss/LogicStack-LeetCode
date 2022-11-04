package practise;

/**
 * @Author: ZTY
 * @Create: 2022-11-04
 * @Time: 9:06
 * @Description: 题目754 ：到达终点数字
 */
public class Topic_00754 {
    /**
     * 方法一 ：数学求解
     * 思路：
     * 我们将每一步能走到的所有点都列出来
     * （由于数轴的左右对称，只需要写出正数即可）：
     * 第1步：1
     * 第2步：1 3
     * 第3步：0 2 4 6
     * 第4步：0 2 4 6 8 10
     * 第5步：1 3 5 7 9 11 13 15
     * 规律：
     * 1.每一步能走到的点的序列都是[0, max]之间的所有奇数或偶数
     * 2.max=k∗(k+1)/2 （K为该序列对应的步数）
     * 这个公式也就是： 一共跳了k次，每次跳跃都是k+1。由于可以前后跳，所以k*(k+1)总是为偶数，所以要除以2计算边界值max
     * max 实际上是每一步都向正半轴跳的结果，
     * 即 max=1+2+...+kmax=1+2+...+kmax=1+2+...+k
     * 3.从 k=1k=1k=1 开始列出这个数列：1，3，6，10，15，21，28，36，45，55...
     * 可以看出：这个数列中数字的奇偶性顺序为奇、奇、偶、偶重复出现。
     * <p>
     * 分析：
     * 我们能找到最小的 k，使得 maxk>=target，再判断当前的maxk是否与 target的奇偶性相同。
     * 如果相同，则k即为最终结果；如果不相同，则向后检查 maxk+1和maxk+2
     * 如何找出最小的k值：
     * 根据 求根公式 找出最小的k值即可 (忘了可以自行百度，只需要考虑+的结果就可以了)
     * <p>
     * 至此，我们可以得出求解该问题的一般步骤：
     * 1.将 target 取绝对值
     * 2.计算 kmin（将上述不等式右边的结果向下取整，
     * 计算此时的 maxk 是否等于 target，如果不是则使 k+1）
     * 3.判断 target奇偶性是否与 max相同，
     * 如果相同，直接输出此时的 k，否则，继续检查 maxk+1和maxk+2
     */
    public int reachNumber01(int target) {
        // 由于轴对称性，跳到负数的次数和正数次数是一样的
        target = Math.abs(target);
        if (target == 0) {
            return 0;
        }
        // 跟据求根公式推出k
        // 注意： 8*target 会超过 int 范围
        // 需要用 ceil转换一下
        // 注意：要写成8L 要不超范围会输出0
        int k = (int) Math.ceil((-1 + Math.sqrt(1 + 8L * target)) / 2);
        // 根据规律找的边界max的公式
        int max = k * (k + 1) / 2;

        // 当max在目标值左边的时候，需要再跳一步
        if (max < target) {
            max += (k + 1);
            k++;
        }

        // 根据上面规律可知，要不全为奇数，要不全为偶数
        // 而且奇偶之间相同2次循环
        // 所以需要判断2次
        if ((max & 1) == (target & 1)) {
            return k;
        }

        // 再跳一次
        max += (k + 1);
        k++;
        // 再判断一次
        if ((max & 1) == (target & 1)) {
            return k;
        }
        // 这里其实是第三次跳跃， 相当于省略求边界值，直接让跳跃数加1 输出即可
        return k + 1;
    }

    /**
     * 方法2 ：优化一下上面对代码
     */
    public int reachNumber02(int target) {
        target = Math.abs(target);
        int k = (int) Math.ceil((-1 + Math.sqrt(1 + 8L * target)) / 2);
        // 注意最后的k%2这个设计
        return (k * (k + 1) / 2 - target) % 2 == 0 ? k : k + 1 + k % 2;
    }
}
