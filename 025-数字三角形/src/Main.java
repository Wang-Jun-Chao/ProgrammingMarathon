import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-13 17:06
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[][] matrix = new int[n][];
            for (int i = 1; i <= n; i++) {
                matrix[i - 1] = new int[i];
                for (int j = 0; j < i; j++) {
                    matrix[i - 1][j] = scanner.nextInt();
                }
            }

            System.out.println(maxSum(matrix));
        }

        scanner.close();
    }

    private static int maxSum(int[][] matrix) {

        int row = matrix.length;

        // 求第一列和(i, i)
        for (int i = 1; i < row; i++) {
            matrix[i][0] += matrix[i - 1][0];
            matrix[i][i] += matrix[i - 1][i - 1];
        }


        for (int i = 2; i < row; i++) {
            for (int j = 1; j < i; j++) {
                matrix[i][j] += Math.max(matrix[i - 1][j - 1], matrix[i - 1][j]);
            }
        }

        int max = Integer.MIN_VALUE;

        // 找最大值
        for (int i = 0; i < row; i++) {
            if (max < matrix[row - 1][i]) {
                max = matrix[row - 1][i];
            }
        }
        return max;
    }
}
