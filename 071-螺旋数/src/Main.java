import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-19 07:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    int top = Math.min(x, n - 1 - x);
                    int left = Math.min(y, n - 1 - y);
                    int round = Math.min(left, top);
                    top = round;
                    left = round;
                    int right = n - round - 1;
                    int bottom = right;

                    int start = n * n - (n - 2 * round) * (n - 2 * round) + 1;

                    int inc = 0;
                    if (x == top) {
                        inc = y - round;
                    } else if (x == bottom) {
                        inc = 2 * (n - 2 * round - 1) + right - y;
                    } else {
                        if (y == right) {
                            inc = n - 2 * round - 1 + x - top;
                        } else if (y == left) {
                            inc = 3 * (n - 2 * round - 1) + bottom - x;
                        }
                    }

                    System.out.print(start + inc);
                    if (y != n - 1) {
                        System.out.print(' ');
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        scanner.close();
    }
}
