import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 10:37
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
//        print();
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        boolean first = true;
        while (scanner.hasNext()) {
            // 保证输入的数据是1≤n≤79的奇整数
            int n = scanner.nextInt();
            char inner = scanner.next().charAt(0);
            char outer = scanner.next().charAt(0);

            if (!first) {
                System.out.println();
            }
            first = false;
            System.out.print(print(n, inner, outer));
        }
        scanner.close();
    }

    /**
     * 【方法二】
     * 打印图案，此方法要消耗更多的内存空间
     *
     * @param n     一共有n行，n列
     * @param inner 最中心的图案
     * @param outer 外面的图案
     * @return 图案字符串
     */
    private static String print(int n, char inner, char outer) {

        char[][] chars = new char[n][n];
        int round = (n + 1) / 2;
        // 如果有奇数圈
        if (round % 2 != 0) {
            for (int i = 0; i < round; i++) {

                // 求第round圈要使用的字符
                char ch = inner;
                if (i % 2 != 0) {
                    ch = outer;
                }

                // 为第round圈点上字符
                for (int x = i; x < n - i; x++) {
                    chars[i][x] = ch;
                    chars[x][i] = ch;
                    chars[n - i - 1][x] = ch;
                    chars[x][n - i - 1] = ch;
                }
            }
        } else {
            for (int i = 0; i < round; i++) {

                char ch = outer;
                if (i % 2 != 0) {
                    ch = inner;
                }

                for (int x = i; x < n - i; x++) {
                    chars[i][x] = ch;
                    chars[x][i] = ch;
                    chars[n - i - 1][x] = ch;
                    chars[x][n - i - 1] = ch;
                }
            }
        }

        // 大于1才需要处理四个角
        if (n > 1) {
            chars[0][0] = ' ';
            chars[0][n - 1] = ' ';
            chars[n - 1][0] = ' ';
            chars[n - 1][n - 1] = ' ';
        }

        StringBuilder b = new StringBuilder(n * (2 + n));
        for (char[] chs : chars) {
            b.append(chs).append('\n');
        }

        return b.toString();
    }

    /**
     * 【方法一】
     * 打印图案，此方法的计算量比较大
     */
    private static void print() {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        boolean first = true;
        while (scanner.hasNext()) {
            // 保证输入的数据是1≤n≤79的奇整数
            int n = scanner.nextInt();
            char inner = scanner.next().charAt(0);
            char outer = scanner.next().charAt(0);

            if (!first) {
                System.out.println();
            }
            first = false;

            if (n > 1) {
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        // 四个角输出空格
                        if (x == 0 && (y == 0 || y == n - 1) || x == n - 1 && (y == 0 || y == n - 1)) {
                            System.out.print(' ');
                        } else {

                            // 求当前位置是在第几圈，最外层是第0圈
                            int top = Math.min(x, n - 1 - x);
                            int left = Math.min(y, n - 1 - y);
                            int round = Math.min(left, top);

                            // 有偶数圈
                            if (((n + 1) / 2) % 2 == 0) {
                                if (round % 2 == 0) {
                                    System.out.print(outer);
                                } else {
                                    System.out.print(inner);
                                }
                            }
                            // 有奇数圈
                            else {
                                if (round % 2 == 0) {
                                    System.out.print(inner);
                                } else {
                                    System.out.print(outer);
                                }
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println(inner);
            }
        }

        scanner.close();
    }
}
