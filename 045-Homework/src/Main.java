import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-26 16:20
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private static class Paper {
        // 完成试卷所要的时间
        private int time;
        // 完成试卷所得到的价值
        private int value;
        // 单位时间内获得的价值
        private double unit;

        Paper(int time, int value) {
            this.time = time;
            this.value = value;
            this.unit = 1.0 * value / time;
        }

        @Override
        public String toString() {
            return "(" + time + ", " + value + ", " + unit + ")";
        }
    }

    private static class PaperComparator implements Comparator<Paper> {

        @Override
        public int compare(Paper o1, Paper o2) {

            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return 1;
            }

            if (o2 == null) {
                return -1;
            }

            if (o1.unit > o2.unit) {
                return -1;
            } else if (o1.unit < o2.unit) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            int time = scanner.nextInt();
            if (num == 0 && time == 0) {
                break;
            }

            Paper[] papers = new Paper[num];
            for (int i = 0; i < num; i++) {
                int t = scanner.nextInt();
                int v = scanner.nextInt();
                papers[i] = new Paper(t, v);
            }

            System.out.printf("%.2f\n", maxValue(papers, time));
        }

        scanner.close();
    }

    private static double maxValue(Paper[] papers, int time) {

        Arrays.sort(papers, new PaperComparator());
//        System.out.println(Arrays.toString(papers));

        double rst = 0;

        for (int i = 0; i < papers.length && time > 0; i++) {
            if (time - papers[i].time >= 0) {
                rst += papers[i].value;
                time -= papers[i].time;
            } else {
                rst += papers[i].unit * time;
                time = 0;
            }
        }

        return rst;
    }
}
