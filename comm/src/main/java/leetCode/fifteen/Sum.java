package leetCode.fifteen;

import java.util.*;

/**
 * 三重for循环 + map去重
 */
public class Sum {

    public static List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        Map<String,List<Integer>> map = new HashMap<>();
        for(int i=0; i<len-2; i++) {
            for(int j=i+1; j<len-1;j++) {
                for(int k=j+1; k<len; k++) {
                    if(nums[i]+nums[j]+nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        StringBuilder key = new StringBuilder();
                        for(int m=0; m<3; m++) {
                            key.append(list.get(m));
                        }
                        map.put(key.toString(),list);
                    }
                }
            }
        }
        map.forEach((key,value) -> result.add(value));

        return result;
    }
}
