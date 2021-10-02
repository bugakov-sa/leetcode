import java.util.ArrayList;
import java.util.List;

class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> permutation = new ArrayList<>();
            permutation.add(nums[0]);
            res.add(permutation);
            return res;
        }
        int[] nums2 = new int[nums.length - 1];
        System.arraycopy(nums, 1, nums2, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> permutations = permute(nums2);
            for (List<Integer> permutation : permutations) {
                permutation.add(nums[i]);
            }
            res.addAll(permutations);
            if (i < nums2.length) {
                nums2[i] = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permutations permutations = new Permutations();
        List<List<Integer>> permute = permutations.permute(nums);
        for (List<Integer> p : permute) {
            System.out.println(p);
        }
    }
}