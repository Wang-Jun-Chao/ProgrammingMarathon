import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 16:26
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            int avg = 0;
            int[] pillar = new int[n];

            for (int i = 0; i < n; i++) {
                pillar[i] = scanner.nextInt();
                avg += pillar[i];
            }

            avg /= n;
            int move = 0;
            for (int i : pillar) {
                if (i > avg) {
                    move += i - avg;
                }
            }

            System.out.println(move);
        }

        scanner.close();
    }
}
