import java.util.*;

public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums, boolean[] flags) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(!flags[i]) {
                flags[i] = true;
                List<List<Integer>> lists = permuteUnique(nums, flags);
                flags[i] = false;
                if (lists.isEmpty()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    res.add(list);
                }
                for (List<Integer> list : lists) {
                    list.add(nums[i]);
                    res.add(list);
                }
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        return permuteUnique(nums, new boolean[nums.length]);
    }

    public static void main(String[] args) {
        new Permutations2().permuteUnique(new int[]{1, 2, 1})
                .forEach(list -> {
                    list.forEach(i -> System.out.print(i + " "));
                    System.out.println();
                });
    }
}
