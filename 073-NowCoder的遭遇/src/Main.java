import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-20 20:11
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private final static boolean[] OFF = new boolean[62];

    static {
        int i = 0;
        int gap = 0;
        while (i < OFF.length) {
            OFF[i] = true;
            gap++;
            for (int j = 0; j < gap; j++) {
                i++;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String s = scanner.next();
            if ("0/0".equals(s)) {
                break;
            }

            String[] ss = s.split("/");
            System.out.println(countOff(Integer.parseInt(ss[0]), Integer.parseInt(ss[1])));
        }

        scanner.close();
    }

    /**
     * 计算停电的天数，假定输入的格式合法
     *
     * @param month 月份
     * @param day   日期
     * @return 停电的天数
     */
    private static int countOff(int month, int day) {
        int idx = 0;
        int count = 0;

        if (month > 9) {
            idx = OFF.length;
        } else if (month == 8) {
            idx = 31 + day - 1;
        } else if (month == 7) {
            idx = day - 1;
        } else {
            idx = 0;
        }

        while (idx < OFF.length) {
            if (OFF[idx]) {
                count++;
            }
            idx++;
        }
        return count;
    }
}
