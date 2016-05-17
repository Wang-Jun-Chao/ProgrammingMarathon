import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 18:31
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String seat = scanner.next();
            int week = scanner.nextInt();

            System.out.println(exchange(seat, week));

        }

        scanner.close();
    }

    private static String exchange(String seat, int week) {

        if (week == 0) {
            return seat;
        }

        // 向右移动
        if (week > 0) {
            week %= seat.length();
            week = seat.length() - week;
        }
        // 向左移动转换成向右移动
        else {
            week = week % seat.length();
            week = -week;
        }

        StringBuilder b = new StringBuilder(seat.length());

        for (int i = week - 1; i >= 0; i--) {
            b.append(seat.charAt(i));
        }

        for (int i = seat.length() - 1; i >= week; i--) {
            b.append(seat.charAt(i));
        }

        for (int i = 0, j = b.length() - 1; i < j; i++, j--) {
            char c = b.charAt(i);
            b.setCharAt(i, b.charAt(j));
            b.setCharAt(j, c);
        }

        return b.toString();
    }


}
