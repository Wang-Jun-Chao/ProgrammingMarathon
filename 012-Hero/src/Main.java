import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-09 18:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains("0 0 0")) {
                break;
            }

            String[] parts = line.split("(\\s)+");

            // 迷宫大小
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            // 剩余时间
            int time = Integer.parseInt(parts[2]);
            // redraiment的开始位置
            int px = 0;
            int py = 0;
            // 公主的位置
            int sx = 0;
            int sy = 0;

            char[][] maze = new char[row][col];
            for (int i = 0; i < row; i++) {
                line = scanner.nextLine();
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
     * 迷宫找最短的路径
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

        // 访问标记数组
        boolean[][] visit = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            visit[i] = new boolean[col];
            for (int j = 0; j < col; j++) {
                if (maze[i][j] == '*') {
                    visit[i][j] = true;
                }
            }
        }


        // 记录广度优先处理的，当前要处理的坐标
        Queue<Integer> curr = new LinkedList<>();
        // 记录广度优先处理的，下一圈的坐标
        Queue<Integer> next = new LinkedList<>();

        curr.add(px);
        curr.add(py);

        while (!curr.isEmpty()) {
            px = curr.remove();
            py = curr.remove();

            if (px == sx && py == sy) {
                return "YES";
            }
        }

        return "NO";
    }
}
