import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-09 10:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String num = scanner.next();
            System.out.println(solve(num));
        }

        scanner.close();
    }

    /**
     * 求数字的数根
     *
     * @param num 字符串表示的数字
     * @return 数根
     */
    private static int solve(String num) {
        return 0;
    }
}
