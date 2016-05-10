import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 解法二
 * Author: 王俊超
 * Time: 2016-05-10 08:10
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            // 迷宫大小
            int col = scanner.nextInt();
            int row = scanner.nextInt();
            // 剩余时间
            int time = scanner.nextInt();
            if (col == 0 && row == 0 && time == 0) {
                break;
            }

            // redraiment的开始位置
            int px = 0;
            int py = 0;
            // 公主的位置
            int sx = 0;
            int sy = 0;

            // a[i][j]数值为-1表示障碍
            // a[i][j]=0表示还未走过
            // a[i][j]>0表示从(px, py)到(i, j)要走a[i][j]步
            int[][] maze = new int[row][col];
            for (int i = 0; i < row; i++) {
                String s = scanner.next();
                for (int j = 0; j < col; j++) {
                    char c = s.charAt(j);
                    if (c == '*') {
                        maze[i][j] = -1;
                    } else {
                        maze[i][j] = 0;
                        if (c == 'S') {
                            sx = i;
                            sy = j;
                        } else if (c == 'P') {
                            px = i;
                            py = j;
                        }
                    }
                }
            }

            if (deal(maze, px, py, sx, sy, time)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * 迷宫找最短的路径，使用广度优先遍历
     *
     * @param maze 迷宫
     * @param px   redraiment的开始位置。横坐标
     * @param py   redraiment的开始位置。纵坐标
     * @param sx   公主的位置。横坐标
     * @param sy   公主的位置。纵坐标
     * @param time 剩余时间
     * @return true：规定时间可以找到，false：规定时间找不到
     */
    private static boolean deal(int[][] maze, int px, int py, int sx, int sy, int time) {

        // 最开始redraiment和公主在同一个位置
        if (px == sx && py == sy) {
            return true;
        }

        int row = maze.length;
        int col = maze[0].length;

        List<Integer> list = new ArrayList<>();
        list.add(px);
        list.add(py);

        while (!list.isEmpty()) {

            px = list.remove(0);
            py = list.remove(0);

            // 已经处理到了公主的位置，公主的位置在之前必然已经被计算过了
            if (px == sx && py == sy) {
                // 从最开始的(px, py)到(sx, sy)的移动频数已经计算过了
                if (maze[px][py] > 0 && maze[px][py] <= time) {
                    return true;
                } else if (maze[px][py] > time) {
                    return false;
                }

                // 公主的位置还没有处理过【这种情况在这里是不存在的】

            }

            // 往上走
            if (px - 1 >= 0 && maze[px - 1][py] != -1) {
                if (maze[px - 1][py] > maze[px][py] + 1 || maze[px - 1][py] == 0) {
                    // 步数加一
                    maze[px - 1][py] = maze[px][py] + 1;
                    list.add((px - 1));
                    list.add(py);
                }
            }

            // 往下走
            if (px + 1 < row && maze[px + 1][py] != -1) {
                if (maze[px + 1][py] > maze[px][py] + 1 || maze[px + 1][py] == 0) {
                    maze[px + 1][py] = maze[px][py] + 1;
                    list.add((px + 1));
                    list.add(py);
                }
            }

            // 往左走
            if (py - 1 >= 0 && maze[px][py - 1] != -1) {
                if (maze[px][py - 1] > maze[px][py] + 1 || maze[px][py - 1] == 0) {
                    maze[px][py - 1] = maze[px][py] + 1;
                    list.add(px);
                    list.add(py - 1);
                }
            }

            // 往右走
            if (py + 1 < col && maze[px][py + 1] != -1) {
                if (maze[px][py + 1] > maze[px][py] + 1 || maze[px][py + 1] == 0) {
                    maze[px][py + 1] = maze[px][py] + 1;
                    list.add(px);
                    list.add(py + 1);
                }
            }
        }

        // 如果在while里没有找到合适的路径，在while外就肯定找不到合适的路径
        return false;
    }
}

