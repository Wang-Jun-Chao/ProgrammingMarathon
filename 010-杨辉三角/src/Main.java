import java.math.BigInteger;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-09 10:53
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int num = scanner.nextInt();

            if (num == 0) {
                break;
            }
            System.out.println(triangle2(num));
        }

        scanner.close();
    }

    /**
     * 【解法一】
     * 计算杨辉三角
     *
     * @param n 杨辉三角的行数
     * @return 杨辉三角
     */
    private static String triangle(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("参数必须是正整数");
        }


        if (n == 1) {
            return "1\n";
        } else if (n == 2) {
            return "1\n1 1\n";
        }

        StringBuilder b = new StringBuilder();

        BigInteger[][] t = new BigInteger[n][n];
        for (int i = 0; i < t.length; i++) {
            t[i] = new BigInteger[n];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    t[i][j] = new BigInteger("1");
                } else {
                    t[i][j] = t[i - 1][j - 1].add(t[i - 1][j]);
                }
                b.append(t[i][j]).append(' ');
            }

            b.setCharAt(b.length() - 1, '\n');
        }


        return b.toString();
    }

    /**
     * 【解法二】
     * 计算杨辉三角
     *
     * @param n 杨辉三角的行数
     * @return 杨辉三角
     */
    private static String triangle2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("参数必须是正整数");
        }

        BigInteger[] t = new BigInteger[n * (n + 1) / 2];
        StringBuilder b = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {

                // 第i行第一个元素的下标是: (i - 1) * i / 2
                // 第i行第j个元素的下标是：(i - 1) * i / 2 + j - 1
                int idx = (i - 1) * i / 2 + j - 1;

                if (j == 1 || i == j) {
                    t[idx] = BigInteger.ONE;
                } else {

                    // 第i-1行第一个元素的下标是: (i - 2) * (i - 1) / 2
                    int x = (i - 2) * (i - 1) / 2 + j - 1;
                    int y = (i - 2) * (i - 1) / 2 + j - 2;
                    t[idx] = t[x].add(t[y]);
                }

                b.append(t[idx].toString()).append(' ');
            }

            b.setCharAt(b.length() - 1, '\n');
        }

        return b.toString();
    }
}
