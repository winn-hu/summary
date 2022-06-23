package algorithm;

/**
 * 二分法查找
 */
public class Dichotomy {

    public static void main(String[] args) {
        int[] nums = new int[]{-2,4,6,9,12,17,24};
        int idx = find(nums, 0, nums.length - 1, 10);
        System.out.println(idx);
    }

    public static int find(int[] nums, int head, int tail,int target) {
        if(head <= tail) {
            int mid = (head + tail) / 2;
            if(nums[mid] < target) {
                return find(nums, mid+1, tail, target);
            } else if (nums[mid] > target) {
                return find(nums, head, mid-1, target);
            } else {
                return mid;
            }
        } else {
            return -head;
        }

    }
}
