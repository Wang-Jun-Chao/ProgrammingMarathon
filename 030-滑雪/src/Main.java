import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 10:36
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 某个点可以移动的四个方向，两个两个一组
    private final static int[] D = {-1, 0, 0, 1, 1, 0, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] matrix = new int[row][col];

            for (int i = 0; i < row; i++) {
                matrix[i] = new int[col];
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            System.out.println(maxLength(matrix));
        }

        scanner.close();
    }

    /**
     * 求最大的下滑长度
     *
     * @param matrix 区域海拔
     * @return 最大的下滑长度
     */
    private static int maxLength(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // 创建记录高度的二维数组，初始值为0
        int[][] length = new int[row][col];
        for (int i = 0; i < row; i++) {
            length[i] = new int[col];
        }


        // 对每一个点进行深度优先遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(i, j, matrix, length);
            }
        }

        // 找最大值
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (max < length[i][j]) {
                    max = length[i][j];
                }
            }
        }

        return max;
    }

    /**
     * 深度优先遍历
     *
     * @param i      行
     * @param j      列
     * @param matrix 区域海拔
     * @param length 记录每个点可以滑动的最长距离
     */
    private static void dfs(int i, int j, int[][] matrix, int[][] length) {

        int row = matrix.length;
        int col = matrix[0].length;

        // 如果坐标不合法，或者已经计算过了就返回
        if (i < 0 || i >= row || j < 0 || j >= col || length[i][j] > 0) {
            return;
        }

        int max = 0;
        for (int k = 0; k < D.length; k += 2) {
            int x = i + D[k];
            int y = j + D[k + 1];

            // 处理(x, y)点的四个方向
            if (x >= 0 && x < row && y >= 0 && y < col && matrix[i][j] > matrix[x][y]) {
                dfs(x, y, matrix, length);

                // 记录最大值
                if (length[x][y] > max) {
                    max = length[x][y];
                }
            }
        }

        // 记录当前点可以滑动的最长距离，1是当前点的长度
        length[i][j] = 1 + max;

    }

}
