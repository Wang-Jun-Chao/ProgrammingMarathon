import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-22 15:48
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    // Haab日历19个月的名称
    private final static String[] HAAB_MONTH = {
            "pop", "no", "zip", "zotz", "tzec", "xul", "yoxkin", "mol", "chen", "yax",
            "zac", "ceh", "mac", "kankin", "muan", "pax", "koyab", "cumhu", "uayet"};

    // Haab日历一年的天数
    private final static int HAAB_YEAR_DAYS = 365;

    // Tzolkin日历使用的循环单词
    private final static String[] TZOLKIN_WORDS = {
            "imix", "ik", "akbal", "kan", "chicchan", "cimi", "manik", "lamat", "muluk", "ok",
            "chuen", "eb", "ben", "ix", "mem", "cib", "caban", "eznab", "canac", "ahau"};

    // Tzolkin日历一年的天数
    private final static int TZOLKIN_YEAR_DAYS = 260;

    // 名称与索引的映射
    private final static Map<String, Integer> HAAB = new HashMap<>();
    private final static Map<String, Integer> TZOLKIN = new HashMap<>();

    // 初始化映射
    static {
        nameToIndex(HAAB, HAAB_MONTH);
        nameToIndex(TZOLKIN, TZOLKIN_WORDS);
    }

    /**
     * 将名称转换成对应的索引
     *
     * @param map   数据存放的集合
     * @param names 名称字符串
     */
    private static void nameToIndex(Map<String, Integer> map, String[] names) {
        for (String s : names) {
            map.put(s, map.size());
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (scanner.hasNext()) {
            int day = scanner.nextInt();
            String month = scanner.next();
            int year = scanner.nextInt();
            System.out.println(habbToTzolkin(day, month, year));
        }

        scanner.close();
    }

    private static String habbToTzolkin(int day, String month, int year) {
        // 当前日期是从开始计时日期以来的第多少天
        int total = year * HAAB_YEAR_DAYS + getHabbDayOfYear(day, month);

        // 对应Tzolkin的年份，减一是因为第260天是一年的最后一天
        year = (total - 1) / TZOLKIN_YEAR_DAYS;

        // 年中的天数
        day = total % TZOLKIN_YEAR_DAYS;
        // 日期数字
        int dayNum = day % 13;

        if (dayNum == 0) {
            dayNum = 13;
        }

        // 日期名称
        String dayName = TZOLKIN_WORDS[(day % 20 - 1 + 20) % 20];
        return dayNum + " " + dayName + " " + year;
    }

    /**
     * 根据日期，月份获取其在Haab日历中是第多少天
     *
     * @param day   日期
     * @param month 月份
     * @return 在Haab日历中是第多少天
     */
    private static int getHabbDayOfYear(int day, String month) {

        if (!HAAB.containsKey(month)) {
            return 1;
        }

        // 取月份的序号
        int n = HAAB.get(month);
        return n * 20 + day + 1;
    }
}
