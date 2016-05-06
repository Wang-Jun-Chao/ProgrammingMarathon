import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-06 15:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private final static int NUM = 100_000 + 1;
    private final static int[] ANS = new int[NUM];

    public static void main(String[] args) {

        init();
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            System.out.println(ANS[n]);
        }

        scanner.close();
    }

    private static void init() {

        // 标记是否是素数
        // false表示是素数
        // true表示不是素数
        // 下标为0和1的不使用
        boolean[] mark = new boolean[NUM];


        // 取出[1, NUM]中的所有素数
        int[] primes = new int[NUM];
        // 记录素数个数
        int count = 0;
        for (int i = 2; i < NUM; i++) {
            // i是质数
            if (!mark[i]) {
                // 记录素数
                primes[count] = i;
                count++;

                // 标记不是素数的
                for (int j = 2 * i; j < NUM; j += i) {
                    mark[j] = true;
                }
            }
        }

        // 求[0, NUM]中，每个数字可以用几组素数表示
        int v;
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                v = primes[i] + primes[j];
                if (v < NUM) {
                    ANS[v]++;
                }
                // 已经大于NUM说明后面的数还会大于NUM，所以要退出内层循环
                else {
                    break;
                }
            }
        }
    }
}
