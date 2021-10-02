public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            i++;
        }
        int leftCount = i;
        int j = intervals.length - 1;
        while (j >= 0 && intervals[j][0] > newInterval[1]) {
            j--;
        }
        int rightCount = intervals.length - j - 1;
        if (j >= i) {
            if (intervals[i][0] < newInterval[0]) {
                newInterval[0] = intervals[i][0];
            }
            if (intervals[j][1] > newInterval[1]) {
                newInterval[1] = intervals[j][1];
            }
        }
        int[][] res = new int[leftCount + 1 + rightCount][2];
        System.arraycopy(intervals, 0, res, 0, leftCount);
        System.arraycopy(intervals, j + 1, res, leftCount + 1, rightCount);
        res[leftCount] = newInterval;
        return res;
    }

    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        int[][] res = insertInterval.insert(intervals, newInterval);
        for(int[] r : res) {
            System.out.print("[" + r[0] + "," + r[1] + "] ");
        }
    }
}
