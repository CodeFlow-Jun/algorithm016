package Week08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reverse_Pairs_493 {
    int N;
    long[] tr;
    public int reversePairs(int[] nums) {
        List<Long> ys = new ArrayList();
        for(int i: nums) {
            ys.add((long)i);
            ys.add((long)i * 2);
        }
        Collections.sort(ys);
        ys = unique(ys);
        N = ys.size();
        tr = new long[N + 1];
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            long target = (long)nums[i] * 2;
            int left = binaryFind(ys, target) + 1;
            int right = N;
            ans += query(right) - query(left);
            add(binaryFind(ys, nums[i]) + 1, 1);
        }
        return ans;
    }

    public void add(int x, int c){
        for(int i = x; i <= N; i += lowBit(i)) tr[i] += c;
    }

    public int query(int x){
        int res = 0;
        for(int i = x; i > 0; i -= lowBit(i)) res += tr[i];
        return res;
    }

    public int lowBit(int x){return x & -x;}



    public List<Long> unique(List<Long> list){
        List<Long> res = new ArrayList(list.size());
        for(int i = 0; i < list.size(); i++){
            if(res.isEmpty() || res.get(res.size() - 1) - list.get(i) != 0){
                res.add(list.get(i));
            }
        }
        return res;
    }

    public int binaryFind(List<Long> list, long target){
        int l = 0, r = list.size() - 1;
        while(l < r){
            int mid = l + r >> 1;
            if(list.get(mid) >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}