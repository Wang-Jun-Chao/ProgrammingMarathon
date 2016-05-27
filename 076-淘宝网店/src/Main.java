import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-21 08:30
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 每一月每一天赚的钱，第一个下标不使用
    private final static int[] DAY_INCOME = {0, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2};
    // 平年每一个月的天数，第一个下标不使用
    private final static int[] DAY = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 平年每月的收益，第一个下标不使用
    private final static int[] MONTH_INCOME = {0, 62, 28, 31, 60, 31, 60, 31, 62, 60, 62, 30, 62};

    // 平年的收益
    private final static int YEAR_INCOME = 31 * 2 + 28 + 31 + 30 * 2 + 31
            + 30 * 2 + 31 + 31 * 2 + 30 * 2 + 31 * 2 + 30 + 31 * 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int[] time = new int[6];

            for (int i = 0; i < time.length; i++) {
                time[i] = scanner.nextInt();
            }

            System.out.println(income(time));
        }

        scanner.close();
    }

    /**
     * 计算收益
     *
     * @param time 长度为6的数组，每一个组表示年月日
     * @return 收益
     */
    private static int income(int[] time) {

        int rst = 0;
        // 如果是同一年，同一月之内
        if (time[0] == time[3] && time[1] == time[4]) {
            rst = (time[5] - time[2] + 1) * DAY_INCOME[time[1]];
        }
        // 同一年，月份不同
        else if (time[0] == time[3]) {
            // [time[2], 月末最后一天]
            rst += (DAY[time[1]] - time[2] + 1) * DAY_INCOME[time[1]];
//            System.out.println(rst + "===");
            // [1, time[5]]的天数
            rst += (time[5]) * DAY_INCOME[time[4]];
//            System.out.println(rst + "===");
            // 求[time[1] + 1, time[4] - 1]区间的整月收益
            rst += wholeMonthIncome(time[0], time[1] + 1, time[4] - 1);
//            System.out.println(rst + "===");
            // 1、如果是闰年，起始月分是二月
            // 要加上2-29日的收益
            if (isLeapYear(time[1]) && time[1] == 2) {
                rst++;
            }
        }
        // 不同的年份
        else {

            // 求[time[0] + 1, time[3] - 1]区间完整年份的收益
            rst += wholeYearIncome(time[0] + 1, time[3] - 1);

            // 转换同一年份的结构，起始日期是开始计算的日期，结束日期是起始年份的最后一天
            int[] t = {time[0], time[1], time[2], time[0], 12, 31};
            rst += income(t);

            // 转换同一年份的结构，起始日期是结束年份的第一天，结束日期是输入的结束日期
            time[0] = time[3];
            time[1] = 1;
            time[2] = 1;
            rst += income(time);
        }

        return rst;
    }

    /**
     * 计算整年的收益
     *
     * @param from 开始年份，包含
     * @param to   结束年份，包含
     * @return 收益
     */
    private static int wholeYearIncome(int from, int to) {
        int rst = 0;
        if (from <= to) {
            for (int i = from; i <= to; i++) {
                // 一整年的收益
                rst += YEAR_INCOME;
                // 如果是闰年还要多加2-29日的收益
                if (isLeapYear(i)) {
                    rst++;
                }
            }
        }

        return rst;
    }

    /**
     * 求year年，[from, to]月份的收益
     *
     * @param year 年份
     * @param from 起始月份，包含
     * @param to   结束月份，包含
     * @return 收益
     */
    private static int wholeMonthIncome(int year, int from, int to) {

        int rst = 0;
        if (from <= to) {
            for (int i = from; i <= to; i++) {
                rst += MONTH_INCOME[i];
            }

            // 如果是闰年，并且包含2月，就要加2-29日的收益
            if (from < 3 && isLeapYear(year)) {
                rst++;
            }
        }

        return rst;
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
