import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 06:49
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 每一年中月份的天数
    private final static int[] DAY_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String line = scanner.next();
            String[] part = line.split("\\-");
            System.out.println(getDayOfYear(Integer.parseInt(part[0]),
                    Integer.parseInt(part[1]), Integer.parseInt(part[2])));
        }

        scanner.close();
    }

    /**
     * 求日期是所在年份的第几天
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 日期是所在年份的第几天
     */
    private static int getDayOfYear(int year, int month, int day) {

        // 闰年
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {
            DAY_OF_MONTH[1] = 29;
        } else {
            DAY_OF_MONTH[1] = 28;
        }

        int rst = 0;

        for (int i = 0, j = month - 1; i < j; i++) {
            rst += DAY_OF_MONTH[i];
        }

        rst += day;
        return rst;
    }
}
