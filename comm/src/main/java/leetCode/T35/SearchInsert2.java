package leetCode.T35;

public class SearchInsert2 {

    public static void main(String[] args) {
        int i = searchInsert(new int[]{1, 3, 5, 6}, 2);
        System.out.println(i);
    }

    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if(len == 0 || target < nums[0]) return 0;
        if(target > nums[len-1]) return len;

        int head = 0;
        int tail = len-1;
        int idx = (tail - head)/2;

        while(head <= tail) {
            if(target < nums[idx]) {
                tail = idx-1;
                idx = (tail - head)/2;
            }else if (target > nums[idx]) {
                head = idx+1;
                idx = (tail + head)/2;
            }else {
                return idx;
            }
        }
        return head;

    }
}
