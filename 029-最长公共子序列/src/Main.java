import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 06:53
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String ms = scanner.next();
            String ns = scanner.next();

            System.out.println(lcs(ms, ns));
        }

        scanner.close();
    }

    /**
     * 求最长公共子序列长度
     *
     * @param ms 字符串
     * @param ns 字符串
     * @return 最长公共子序列的长度
     */
    private static int lcs(String ms, String ns) {
        int m = ms.length() + 1;
        int n = ns.length() + 1;

        // 创建二维数组，初始值为0
        int[][] d = new int[n][m];
        for (int i = 0; i < m; i++) {
            d[i] = new int[n];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                // 对应到字串串中的位置
                int x = i - 1;
                int y = j - 1;

                if (ms.charAt(x) == ns.charAt(y)) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
                }
            }
        }

        return d[m - 1][n - 1];
    }


}
