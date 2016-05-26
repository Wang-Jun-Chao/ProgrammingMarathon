import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-18 12:19
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 右，下，左，上四个方向
    private final static int[] D = {0, 1, 1, 0, 0, -1, -1, 0};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] map = new char[n][n];
            for (int i = 0; i < n; i++) {
                map[i] = scanner.next().toCharArray();
            }

            System.out.println(square(map));
        }

        scanner.close();
    }

    private static int square(char[][] map) {

//        for (char[] chs : map) {
//            System.out.println(Arrays.toString(chs));
//        }

        int row = map.length;
        int col = map[0].length;

        // 将外层的0标记成1
        for (int i = 0; i < row; i++) {
            // 第一列
            if (map[i][0] == '0') {
                bfs(i, 0, map);
            }
            // 最后一列
            if (map[i][col - 1] == '0') {
                bfs(i, col - 1, map);
            }
        }

        for (int i = 0; i < col; i++) {
            // 第一行
            if (map[0][i] == '0') {
                bfs(0, i, map);
            }
            // 最后一行
            if (map[row - 1][i] == '0') {
                bfs(row - 1, i, map);
            }
        }

        // 统计一的个数
        int count = 0;
        for (char[] line : map) {
            for (int j = 0; j < col; j++) {
                count += line[j] - '0';
            }
        }


//        System.out.println();
//        for (char[] chs : map) {
//            System.out.println(Arrays.toString(chs));
//        }

        return row * col - count;
    }

    /**
     * 从(x, y)处开始广度优先遍历，(x, y)是1之外的0
     *
     * @param x   行
     * @param y   列
     * @param map 地图
     */
    private static void bfs(int x, int y, char[][] map) {
        List<Integer> list = new LinkedList<>();

        list.add(x);
        list.add(y);

        int len = map.length;

        map[x][y] = '1';

        while (!list.isEmpty()) {
            x = list.remove(0);
            y = list.remove(0);

            for (int i = 0; i < D.length; i += 2) {
                int v = x + D[i];
                int w = y + D[i + 1];

                if (v > -1 && v < len && w > -1 && w < len && map[v][w] == '0') {
                    map[v][w] = '1';
                    list.add(v);
                    list.add(w);
                }
            }
        }
    }

}
