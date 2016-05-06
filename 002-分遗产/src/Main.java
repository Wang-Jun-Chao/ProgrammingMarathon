import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-06 11:46
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if (line.contains("0 0")) {
                break;
            }

            String[] parts = line.split("(\\s)+");
            // 马匹数
            long hours = Long.parseLong(parts[0]);
            // 儿子数
            int num = Integer.parseInt(parts[1]);

            line = scanner.nextLine();
            parts = line.split("(\\s)+");

            int[] arr = new int[parts.length];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }

            System.out.println(heritage(hours, arr));
        }

        scanner.close();
    }

    /**
     * 分遗产
     *
     * @param hours 遗产中的马匹数
     * @param arr   每个儿子所得马匹的几分之几
     * @return 遗产划分结果
     */
    private static String heritage(long hours, int[] arr) {

        // 其实hours可以不使用

        long v = arr[0];
        // 求数组中所有元素的最小公倍数
        for (int i = 1; i < arr.length; i++) {
            v = lcm(v, arr[i]);
        }

        long sum;
        long[] result = new long[arr.length];
        for (int times = 1; ; times++) {
            sum = 0;

            for (int i = 0; i < arr.length; i++) {
                result[i] = times * v / arr[i];
                sum += result[i];
            }

            if (sum >= hours) {
                break;
            }
        }

        if (sum != hours) {
            return "Can't Solve";
        } else {
            StringBuilder b = new StringBuilder();
            for (long i : result) {
                b.append(i).append(' ');
            }

            return b.substring(0, b.length() - 1);
        }
    }

    /**
     * 求最小公倍数
     *
     * @param a 正整数
     * @param b 正整数
     * @return 最小倍约数
     */
    private static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    /**
     * 求最大公约数
     *
     * @param a 正整数
     * @param b 正整数
     * @return 最大公约数
     */
    private static long gcd(long a, long b) {

        long t;
        // 辗转相除
        while ((t = a % b) != 0) {
            a = b;
            b = t;
        }

        return b;
    }

}
