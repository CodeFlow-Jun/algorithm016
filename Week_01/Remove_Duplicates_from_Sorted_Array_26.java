package Week01;

public class Remove_Duplicates_from_Sorted_Array_26 {
    public int removeDuplicates(int[] nums) {
        // 前后双指针法
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int j = 0;
        int i = 1; //i = 0 也ok
        while (i < nums.length) {
            if (nums[i] != nums[j]) {
                nums[j+1] = nums[i];
                j++;
            }
            i++;
        }
        return j + 1;
    }
}
