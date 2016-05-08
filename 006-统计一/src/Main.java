/**
 * Author: 王俊超
 * Time: 2016-05-07 12:14
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {

    }

    private static long countOne3(long n) {
        long count = 0;
        for (int i = 0, j; i <= n; i++) {
            j = i;
            while (j != 0) {
                if (j % 10 == 1) {
                    count++;
                }
                j = j / 10;
            }
        }
        return count;
    }
}
