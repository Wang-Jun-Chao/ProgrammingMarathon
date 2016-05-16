import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 19:14
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();

            System.out.println(split(s));
        }

        scanner.close();
    }

    /**
     * 分割字符串s，每76个字符插入一个换行符
     *
     * @param s 字符串
     * @return 分割后的字符串
     */
    private static String split(String s) {
        int i = 0;
        int n = 76;

        int len = s.length();
        StringBuilder b = new StringBuilder(s.length());
        while (i < len) {
            if (i + n <= len) {
                b.append(s.substring(i, i + n)).append('\n');
            } else {
                b.append(s.substring(i)).append('\n');
            }

            i += n;
        }

        return b.toString();
    }
}
