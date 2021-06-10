public class RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new RotateImage().rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        int size = matrix[0].length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = i; j < size - i - 1; j++) {
                int i1 = i, j1 = j;
                int i2 = j, j2 = size - i - 1;
                int i3 = size - i - 1, j3 = size - j - 1;
                int i4 = size - j - 1, j4 = i;
                int temp = matrix[i4][j4];
                matrix[i4][j4] = matrix[i3][j3];
                matrix[i3][j3] = matrix[i2][j2];
                matrix[i2][j2] = matrix[i1][j1];
                matrix[i1][j1] = temp;
            }
        }
    }
}
