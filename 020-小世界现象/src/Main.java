import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-12 11:52
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        int group = scanner.nextInt();
        for (int i = 0; i < group; i++) {
            // 用户个数
            int n = scanner.nextInt();
            int[][] edge = new int[n][n];
            for (int j = 0; j < n; j++) {
                edge[j] = new int[n];
                for (int k = 0; k < n; k++) {
                    edge[j][k] = scanner.nextInt();
                }
            }

            // 用户组
            int m = scanner.nextInt();
            List<Integer> pairs = new ArrayList<>(m * 2);
            m *= 2;
            for (int j = 0; j < m; j++) {
                // 因为数组下标从0开始，而人的编号从1开始，将人的编号全部减1
                pairs.add(scanner.nextInt() - 1);
            }

            // 对输入的关系矩阵进行处理(v, v)设置为1，(v, w)不直接可达的设置为Integer.MAX_VALUE
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) {
                        edge[j][k] = 0;
                    } else if (edge[j][k] == 0) {
                        edge[j][k] = Integer.MAX_VALUE;
                    }
                }
            }


            List<Integer> result = floyd(edge, pairs);
//            List<Integer> result = dijkstra(edge, pairs);

            // 输入结果，因求出的是(v,w)之前的边的数目，它们之前的顶点数就是最少的联系人数目
            // 最少的联系人数目=(v, w)最少的边数-1
            for (Integer r : result) {
                if (r < Integer.MAX_VALUE) {
                    System.out.println(r - 1);
                } else {
                    System.out.println("Sorry");
                }
            }
        }

        scanner.close();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 解法一：Floyd方法求任意两点间的距离
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 使用Floyd算法求图任意两点之间的最短距离
     *
     * @param edge  图的邻接矩阵
     * @param pairs 所要求的(v, w)点的集合
     * @return (v, w)的最短路路径
     */
    private static List<Integer> floyd(int[][] edge, List<Integer> pairs) {

        int MAX = Integer.MAX_VALUE;
        // 顶点数
        int N = edge.length;
        // 记录任意两点的最短路径
        int[][] A = new int[N][N];
        // 记录最短路径的走法，在本题中可以不使用
        int[][] path = new int[N][N];

        // 初始化A和path
        for (int i = 0; i < N; i++) {
            A[i] = new int[N];
            path[i] = new int[N];

            for (int j = 0; j < N; j++) {
                A[i][j] = edge[i][j];

                // (i, j)有路径
                if (i != j && A[i][j] < MAX) {
                    path[i][j] = i;
                }
                // 从i到j没有路径
                else {
                    path[i][j] = -1;
                }
            }
        }

        // /从A(-1)递推到A(0), A(1), ..., A(n-1),或者理解成依次将v0,v1,...,v(n-1)作为中间顶点
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    if (A[i][k] < MAX && A[k][j] < MAX && A[i][k] + A[k][j] < A[i][j]) {
                        A[i][j] = A[i][k] + A[k][j];
                        // path[i][j]是从顶点vi到顶点vj的最短路径上顶点j的前一顶点的序号
                        // 现在path[i][j]中j的前一个顶点就是path[k][j]中j的前一个顶点
                        path[i][j] = path[k][j];
                    }
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        while (!pairs.isEmpty()) {
            int x = pairs.remove(0);
            int y = pairs.remove(0);
            result.add(A[x][y]);
        }

        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 解法二：Dijkstra方法求任意两点间的距离
    /////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * 使用Dijkstra算法求图任意两点之间的最短距离
     *
     * @param edge  图的邻接矩阵
     * @param pairs 所要求的(v, w)点的集合
     * @return (v, w)的最短路路径
     */
    private static List<Integer> dijkstra(int[][] edge, List<Integer> pairs) {

        int N = edge.length;
        int MAX = Integer.MAX_VALUE;
        // 标记顶点是否已经访问过
        boolean[] S = new boolean[N];
        // 记录起点到各点的最短距离
        int[][] DIST = new int[N][N];
        // 记录前驱顶点，通过找前驱可以找到从(v, w)的最短路径的走法，在本题中可以不使用
        int[][] PREV = new int[N][N];

        List<Integer> result = new ArrayList<>();

        // 处理每一个(v, w)
        for (int v = 0; v < N; v++) {
            DIST[v] = new int[N];
            PREV[v] = new int[N];

            // 处理第一个点
            for (int i = 0; i < N; i++) {
                S[i] = false;
                DIST[v][i] = edge[v][i];
                // 如果是最大值，说明(0, i)不存在。所以PREV[i]不存在
                if (DIST[v][i] == MAX) {
                    PREV[v][i] = -1;
                } else {
                    PREV[v][i] = 0;
                }
            }

            // 标记v号顶点已经处理过
            S[v] = true;

            // 处理其余的点
            for (int i = 1; i < N; i++) {
                int min = MAX;
                int u = 0;

                // 找未访问过的顶点j，并且DIST[j]的值最小
                for (int j = 0; j < N; j++) {
                    if (!S[j] && DIST[v][j] < min) {
                        u = j;
                        min = DIST[v][j];
                    }
                }

                // 标记u已经被访问过了
                S[u] = true;

                for (int j = 0; j < N; j++) {
                    // j没有被访问过，并且(u, j)可达
                    if (!S[j] && edge[u][j] < MAX) {
                        int weight = DIST[v][u] + edge[u][j];
                        // 从0->...->u->j比0->...->j（其它路径）短
                        if (DIST[v][u] < MAX && edge[u][j] < MAX && weight < DIST[v][j]) {
                            DIST[v][j] = weight;
                            // j是通过u访问到的
                            PREV[v][j] = u;
                        }
                    }
                }
            }

        }

        for (int i = 0; i < pairs.size(); i += 2) {
            int v = pairs.get(i);
            int w = pairs.get(i + 1);
            result.add(DIST[v][w]);
        }

        return result;
    }


    private static void print(int[][] arr) {
        for (int[] line : arr) {
            print(line);
        }
    }

    private static void print(int[] arr) {
        for (int val : arr) {
            if (val != Integer.MAX_VALUE) {
                System.out.print(val + " ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
    }
}
