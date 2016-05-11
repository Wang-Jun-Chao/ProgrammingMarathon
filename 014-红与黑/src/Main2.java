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

//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char[][] matrix = new char[row][col];

            for (int i = 0; i < row; i++) {
                String s = scanner.next();
                for (int j = 0; j < s.length(); j++) {
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
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int row = matrix.length;
        int col = matrix[0].length;

        int sx = -1;
        int sy = -1;
        int counts = 0;
        boolean flag = false;

        for (int i = 0; !flag && i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '@') {
                    sx = j;
                    sy = i;
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            return 0;
        }
        Queue<P> queue = new LinkedList<>();
        queue.add(new P(sx, sy));

        P curr;
        while (!queue.isEmpty()) {
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
