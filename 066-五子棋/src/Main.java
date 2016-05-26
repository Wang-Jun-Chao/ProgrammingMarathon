import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-26 19:27
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 四个方向两个一组，分别代表，右，右下，下，左下
    private final static int[] D = {0, 1, 1, 1, 1, 0, 1, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        int n = 20;
        while (scanner.hasNext()) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                board[i] = scanner.next().toCharArray();
            }

            if (checkBoard(board)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    private static boolean checkBoard(char[][] board) {
        int len = board.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] != '.' && checkBoard(board, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkBoard(char[][] board, int i, int j) {

        int len = board.length;
        char ch = board[i][j];

        int endX;
        int endY;

        // 在四个方向上进行查找
        for (int d = 0; d < D.length; d += 2) {
            // 在(i, j)的基础上只要走四步就可以了
            endX = i + D[d] * 4;
            endY = j + D[d + 1] * 4;

            // 边界条件要合法
            if (endX > -1 && endX < len && endY > -1 && endY < len) {
                int step = 0;
                // 只要比较四次就可以了
                for (int v = i + D[d], w = j + D[d + 1]; step < 4; v += D[d], w += D[d + 1], step++) {
                    // 有不满足条件的就跳出内层循环
                    if (board[v][w] != ch) {
                        break;
                    }
                }

                // 说明找找到了
                if (step >= 4) {
                    return true;
                }
            }
        }
        return false;
    }
}

