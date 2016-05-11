import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-11 09:34
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        //        Scanner scanner = new Scanner(System.in);
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
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (floor[i][j] == '@') {
                    x = i;
                    y = j;
                }
            }
        }

        for (int[] line : floor) {
            for (int e : line) {
                System.out.print((char) e);
            }

            System.out.println();
        }


        int[] result = {0};
        int[] current = {0};
        findPath(floor, x, y, current, result);

        return result[0];
    }

    /**
     * 这个方法非常耗时
     * 求可以走的地板的最大步数
     *
     * @param floor   地板
     * @param x       起始坐标
     * @param y       起始坐标
     * @param current 当前走的步数
     * @param result  记录到的最大的步数
     */
    private static void findPath(int[][] floor, int x, int y, int[] current, int[] result) {

        System.out.println(x + ", " + y);

        int row = floor.length;
        int col = floor[0].length;

        if (x < 0 || x >= row || y < 0 || y >= col || floor[x][y] == '#') {
            return;
        }

        current[0]++;
        if (result[0] < current[0]) {
            current[0] = result[0];
        }

        // 标记已经被访问过
        floor[x][y] = '#';

        // 向上走
        if (x - 1 >= 0 && floor[x - 1][y] == '.') {
            findPath(floor, x - 1, y, current, result);
        }

        // 向右走
        if (y + 1 < col && floor[x][y + 1] == '.') {
            findPath(floor, x, y + 1, current, result);
        }

        // 向下走
        if (x + 1 < row && floor[x + 1][y] == '.') {
            findPath(floor, x + 1, y, current, result);
        }

        // 向左走
        if (y - 1 >= 0 && floor[x][y - 1] == '.') {
            findPath(floor, x, y - 1, current, result);
        }

        // 还原
        floor[x][y] = '.';
    }
}
