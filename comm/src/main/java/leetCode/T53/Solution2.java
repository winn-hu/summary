package leetCode.T53;

public class Solution2 {

    public static int maxsubArray(int[] nums) {
        int sum = nums[0];
        for (int num : nums) {
            if(sum > 0) {
                sum += num;
            }else {
                sum = Math.max(sum,num);
            }
        }
        return sum;
    }
}
