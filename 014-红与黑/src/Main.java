import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 改进方案
 * <p>
 * Author: 王俊超
 * Time: 2016-05-11 09:34
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int[][] floor = new int[row][col];

            for (int i = 0; i < row; i++) {
                floor[i] = new int[col];
                String line = scanner.next();
                for (int j = 0; j < col; j++) {
                    floor[i][j] = line.charAt(j);
                }
            }

            System.out.println(maxStep(floor));
        }

        scanner.close();
    }

    /**
     * 求可以走的地板的最大步数
     *
     * @param floor 地板
     * @return 步数
     */
    private static int maxStep(int[][] floor) {

        int x = 0;
        int y = 0;
        int row = floor.length;
        int col = floor[0].length;

        // 找起始位置
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (floor[i][j] == '@') {
                    x = i;
                    y = j;
                }
            }
        }

        // 输出地板信息
//        for (int[] line : floor) {
//            for (int e : line) {
//                System.out.print((char) e);
//            }
//            System.out.println();
//        }


        return findPath(floor, x, y);
    }

    /**
     * 求可以走的黑地板的最大步数
     *
     * @param floor 地板
     * @param x     起始坐标
     * @param y     起始坐标
     */
    private static int findPath(int[][] floor, int x, int y) {


        int row = floor.length;
        int col = floor[0].length;

        if (x < 0 || x >= row || y < 0 || y >= col || floor[x][y] == '#') {
            return 0;
        }

        // 记录待访问的位置，两个一组
        Queue<Integer> queue = new ArrayDeque<>(row * col * 2);
        // 可以移动的四个方向，两个一组
        int[] d = {1, 0, 0, 1, -1, 0, 0, -1};

        queue.add(x);
        queue.add(y);

        // 最多可以走的黑地板数目
        int result = 1;

        while (!queue.isEmpty()) {
            x = queue.remove();
            y = queue.remove();

            for (int i = 0; i < d.length; i += 2) {
                int t = x + d[i];
                int v = y + d[i + 1];
                if (t >= 0 && t < row && v >= 0 && v < col && floor[t][v] == '.') {
                    // 标记已经访问过
                    floor[t][v] = '#';
                    // 计数器增加
                    result++;

                    // 访问的位置添加到队列中
                    queue.add(t);
                    queue.add(v);
                }
            }
        }


        return result;
    }
}
