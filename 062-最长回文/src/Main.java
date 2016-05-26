import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-26 17:02
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(longestPalindrome(line));
        }

        scanner.close();
    }

    /**
     * 找最长回访主序列
     *
     * @param s 输入字符串
     * @return 回文字符串，输入为空返回为null
     */
    private static String longestPalindrome(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        int max = 0;
        String rst = null;

        int length = s.length();

        boolean[][] table = new boolean[length][length];

        // 一个字符都是回文
        for (int i = 0; i < length; i++) {
            table[i][i] = true;
        }

        // 判断两个是不是回文
        for (int i = 0, j = length - 1; i < j; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;

                // 记录第一个长度为2的回文字符串
                if (max < 2) {
                    max = 2;
                    rst = s.substring(i, i + 2);
                }
            }
            // 不是就标记为false
            else {
                table[i][i + 1] = false;
            }
        }

        // 求长度是3或者以上长度的字符串中，最长的回文
        for (int k = 3; k <= length; k++) {
            for (int i = 0, j; (j = i + k - 1) < length; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    table[i][j] = table[i + 1][j - 1];

                    // 记长度最长的回文字符串，相同的记录第一个
                    if (table[i][j] && max < k) {
                        max = k;
                        rst = s.substring(i, j + 1);
                    }
                } else {
                    table[i][j] = false;
                }
            }
        }

        // 说明长度最多为1，取第一个
        if (rst == null) {
            rst = s.substring(0, 1);
        }

        return rst;
    }
}
