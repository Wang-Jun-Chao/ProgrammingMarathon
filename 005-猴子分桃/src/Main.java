import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-07 08:13
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
            if (n == 0) {
                break;
            }

            long[] r = peach(n);
            System.out.println(r[0] + " " + r[1]);
        }

        scanner.close();
    }

    /**
     * 分桃子
     * <p>
     * 解题思路：见PDF或者WORD
     *
     * @param n 小猴子的数目
     * @return 长度为二的一维数组，第一个表示最少的桃子数目，第二个表示老猴子最小可以得到的桃子数
     */
    private static long[] peach(int n) {
        long power5 = 1;
        long power4 = 1;

        for (int i = 0; i < n; i++) {
            power4 *= 4;
            power5 *= 5;
        }

        return new long[]{power5 - 4, power4 + n - 4};
    }
}
