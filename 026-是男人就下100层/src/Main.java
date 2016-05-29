import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-13 19:19
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    // 坐标类
    private static class Coordinate {
        // 横坐标左端点
        int a;
        // 横坐标右端点
        int b;
        // 纵坐标
        int y;

        Coordinate(int a, int b, int y) {
            this.a = a;
            this.b = b;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int max = scanner.nextInt();
            Coordinate[] cos = new Coordinate[n + 1];
            for (int i = 0; i < n; i++) {
                cos[i] = new Coordinate(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            }

            // 最后人的人的坐标视为一个长度为0的档板
            cos[n] = new Coordinate(x, x, y);
            System.out.println(earliestTime(cos, max));

        }

        scanner.close();

    }

    /**
     * 下100层的最少时间
     *
     * @param cos 平台数组
     * @param max 人一次可以下落的最大高度
     * @return 最短的时间
     */
    private static int earliestTime(Coordinate[] cos, int max) {

        // 按离地面的高度从小到大排序
        Arrays.sort(cos, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return o1.y - o2.y;
            }
        });

        int len = cos.length;
        //  第i层到最底层的时间，0左侧下去最小时间，右侧下去的最小时间
        int[][] height = new int[len][2];

        // 第一个档板从左右到最底层的时间
        height[0][0] = cos[0].y;
        height[0][1] = cos[0].y;

        // 从下向上处理其它的档板
        for (int i = 1; i < len; i++) {
            // 假设本层到下一层高度最大
            height[i][0] = Integer.MAX_VALUE;
            height[i][1] = Integer.MAX_VALUE;

            // 本层从左右下到下一层是否已经计算过了
            boolean left = false;
            boolean right = false;

            for (int j = i - 1; j >= 0; j--) {
                // 两层的高度差
                int diff = cos[i].y - cos[j].y;

                // 高度差满足条件，并且左右两侧没有全部计算好，就继续处理
                if (diff <= max && (!left || !right)) {
                    // 从第i层的左边下去
                    if (!left && between(cos[i].a, cos[j].a, cos[j].b)) {
                        // 从第i层的【左】边下去，并且通过第j层的【左】边再下去
                        height[i][0] = Math.min((cos[i].a - cos[j].a) + diff + height[j][0], height[i][0]);
                        // 从第i层的【左】边下去，并且通过第j层的【右】边再下去
                        height[i][0] = Math.min((cos[j].b - cos[i].a) + diff + height[j][1], height[i][0]);

                        // 第i层左边下到下一层已经处理
                        left = true;
                    }

                    // 从第i层的右边下去
                    if (!right && between(cos[i].b, cos[j].a, cos[j].b)) {
                        // 从第i层的【右】边下去，并且通过第j层的【左】边再下去
                        height[i][1] = Math.min((cos[i].b - cos[j].a) + diff + height[j][0], height[i][1]);
                        // 从第i层的【右】边下去，并且通过第j层的【右】边再下去
                        height[i][1] = Math.min((cos[j].b - cos[i].b) + diff + height[j][1], height[i][1]);

                        // 第i层右边下到下一层已经处理
                        right = true;
                    }
                } else {
                    // 此处要退出内层for执行了，第i层下不到第j层
                    break;
                }
            } // 结束内层for


            // 从第i层的左边没有遇到下一层的档板，横坐标在范围内，并且从第i层直接到0层是可达的，
            // 即高度差小于max
            if (!left && checkRange(cos[i].a) && cos[i].y <= max) {
                height[i][0] = cos[i].y;
            }

            if (!right && checkRange(cos[i].b) && cos[i].y <= max) {
                height[i][1] = cos[i].y;
            }

        }

        return Math.min(height[len - 1][0], height[len - 1][1]);
    }

    /**
     * 检查横坐标是否在范围内
     *
     * @param a 模坐标
     * @return true：在范围内，false：否
     */
    private static boolean checkRange(int a) {
        return a >= -20000 && a <= 20000;
    }

    /**
     * 检查a是否在[x, y]范围内
     *
     * @param a 数值
     * @param x 起点值
     * @param y 终点值
     * @return true：在范围内，false：不在范围内
     */
    private static boolean between(int a, int x, int y) {
        return a >= x && a <= y;
    }
}
