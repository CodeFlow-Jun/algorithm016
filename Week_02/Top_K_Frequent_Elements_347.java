package Week02;

import java.util.*;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
// 示例 1:
// 输入: nums = [1,1,1,2,2,3], k = 2
// 输出: [1,2]
//
// 示例 2:
// 输入: nums = [1], k = 1
// 输出: [1]
public class Top_K_Frequent_Elements_347 {
    public int[] topKFrequent(int[] nums, int k) {
        // 使用HashMap，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 优先队列本质就是最小堆
        // 遍历map，用 最小堆 维护频率最大的k个元素
//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer a, Integer b){
//                return map.get(a) - map.get(b);  // 频次的比较
//            }
//        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (map.get(x) - map.get(y)));
        // 频次越低，优先级越高
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        int[] res = new int[k];
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            res[i] = pq.remove();
        }
        return res;
    }
}
