import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 08:25
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
            int k = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            String s = scanner.next();

            System.out.println(encrypt(s, arr, k));
        }

        scanner.close();
    }

    private static String encrypt(String s, int[] arr, int k) {

        int len = arr.length;
        char[] chars = new char[len];
        char[] temps = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = ' ';
            temps[i] = ' ';
        }

        // 第一次加密
        for (int i = 0, j = s.length(); i < j; i++) {
            chars[arr[i] - 1] = s.charAt(i);
        }

        // [2, k]次加密
        for (int t = 1; t < k; t++) {
            for (int i = 0; i < len; i++) {
                temps[arr[i] - 1] = chars[i];
            }

            char[] chs = temps;
            temps = chars;
            chars = chs;
        }

        return new String(chars);
    }
}
