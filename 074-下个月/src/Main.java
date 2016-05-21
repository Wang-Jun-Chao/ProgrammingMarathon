import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-20 22:29
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (scanner.hasNext()) {
            int year = scanner.nextInt();
            int month = scanner.nextInt();
            int day = scanner.nextInt();
            int num = scanner.nextInt();
            System.out.println(nextMonth(year, month, day, num));
        }

        scanner.close();
    }

    /**
     * 求下一个月份的日期
     *
     * @param year  年份
     * @param month 月份
     * @param day   日期
     * @param num   月数
     * @return 下一个月份的日期
     */
    private static String nextMonth(int year, int month, int day, int num) {
        // 当前年份的月天数情况
        int[] curr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 所求年份的月天数情况
        int[] next = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            curr[1] = 29;
        }


        int nextMonth = month + num;
        int nextYear = year + (nextMonth - 1) / 12;
        nextMonth = (nextMonth - 1) % 12 + 1;
        int nextDay = day;

        if (isLeapYear(nextYear)) {
            next[1] = 29;
        }

        // 如果是一个月的最后一天
        if (curr[month - 1] == day) {
            nextDay = next[nextMonth - 1];
        }
        // 如果不是一个月的最后一天
        else {
            // 如果目标月份的日期最后一天比开始日期的天数少
            // 比如2015 01 30 1
            if (next[nextMonth - 1] < day) {
                nextDay = next[nextMonth - 1];
            }
        }

        return nextYear + " " + nextMonth + " " + nextDay;
    }

    /**
     * 判断是否是闰年
     *
     * @param year 年份
     * @return true：是闰年，false：不是闰年
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
