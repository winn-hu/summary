package leetCode.T53;

import java.util.Arrays;

public class Solution1 {

    public static int maxsubArray(int[] nums) {
        int ans = nums[0];
        for(int i=0; i<nums.length; i++) {
            int[] tmp = new int[nums.length-i];
            tmp[0] = nums[i];
            for(int j=1; j<nums.length-i; j++) {
                tmp[j] = tmp[j-1] + nums[i+j];
            }
            Arrays.sort(tmp);
            ans = Math.max(ans,tmp[tmp.length-1]);
        }
        return ans;
    }
}
