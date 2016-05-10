import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 解法一
 * Author: 王俊超
 * Time: 2016-05-09 18:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            // 迷宫大小
            int col = scanner.nextInt();
            int row = scanner.nextInt();
            // 剩余时间
            int time = scanner.nextInt();

            if (row == 0 && col == 0 && time == 0) {
                break;
            }

            // redraiment的开始位置
            int px = 0;
            int py = 0;
            // 公主的位置
            int sx = 0;
            int sy = 0;

            char[][] maze = new char[row][col];
            for (int i = 0; i < row; i++) {
                String line = scanner.next();
                maze[i] = new char[col];
                for (int j = 0; j < col; j++) {
                    maze[i][j] = line.charAt(j);
                    if (maze[i][j] == 'P') {
                        px = i;
                        py = j;
                    } else if (maze[i][j] == 'S') {
                        sx = i;
                        sy = j;
                    }
                }
            }

            System.out.println(findPath(maze, px, py, sx, sy, time));
        }

        scanner.close();
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
     * @return YES：在time时间内可以找到公主，NO：在time时间内找不到公主
     */
    private static String findPath(char[][] maze, int px, int py, int sx, int sy, int time) {

        // 迷宫大小
        int row = maze.length;
        int col = maze[0].length;


        // 记录广度优先处理的，当前要处理的坐标
        List<Integer> curr = new LinkedList<>();
        // 记录广度优先处理的，下一圈的坐标
        List<Integer> next = new LinkedList<>();

        curr.add(px);
        curr.add(py);
        // 标记已经访问的位置
        maze[px][py] = '*';

        while (!curr.isEmpty()) {
            px = curr.remove(0);
            py = curr.remove(0);


            if (px == sx && py == sy) {
                return "YES";
            }

//            System.out.println("(" + px + ", " + py + ")");

            // 往上走
            if (px - 1 >= 0 && maze[px - 1][py] != '*') {
                next.add(px - 1);
                next.add(py);
                maze[px - 1][py] = '*';
            }

            // 往右走
            if (py + 1 < col && maze[px][py + 1] != '*') {
                next.add(px);
                next.add(py + 1);
                maze[px][py + 1] = '*';
            }

            // 往下走
            if (px + 1 < row && maze[px + 1][py] != '*') {
                next.add(px + 1);
                next.add(py);
                maze[px + 1][py] = '*';
            }

            // 往左走
            if (py - 1 >= 0 && maze[px][py - 1] != '*') {
                next.add(px);
                next.add(py - 1);
                maze[px][py - 1] = '*';
            }

            // 当前层处理完
            if (curr.isEmpty()) {
                // 剩下的时间减少
                time--;

                // 时间用完了还没有找到
                if (time < 0) {
                    return "NO";
                }
                // 处理下一层
                else {
                    List<Integer> queue = curr;
                    curr = next;
                    next = queue;
                }
            }
        }

        return "NO";
    }
}
