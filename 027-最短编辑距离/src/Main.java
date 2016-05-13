import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-13 20:12
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String s = scanner.next();
            String t = scanner.next();

            System.out.println(shortest(s, t));

        }

        scanner.close();
    }

    /**
     * 最短编辑距离
     *
     * @param s 字符串
     * @param t 字符串
     * @return 最短编辑距离
     */
    private static int shortest(String s, String t) {

        int row = s.length() + 1;
        int col = t.length() + 1;

        // 初始化
        int[][] d = new int[row][col];
        for (int i = 0; i < row; i++) {
            d[i] = new int[col];
        }

        // 设置第一列
        for (int i = 0; i < row; i++) {
            d[i][0] = i;
        }

        // 设置第一行
        for (int j = 0; j < col; j++) {
            d[0][j] = j;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                int u = d[i - 1][j] + 1;
                int v = d[i][j - 1] + 1;
                int w = d[i - 1][j - 1];
                if (s.charAt(i - 1) != t.charAt(j - 1)) {
                    w++;
                }

                d[i][j] = Math.min(u, Math.min(v, w));
            }
        }

        return d[row - 1][col - 1];
    }
}
