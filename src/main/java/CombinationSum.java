import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSum {

    private List<List<Integer>> combinationSum(int[] candidates, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if(start == candidates.length || target < candidates[start]) {
            return res;
        }
        int n = target / candidates[start];
        for(int i = 0; i <= n; i++) {
            int subTarget = target - candidates[start] * i;
            List<Integer> iCopies = new ArrayList<>(Collections.nCopies(i, candidates[start]));
            if(subTarget > 0) {
                List<List<Integer>> subLists = combinationSum(candidates, subTarget, start + 1);
                for(List<Integer> subList : subLists) {
                    subList.addAll(iCopies);
                }
                res.addAll(subLists);
            }
            else {
                res.add(iCopies);
            }
        }
        return res;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates, target, 0);
    }
}
