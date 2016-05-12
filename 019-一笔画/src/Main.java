import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-12 09:04
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] adj = new int[n][n];
            for (int i = 0; i < n; i++) {
                adj[i] = new int[n];
            }

            for (int i = 0; i < m; i++) {
                //下标从0开始
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                // 标记可达，有n个(x, y)的边就有adj[x][y]=n
                adj[x][y]++;
            }

            if (draw(adj)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    private static boolean draw(int[][] adj) {
        int len = adj.length;
        // 统计出度和入度
        int[] in = new int[len];
        int[] out = new int[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                // 第i行和第i列
                in[i] += adj[i][j];
                out[i] += adj[j][i];
            }
        }

        // 统计有多少个出度和入度不相等的
        int ne = len;
        // 记录最后一个入度大于出度，它们的差
        int x = 0;
        // 记录最后一个出度大于入度，它们的差
        int y = 0;

        for (int i = 0; i < len; i++) {
            // 出度=入度
            if (in[i] == out[i]) {
                ne--;
            } else if (in[i] > out[i]) {
                x = (in[i] - out[i]);
            } else {
                y = (out[i] - in[i]);
            }
        }

        // 输出邻接矩阵、出度、入度和不相等的出度入度数
//        for (int[] row : adj) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println();
//        System.out.println(Arrays.toString(in));
//        System.out.println(Arrays.toString(out));
//        System.out.println(ne);

        // 有向图D 存在欧拉通路的充要条件是：
        // D 为有向图，D 的基图连通，并且所有顶点的出度与入度都相等；或者除两个顶点外，其余
        // 顶点的出度与入度都相等，而这两个顶点中一个顶点的出度与入度之差为1，另一个顶点的出度
        // 与入度之差为-1。
        return ne == 0 || (ne == 2 && x == 1 && y == 1);
    }
}
