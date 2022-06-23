package leetCode.T35;

public class SearchInsert {

    public static void main(String[] args) {
        int i = searchInsert(new int[]{1, 2, 3, 5, 6}, 0);
        System.out.println(i);
    }

    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int idx= len/2;

        while(true) {
            if(target == nums[idx]) {
                return idx;
            }else if(target < nums[idx]) {
                if(idx <= 0) return 0;
                if( target > nums[idx-1]) {
                    return idx;
                }else if(target < nums[idx-1]) {
                    idx = idx/2;
                }else {
                    return idx-1;
                }
            }else {
                if(idx+1 > len-1) return len;
                if(target > nums[idx+1]) {
                    idx = idx+(len-idx)/2;
                } else {
                    return idx+1;
                }
            }
        }
    }
}
