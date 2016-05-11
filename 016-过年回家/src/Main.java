import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-11 19:56
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));

        while (scanner.hasNext()) {

            int line = scanner.nextInt();
            // 收费站的数目加上起点
            int num = scanner.nextInt() + 1;
            int[][] graph = new int[num][num];

            // 初始化图
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (i != j) {
                        graph[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            // 读取输入构造有向图
            for (int i = 0; i < line; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int v = scanner.nextInt();
                graph[x][y] = v;
            }

            System.out.println(dijkstra(graph));

        }

        scanner.close();
    }

    /**
     * 求起点为0，终点为graph.length-1的最短路径，权重不能为负数
     *
     * @param graph 有向图
     * @return 最短路径，没有找到返回Integer.MAX_VALUE;
     */
    private static int dijkstra(int[][] graph) {
        // 标记顶点是否已经访问过
        boolean[] S = new boolean[graph.length];
        // 记录起点到各点的最短距离
        int[] DIST = new int[graph.length];
        // 记录前驱顶点，通过找前驱可以找到从(v, w)的最短路径的走法
        int[] PREV = new int[graph.length];

        // 处理第一个点
        for (int i = 0; i < graph.length; i++) {
            DIST[i] = graph[0][i];
            // 如果是最大值，说明(0, i)不存在。所以PREV[i]不存在
            if (DIST[i] == Integer.MAX_VALUE) {
                PREV[i] = -1;
            } else {
                PREV[i] = 0;
            }
        }

        // 标记0号顶点已经处理过
        S[0] = true;

        // 处理其余的点
        for (int i = 1; i < S.length; i++) {
            int min = Integer.MAX_VALUE;
            int u = 0;

            // 找未访问过的顶点j，并且DIST[j]的值最小
            for (int j = 0; j < S.length; j++) {
                if (!S[j] && DIST[j] < min) {
                    u = j;
                    min = DIST[j];
                }
            }

            // 标记u已经被访问过了
            S[u] = true;

            for (int j = 0; j < S.length; j++) {
                // j没有被访问过，并且(u, j)可达
                if (!S[j] && graph[u][j] < Integer.MAX_VALUE) {
                    int v = DIST[u] + graph[u][j];
                    // 从0->...->u->j比0->...->j（其它路径）短
                    if (v < DIST[j]) {
                        DIST[j] = v;
                        // j是通过u访问到的
                        PREV[j] = u;
                    }
                }
            }
        }


        return DIST[DIST.length - 1];
    }
}
