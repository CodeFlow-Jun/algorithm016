// 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
// 示例:
// 输入: n = 10
// 输出: 12
// 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
package Week02;

import java.util.PriorityQueue;
import java.util.Queue;

public class Ugly_Number_LCOF_49 {
    public int nthUglyNumber(int n) {
        // 任何丑数乘以2、3、5，其结果也是丑数
        // 利用 最小堆，将1作为第一个丑数
        // 每次从小根堆弹出最小的丑数，然后记录已弹出丑数的个数
        // 如果count>=n,返回当前弹出的元素，否则继续乘以2、3、5
        // 注意：放入堆里的元素需要排除重复值
        int[] uglyNumber = {2, 3, 5};
        // 创建小根堆，每次出堆的都是最小值
        Queue<Long> queue = new PriorityQueue<>();
        queue.add(1L);
        // 记录出堆的个数，出堆的元素完全按照从小到大排序
        int count = 0;
        while (! queue.isEmpty()) {
            long cut = queue.poll();

            // 如果出堆的个数>=n,当前cut就是第n个丑数
            if (++count >= n) {
                return (int)cut;
            }
            for (int num : uglyNumber) {
                // 排除重复的数字
                if (! queue.contains(num * cut)) {
                    queue.add(num * cut);
                }
            }
        }
        return -1;
    }
}
