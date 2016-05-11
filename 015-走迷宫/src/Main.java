import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-11 18:59
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private final static int N = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));

        while (scanner.hasNextLine()) {
            char[][] maze = new char[N][N];
            for (int i = 0; i < N; i++) {
                maze[i] = scanner.nextLine().toCharArray();
            }

            System.out.println(minStep(maze));
        }

        scanner.close();
    }

    /**
     * 求使用最少的步骤走出迷宫，迷宫大小固定为10*10，起点为(0, 1)，终点为(9, 8)
     *
     * @param maze 迷宫
     * @return 走出迷宫最少的步数，走不出去返回-1
     */
    private static int minStep(char[][] maze) {

        // 记录当前处理层的位置
        Queue<Integer> curr = new ArrayDeque<>(N * N);
        // 记录下一层处理的位置
        Queue<Integer> next = new ArrayDeque<>(N * N);

        // 可以移动的四个方向，两个一组
        int[] d = {1, 0, 0, 1, -1, 0, 0, -1};

        // 添加起点;
        curr.add(0);
        curr.add(1);
        int x;
        int y;
        // 记录最少的步数
        int step = 0;

        while (!curr.isEmpty()) {
            x = curr.remove();
            y = curr.remove();

            // 找到终点位置
            if (x == 9 && y == 8) {
                return step;
            }

            // 处理(x, y)位置的四个方向
            for (int i = 0; i < d.length; i += 2) {
                int t = x + d[i];
                int v = y + d[i + 1];
                if (t >= 0 && t < N && v >= 0 && v < N && maze[t][v] == '.') {
                    // 标记已经访问过
                    maze[t][v] = '#';
                    // 访问的位置添加到队列中
                    next.add(t);
                    next.add(v);
                }
            }

            // 当前层已经处理完
            if (curr.isEmpty()) {
                // 步数加一
                step++;
                // 处理下一层
                Queue<Integer> temp = curr;
                curr = next;
                next = temp;
            }

        }

        // 执行到此说明找不到出路
        return -1;
    }
}
