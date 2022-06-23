package leetCode.T213;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
       int res = rob(new int[]{5});
        System.out.println(res);
    }

    public static int rob(int[] nums) {
        //ȥ����һ�����ҳ�����
        int[] noStart = Arrays.copyOfRange(nums,1,nums.length);
        int start = rangeBob(noStart);
        //ȥ�����һ�����ҳ�����
        int[] noEnd = Arrays.copyOfRange(nums,0,nums.length-1);
        int end = rangeBob(noEnd);
        //��ȡ���ֵ
        return Math.max(start,end);
    }

    private static int rangeBob(int[] nums) {
        if(nums.length == 0) return 0;

        int tmp, first = 0, second =nums[0];
        for(int i = 2, len = nums.length; i <= len; i++) {
            tmp = second;
            second = Math.max(first+nums[i-1], tmp);
            first = tmp;
        }
        return second;
    }
}
