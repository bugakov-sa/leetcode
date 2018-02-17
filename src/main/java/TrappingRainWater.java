//https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int sum = 0;
        for (int i = 2; i < height.length; i++) {
            int currHeight = height[i];
            int prevHeight = height[i - 1];
            if (currHeight > prevHeight) {
                int minHeight = height[i - 1] + 1;
                int startIndex = i - 2;
                int leftBorder;
                do {
                    leftBorder = findFromRightToLeft(height, minHeight, startIndex, 0);
                    if (leftBorder >= 0) {
                        int heightDiff = Math.min(height[leftBorder], currHeight) - (minHeight - 1);
                        sum += (i - leftBorder - 1) * heightDiff;
                        minHeight = height[leftBorder] + 1;
                        startIndex = leftBorder - 1;
                    }
                } while (leftBorder > 0 && height[leftBorder] < currHeight);
            }
        }
        return sum;
    }

    private int findFromRightToLeft(int[] height, int minHeight, int startIndex, int endIndex) {
        for (int j = startIndex; j >= endIndex; j--) {
            if (height[j] >= minHeight) {
                return j;
            }
        }
        return -1;
    }
}

