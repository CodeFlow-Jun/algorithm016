package Week01;

public class Trapping_Rain_Water_42 {
    // 双指针夹逼相遇法
    public int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (leftMax < rightMax) {
                // 将当前高度和挡板比较
                res += leftMax - height[left] > 0 ?
                        leftMax-height[left] : 0;
                // 维护 挡板 和 指针
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                res += rightMax - height[right] > 0 ?
                        rightMax - height[right] : 0;
                // 维护 挡板 和 指针
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return res;
    }
}
