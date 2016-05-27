import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 07:05
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int year = scanner.nextInt();
            List<String> days = holiday(year);

            for (String s : days) {
                System.out.println(s);
            }

            System.out.println();
        }

        scanner.close();
    }

    private static List<String> holiday(int year) {
        List<String> rst = new LinkedList<>();

        rst.add(year + "-01-01");
        rst.add(year + "-01-" + weekToDayZeller(year, 1, 3, 1, true));
        rst.add(year + "-02-" + weekToDayZeller(year, 2, 3, 1, true));
        rst.add(year + "-05-" + weekToDayZeller(year, 5, 1, 1, false));
        rst.add(year + "-07-04");
        rst.add(year + "-09-" + weekToDayZeller(year, 9, 1, 1, true));
        rst.add(year + "-11-" + weekToDayZeller(year, 11, 4, 4, true));
        rst.add(year + "-12-25");
        return rst;
    }

    /**
     * 【输入的日期要在1582年10月4日之后】
     * 求第X年第Y月第Z周第N天是几号
     *
     * @param year  年份
     * @param month 月份
     * @param num   第几周
     * @param week  星期
     * @param order 顺序的还是倒数的
     * @return 日期
     */
    private static String weekToDay(int year, int month, int num, int week, boolean order) {
        // 某年的1、2月要看作上一年的13、14月来计算
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }


        int day = 1;
        int step = 1;
        if (!order) {
            day = 31;
            step = -1;
        }

        int n = 0;
        int w;

        int c = year / 100;
        int y = year % 100;


        for (int i = 1; i < 32; i++, day += step) {

            // 基姆拉尔森公式
            w = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;

            if (w + 1 == week) {
                n++;
            }

            if (n == num) {
                break;
            }
        }

        return day > 9 ? "" + day : "0" + day;
    }

    /**
     * 【输入的日期要在1582年10月4日之后】【蔡勒公式】
     * 求第X年第Y月第Z周第N天是几号
     *
     * @param year  年份
     * @param month 月份
     * @param num   第几周
     * @param week  星期
     * @param order 顺序的还是倒数的
     * @return 日期
     */
    private static String weekToDayZeller(int year, int month, int num, int week, boolean order) {
        // 某年的1、2月要看作上一年的13、14月来计算
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }


        int day = 1;
        int step = 1;
        if (!order) {
            day = 31;
            step = -1;
        }

        int n = 0;
        int w;

        int c = year / 100;
        int y = year % 100;


        for (int i = 1; i < 32; i++, day += step) {

            // 蔡勒公式
            w = (y + y / 4 + c / 4 - 2 * c + (26 * (month + 1) / 10) + day - 1);
            // 防止余数为负
            w = (w % 7 + 7) % 7;

            //
            if (w == week) {
                n++;
            }

            if (n == num) {
                break;
            }
        }

        return day > 9 ? "" + day : "0" + day;
    }
}
