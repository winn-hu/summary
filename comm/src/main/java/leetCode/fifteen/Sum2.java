package leetCode.fifteen;

import java.util.*;

/**
 * 排序 + 三重for循环
 */
public class Sum2 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        int len = nums.length;

        //排序
        Arrays.sort(nums);
        //全为正或者全为负都不符合要求
        if(nums.length < 3 || nums[0] > 0 || nums[len-1] < 0) return result;

        for(int i=0; i<len-2; i++) {
            //第一个数字必须小于等于0
            if(nums[i]>0) break;
            //如果当前元素与上个元素相同，不再遍历
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }

            for(int j=i+1; j<len-1;j++) {
                if(j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                for(int k=len-1; k > j; k--) {
                    if(nums[i]+nums[j]+nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                        break;
                    }else if(nums[i]+nums[j]+nums[k] < 0) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
