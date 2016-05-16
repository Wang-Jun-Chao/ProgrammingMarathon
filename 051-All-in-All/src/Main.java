import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 17:08
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

            if (containSequence(s, t)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    /**
     * 判断t字符串是否是s串的子序列
     *
     * @param s 字符串
     * @param t 字符串
     * @return true：t是s是子序列，false：不是
     */
    private static boolean containSequence(String s, String t) {

        // t串比s串长，t一定不是s的子序列
        if (t.length() > s.length()) {
            return false;
        }

        int is = 0;
        int it = 0;

        int ls = s.length();
        int lt = t.length();

        while (is < ls && it < lt) {

            char ct = t.charAt(it);

            // 在s串中找第一个与t串中ct字符相等的
            while (is < ls && s.charAt(is) != ct) {
                is++;
            }

            // 没有找到说明t串不是s串的子串
            if (is >= ls) {
                return false;
            } else {
                // s串中剩余的字符数不能比t串中剩余的字符数少，否则t串不是s串的子序列
                if (ls - is >= lt - it) {
                    is++;
                    it++;
                } else {
                    return false;
                }

            }
        }

        // t串中的所有字符处理完了才说明t串是s串的子序列
        return lt == it;
    }
}
