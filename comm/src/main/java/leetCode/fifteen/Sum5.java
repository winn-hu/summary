package leetCode.fifteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * forѭ��+˫ָ��
 */
public class Sum5 {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        int len = nums.length;
        //ȫΪ������ȫΪ����������Ҫ��
        if(nums.length < 3 || nums[0] > 0 || nums[len-1] < 0) return result;

        for(int i=0; i<len-2; i++) {
            //��һ�����ֱ���С�ڵ���0
            if(nums[i]>0) break;

            //�����ǰԪ�����ϸ�Ԫ����ͬ�����ٱ���
            if(i>0 && nums[i] == nums[i-1]) continue;

            int j = i+1;
            int k = len-1;

            while(k > j) {
                if(nums[i]+nums[j]+nums[k] < 0) {
                    j++;
                }else if (nums[i]+nums[j]+nums[k] > 0) {
                    k--;
                }else {
                    //�������һ����ͬ
                    if (nums[j] != nums[j - 1] || (k + 1 == len || nums[k] != nums[k + 1])) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        result.add(list);
                    }
                    j++;
                    k--;
                }
            }
        }

        return result;
    }
}
