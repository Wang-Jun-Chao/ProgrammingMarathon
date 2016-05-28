import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-21 08:26
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int[] time = new int[6];
            for (int i = 0; i < time.length; i++) {
                time[i] = scanner.nextInt();
            }
            int[] rst = arrive(time);
            System.out.println(rst[0] + " " + rst[1]);
        }

        scanner.close();
    }

    /**
     * 计算到达时间
     *
     * @param time 时间，长度为6的数组，分别表示：出发小时和分钟、
     *             到目的地所需时间的小时和分钟、发地所在的时区、目的地所在的时区
     * @return 到达时间，第一个数表示小时，第二个数表示分钟
     */
    private static int[] arrive(int[] time) {

        // 目的时区-出发时区，偏移量
        int hour = time[5] - time[4];
        // 如果分钟有进位，加到偏移量上
        hour += (time[1] + time[3]) / 60;
        // 最后的分钟数
        int minute = (time[1] + time[3]) % 60;

        // 出发时间加上花费的时间，现加上偏移量，就是目标时间，可能有负数，不需要校正
        hour = (time[0] + time[2] + hour) % 24;

        return new int[]{hour, minute};
    }
}
