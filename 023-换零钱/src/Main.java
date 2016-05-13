import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-13 15:15
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 硬币可以供选择的面值
    private final static int[] T = {1, 5, 10, 25, 50};

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

//            System.out.println(exchange(n, T.length));
            System.out.println(exchange(n));
        }

        scanner.close();
    }


    /**
     * 解法一：递归解法
     * 找零操作
     *
     * @param n 当前要找的零钱数
     * @param m 可以选择的硬币种类T[0]~T[m-1]
     * @return 不的找零数目
     */
    private static int exchange(int n, int m) {

        if (n == 0) {
            return 1;
        } else if (n < 0 || m <= 0) {
            return 0;
        } else {
            return exchange(n - T[m - 1], m) + exchange(n, m - 1);
        }
    }

    /**
     * 解法二：非递归解法
     * 找零操作
     *
     * @param n 当前要找的零钱数
     * @return 不的找零数目
     */
    private static long exchange(int n) {

        // 选择long不然可能会超出int表达范围
        long[] result = new long[n + 1];

        // 初始化
        result[0] = 1;

        // 每次增加一种硬币，且比之前硬币的面值大，计算加入新的硬币后每个数目的钱其找零数有多少种
        for (int t : T) {
            for (int j = t; j <= n; j++) {
                result[j] += result[j - t];
            }
        }
        return result[n];
    }
}
