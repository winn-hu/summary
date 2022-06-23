package algorithm;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 思想：
 * 1)拆分数组
 * 2)合并两个已排序的数组
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[]{5,15,3,8,45,7,9,2};
        split(nums, 0, nums.length-1);
    }


    public static void split(int[] nums, int head, int tail) {
         if(head < tail) {
             int mid = (tail + head)/2;
             split(nums,head,mid);
             split(nums,mid+1,tail);
             sort(nums, head, mid, tail);
         }

    }

    public static void sort(int[] nums, int head, int mid, int tail) {
        int[] left =  Arrays.copyOfRange(nums, head, mid+1);
        int[] right = Arrays.copyOfRange(nums, mid+1, tail+1);
        int i = 0, j = 0, k = head;
        //int[] result = new int[left.length+right.length];
        while(i < left.length && j < right.length) {
            if(left[i] < right[j]) {
                nums[k++] = left[i++];
            }else {
                nums[k++] = right[j++];
            }
        }
        for (int l = i; l < left.length; l++) {
            nums[k++] = left[l];
        }
        for (int l = j; l < right.length; l++) {
            nums[k++] = right[l];
        }
        System.out.println(Arrays.toString(nums));
    }


}
