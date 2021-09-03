public class Demo {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length+nums1.length];
        int res_idx = 0;
        int nums1_idx = 0;
        int nums2_idx = 0;
        while(nums1_idx < nums1.length && nums2_idx < nums2.length) {
            res[res_idx++] = (nums1[nums1_idx] <= nums2[nums2_idx]
                    ? nums1[nums1_idx++] : nums2[nums2_idx++]);
        }
        if(nums1_idx == nums1.length-1) {
            for(int i=nums2_idx;i<nums2.length;i++) {
                res[res_idx++] = nums2[i];
            }
        }else {
            for(int i=nums1_idx;i<nums1.length;i++) {
                res[res_idx++] = nums1[i];
            }
        }

        if(res.length%2 != 0) {
            return res[res.length/2]/1.0;
        }else {
            return (res[res.length/2] + res[res.length/2-1])/2.0;
        }
    }
}
