package leetCode.T35;

import java.util.Arrays;

public class SearchInsert3 {

    public static void main(String[] args) {
        int i = searchInsert(new int[]{1, 3, 5, 6}, 2);
        int i1 = Arrays.binarySearch(new int[]{1, 3, 5, 6}, 4);
        System.out.println(i1);
        System.out.println(i);
    }

    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        //����Ϊ0����Ŀ��ֵС��������СԪ��ֵ������0
        if(len == 0 || target < nums[0]) return 0;
        //Ŀ��ֵ�������ֵ����������ĳ���ֵ
        if(target > nums[len-1]) return len;

        int head = 0;
        int tail = len-1;
        while(head <= tail) {
            int idx = (tail + head) >>> 1;
            if(target < nums[idx]) {
                tail = idx-1;
            }else if (target > nums[idx]) {
                head = idx+1;
            }else {
                return idx;
            }
        }



        return head;

    }
}
