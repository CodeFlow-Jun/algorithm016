package Week01;
// 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
// 示例:
// 给定 nums = [2, 7, 11, 15], target = 9
//
// 因为 nums[0] + nums[1] = 2 + 7 = 9
// 所以返回 [0, 1]
//
// Related Topics 数组 哈希表

import java.util.Arrays;

public class Two_Sum_1 {
//    public int[] twoSum(int[] nums, int target) {
//        //暴力解法
//        int[] a = new int[2];
//        for (int i = 0; i < nums.length-1; i++) {
//            for (int j = i+1; j<nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    a[0] = i;
//                    a[1] = j;
//                }
//            }
//        }
//        return a;
//    }
    public int[] twoSum(int[] nums, int target) {
        // 双指针夹逼法
        int[] tmp = nums.clone();
        Arrays.sort(nums);
        int[] arr = new int[2];
        int sz = nums.length;
        int l = 0, r = sz - 1;
        while (l < r) {
            if (nums[l] + nums[r] > target){
                r --;
            } else if (nums[l] + nums[r] < target) {
                l ++;
            } else {
                break;
            }
        }
        int i = 0;
        for (i = 0; i < tmp.length; i++) {
            if (nums[l] == tmp[i]) {
                arr[0] = i;
                break;
            }
        }
        for (int j = 0; j < tmp.length; j++) {
            if (nums[r] == tmp[j] && j != i) { // j != i：排查两个重复值的情况
                arr[1] = j;
                break;
            }
        }
        return arr;
    }
}
