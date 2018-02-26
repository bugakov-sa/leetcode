public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 0) {
            int res1 = 0, res2 = 0;
            int i1 = 0, i2 = 0;
            for (int i = 0; i <= total / 2; i++) {
                if (i1 < nums1.length) {
                    if (i2 < nums2.length) {
                        if (nums1[i1] < nums2[i2]) {
                            res1 = res2;
                            res2 = nums1[i1];
                            i1++;
                        } else {
                            res1 = res2;
                            res2 = nums2[i2];
                            i2++;
                        }
                    } else {
                        res1 = res2;
                        res2 = nums1[i1];
                        i1++;
                    }
                } else {
                    res1 = res2;
                    res2 = nums2[i2];
                    i2++;
                }

            }
            return ((double) (res1 + res2)) / 2;
        } else {
            int res = 0;
            int i1 = 0, i2 = 0;
            for (int i = 0; i <= total / 2; i++) {
                if (i1 < nums1.length) {
                    if (i2 < nums2.length) {
                        if (nums1[i1] < nums2[i2]) {
                            res = nums1[i1];
                            i1++;
                        } else {
                            res = nums2[i2];
                            i2++;
                        }
                    } else {
                        res = nums1[i1];
                        i1++;
                    }
                } else {
                    res = nums2[i2];
                    i2++;
                }

            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
}
