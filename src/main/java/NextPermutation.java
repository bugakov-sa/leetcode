public class NextPermutation {
    private void swap(int[] nums, int l, int r) {
        int temp = nums[r];
        nums[r] = nums[l];
        nums[l] = temp;
    }

    private void reverse(int[] nums, int start) {
        for (int l = start, r = nums.length - 1; r > l; r--, l++) {
            swap(nums, l, r);
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {5, 4, 7, 5, 3, 2};
        nextPermutation.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
