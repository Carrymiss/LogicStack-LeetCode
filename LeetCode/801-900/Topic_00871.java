package practise;

import java.util.PriorityQueue;

/**
 * @Author: ZTY
 * @Create: 2023/2/14
 * @Time: 9:52
 * @Description: 题目871 ：最低加油次数
 */
public class Topic_00871 {
    /**
     * 方法一：贪心+优先队列
     * <p> 想象成不是只在加油站才能加油，而是只要现在需要油，并且之前有加油站
     * <p> 还没有加油，那么此时就可以加油。这样一来，如果要使得加油次数最少，那么
     * <p> 只要加油就加最多的油，为了保证时间效率，这里用堆来维护前面的未用过的加油站
     * <p>  里的油量。需要加油而没有油时(也就是堆为空)，那么就不能够到达，此时返回-1。
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations.length == 0) {
            return startFuel >= target ? 0 : -1;
        }
        // 创建优先队列 数字大的优先级高
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        int ans = 0, sum = startFuel;
        for (int i = 0; i < stations.length; i++) {
            // 放入能放入的所有汽油
            while (sum < stations[i][0]) {
                Integer a = queue.poll();
                if (a == null) {
                    return -1;
                }
                sum += a;
                ans++;
            }
            queue.offer(stations[i][1]);
        }
        // 过完所有加油站 比较目的地
        // 这里小于等于都能到到达
        while (sum < target) {
            Integer a = queue.poll();
            if (a == null) {
                return -1;
            }
            sum += a;
            ans++;
        }
        return ans++;
    }
}
