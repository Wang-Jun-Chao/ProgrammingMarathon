import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-20 20:05
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 米字型8个方向
    private final static int[] D = {-1, -1, -1, 0, -1, 1, 0, -1, 0, 1, 1, -1, 1, 0, 1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        boolean first = true;
        while (scanner.hasNext()) {

            char[][] board = new char[8][];
            for (int i = 0; i < 8; i++) {
                board[i] = scanner.next().toCharArray();
            }

            int x = scanner.nextInt();
            int y = scanner.nextInt();
            char ch = scanner.next().charAt(0);

            play(board, x, y, ch);

            for (char[] chs : board) {
                System.out.println(new String(chs));
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void play(char[][] board, int x, int y, char ch) {
        // 对应到棋盘的坐标
        x--;
        y--;
        board[x][y] = ch;

        // 在8个方向上搜索
        for (int i = 0; i < D.length; i += 2) {
            int v = x + D[i];
            int w = y + D[i + 1];

            // 在一个方向上找
            while (v >= 0 && v < 8 && w >= 0 && w < 8 && board[v][w] != ch && board[v][w] != '.') {
                v += D[i];
                w += D[i + 1];
            }

            // 在寻找的方向上找到一个
            if (v >= 0 && v < 8 && w >= 0 && w < 8 && board[v][w] == ch) {
                // 从原来的方向上回退，并且翻转
                do {
                    board[v][w] = ch;
                    v -= D[i];
                    w -= D[i + 1];
                } while (board[v][w] != ch);
            }
        }
    }
}
