import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 09:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String s = scanner.next();
            String t = scanner.next();

            System.out.println(contain(s, t));
        }

        scanner.close();
    }

    /**
     * 求ms中包含多少个ns，实际使用了KMP算法
     *
     * @param ms 字符串
     * @param ns 字符串
     * @return 包含的个数
     */
    private static int contain(String ms, String ns) {

        int m = ms.length();
        int n = ns.length();
        int[] next = getNext(ns);

        int count = 0;

        for (int j = 0, i = -1; j < m; j++) {
            while (ns.charAt(i + 1) != ms.charAt(j) && i >= 0) {
                i = next[i];
            }

            if (ns.charAt(i + 1) == ms.charAt(j)) {
                i++;
            }

            if (i == n - 1) {
                count++;
                // 指向ns开始的位置
                i = -1;
            }
        }

        return count;
    }

    /**
     * KMP算法中求s的next数组
     *
     * @param s 字符串
     * @return next数组
     */
    private static int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];
        next[0] = -1;

        for (int j = 1, i; j < len; j++) {
            i = next[j - 1];
            while (s.charAt(i + 1) != s.charAt(j) && i >= 0) {
                i = next[i];
            }

            if (s.charAt(i + 1) == s.charAt(j)) {
                next[j] = i + 1;
            } else {
                next[j] = -1;
            }
        }

        return next;
    }


}
