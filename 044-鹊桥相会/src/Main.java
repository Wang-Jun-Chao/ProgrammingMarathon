import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-29 09-27
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int width = scanner.nextInt();
            int num = scanner.nextInt();

            if (width <= 0 || num <= 0) {
                break;
            }
            int[][] birds = new int[num][2];
            for (int i = 0; i < num; i++) {
                birds[i][0] = scanner.nextInt();
                birds[i][1] = scanner.nextInt();
            }

            System.out.println(meet(birds, width));
        }

        scanner.close();
    }

    /**
     * 鹊桥相会，牛郎和鸟儿都在同一条直线上，牛郎在0位置。所有的鸟儿都同时出发
     *
     * @param birds 鸟的位置和速度，速度的单位是m/s
     * @param width 银河的宽度，单位Km
     * @return 相会的时间，不能相会就返回Can't Solve
     */
    private static String meet(int[][] birds, int width) {
        // 转换成米
        width *= 1000;

        // 取最大值，同时假设其不可达
        int time = Integer.MAX_VALUE;

        for (int[] bird : birds) {
            // 只在鸟的位置不在牛郎的位置前面，并且方向是到对岸，才进行处理
            if (bird[0] <= 0 && bird[1] > 0) {
                // 真正到对岸的时间就是所坐的鸟的起始位置到对岸的距离除以鸟儿的速度
                // 计算最后坐当前鸟儿到对岸所用的时间
                int t = (width - bird[0]) / bird[1];

                // 记录较小的时间
                if (t < time) {
                    time = t;
                }
            }
        }

        if (time < Integer.MAX_VALUE) {
            return "" + time;
        } else {
            return "Can't Solve";
        }
    }
}
