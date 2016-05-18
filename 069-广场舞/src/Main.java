import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-18 10:43
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[][] matrix = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            List<String> rst = findSaddlePoint2(matrix);

            if (!rst.isEmpty()) {
                for (String s : rst) {
                    System.out.println(s);
                }
            } else {
                System.out.println("No Point");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static List<String> findSaddlePoint(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        List<String> rst = new ArrayList<>();

        // 找每一行的最大值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int v = matrix[i][j];
                boolean flag = true;
                for (int k = 0; k < col; k++) {
                    if (v < matrix[i][k]) {
                        flag = false;
                        break;
                    }
                }
                // v是一行中最大的
                if (flag) {
                    for (int k = 0; k < row; k++) {
                        if (v > matrix[k][j]) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    rst.add((i + 1) + " " + (j + 1));
                }
            }
        }

        return rst;
    }

    private static List<String> findSaddlePoint2(int[][] matrix) {
        List<String> rst = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;


        HashMap<Integer, Integer> rowMax = new HashMap<>();
        HashMap<Integer, Integer> colMin = new HashMap<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int num = matrix[i][j];
                if (!rowMax.containsKey(i)) {
                    rowMax.put(i, num);
                } else if (rowMax.get(i) < num) {
                    rowMax.put(i, num);
                }

                if (!colMin.containsKey(j)) {
                    colMin.put(j, num);
                } else if (colMin.get(j) > num) {
                    colMin.put(j, num);
                }
            }
        }

        // 接下来需要做的就是遍历数组，然后寻找鞍点
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == rowMax.get(i) && matrix[i][j] == colMin.get(j)) {
                    rst.add((i + 1) + " " + (j + 1));
                }
            }
        }
        return rst;
    }
}
