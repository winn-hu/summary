package leetCode.fifteen;

import java.util.*;

/**
 * ���� + ˫��forѭ�� + ָ��
 */
public class Sum3 {

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
            if(i>0 && nums[i] == nums[i-1]) {
                continue;
            }

            for(int j=i+1; j<len-1;j++) {
                if(j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                int k = len-1;
                while (k > j && nums[i]+nums[j]+nums[k] > 0) {
                    k--;
                }

                if(k != j && nums[i]+nums[j]+nums[k] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
            }
        }

        return result;
    }
}
