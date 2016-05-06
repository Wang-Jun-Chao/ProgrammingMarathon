import java.util.Scanner;

/**
 * Author: 王俊超
 * Date: 2016-01-13 13:54
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            if (n >= 1 && n <= 10_000_000) {
                System.out.println(guess(n));
            }
        }

        scanner.close();
    }

    /**
     * 求[1, n]中素数的个数，使用标记法
     *
     * @param n 最大的范围
     * @return 素数个数
     */
    private static int guess(int n) {

        // 标记是否是素数
        // false表示是素数
        // true表示不是素数
        boolean[] mark = new boolean[n + 1];

        mark[0] = true;
        mark[1] = true;

        for (int i = 2; i < mark.length; i++) {
            // i是质数
            if (!mark[i]) {
                for (int j = 2 * i; j < mark.length; j += i) {
                    mark[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < mark.length; i++) {
            if (!mark[i]) {
                count++;
            }
        }

        return count;
    }
}
