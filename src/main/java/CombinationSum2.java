import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CombinationSum2 {
    List<List<Integer>> combinationSum2(int[] nums, int[] counts, int offset, int target) {
        if (offset == nums.length) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tail = new ArrayList<>();
        int subTarget = target;
        for (int i = 0; i <= counts[offset]; i++) {
            if (subTarget > 0) {
                combinationSum2(nums, counts, offset + 1, subTarget).forEach(list -> {
                    list.addAll(tail);
                    res.add(list);
                });
                subTarget -= nums[offset];
                tail.add(nums[offset]);
            }
            else {
                if (subTarget == 0) {
                    res.add(tail);
                }
                break;
            }
        }
        return res;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> dict = new java.util.HashMap<>();
        for(int num : candidates) {
            dict.compute(num, (k, v) -> (v == null) ? 1 : (v + 1));
        }
        int[] distinctNums = new int[dict.size()];
        int[] numsCounts = new int[dict.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> kv : dict.entrySet()) {
            distinctNums[i] = kv.getKey();
            numsCounts[i] = kv.getValue();
            i++;
        }
        return combinationSum2(distinctNums, numsCounts, 0, target);
    }

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        CombinationSum2 combinationSum2 = new CombinationSum2();
        List<List<Integer>> lists = combinationSum2.combinationSum2(nums, 8);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
