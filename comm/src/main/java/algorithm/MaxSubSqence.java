package algorithm;

/**
 * 最大子段和
 * 算法1：遍历
 * 算法2：分治法(divide-and-conquer)
 */
public class MaxSubSqence {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,11,-4,13,-5,-2,9};
        foreach(nums);
        int result = divideConquer(nums, 0, nums.length - 1);
        System.out.println(result);
    }

    private static void foreach(int[] nums) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum = sum < 0 ? 0 : sum;
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }


    private static int divideConquer(int[] nums, int head, int tail) {
        int sum;
        if(head == tail) {
            sum = Math.max(0, nums[head]);
        } else {
            int mid = (head+tail)/2;
            int left = divideConquer(nums, head, mid);
            int right = divideConquer(nums, mid+1, tail);
            int leftsum = 0, maxleft = 0;
            for (int i = mid; i >= head; i--) {
                leftsum += nums[i];
                maxleft = Math.max(maxleft, leftsum);
            }

            int rightsum = 0, maxRight = 0;
            for (int i = mid+1; i <= tail; i++) {
                rightsum += nums[i];
                maxRight = Math.max(maxRight, rightsum);
            }

            sum = Math.max(Math.max(maxleft + maxRight, left),right);

        }
        return sum;
    }
}
