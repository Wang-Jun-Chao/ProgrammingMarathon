import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-11 11:33
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    private static class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char[][] matrix = new char[row][col];

            for (int i = 0; i < row; i++) {
                String s = scanner.next();
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = s.charAt(j);
                }
            }

            System.out.println(maxStep(matrix));
        }

        scanner.close();
    }

    private static int maxStep(char[][] matrix) {
        if (matrix == null || matrix.length <= 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // 记录起始位置
        int sx = -1;
        int sy = -1;

        // 记录最多可以走的步数
        int counts = 0;

        // 移动的四个方向
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        // 找起始位置
        for (int i = 0; sx == -1 && i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '@') {
                    sx = j;
                    sy = i;
                    break;
                }
            }
        }

        if (sx == -1) {
            return 0;
        }

        // 记录已经走过的点
        Queue<P> queue = new LinkedList<>();
        queue.add(new P(sx, sy));
        // 记录当前的点
        P curr;


        // 队列不为空
        while (!queue.isEmpty()) {
            // 删除队头元素
            curr = queue.poll();
            counts++;
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dir[i][0];
                int ny = curr.y + dir[i][1];
                P next = new P(nx, ny);
                if (nx >= 0 && nx < col && ny >= 0 && ny < row && matrix[ny][nx] == '.') {
                    queue.add(next);
                    matrix[ny][nx] = '-';
                }
            }
        }
        return counts;
    }
}
