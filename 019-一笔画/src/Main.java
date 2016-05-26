import java.util.ArrayList;
import java.util.List;
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
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            // 记录边
            int[] edge = new int[m * 2];

            for (int i = 0; i < edge.length; i++) {
                edge[i] = scanner.nextInt();
            }

            if (draw(n, edge)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    /**
     * 图是否可以笔画完（判断无向图是否存在欧拉通路）
     *
     * @param n    顶点点个数，顶点的编号从1到n
     * @param edge 边的连接数组，两个一起表示一条边
     * @return true：可以一笔画完，false：不可以一笔画完
     */
    private static boolean draw(int n, int[] edge) {

        int[] vertex = new int[n + 1];

        // 统计每个顶点的度数
        for (int i : edge) {
            vertex[i]++;
        }


        ///////////////////////////////////////////////////////////////////////////////////////////
        // 无向图G存在欧拉通路的充要条件是：G为连通图，并且G仅有两个奇度结点（度数为奇数的顶点）或者无奇度结点。
        ///////////////////////////////////////////////////////////////////////////////////////////

        // 统计奇度顶点个数
        int count = 0;
        for (int i = 1; i < vertex.length; i++) {
            if (vertex[i] % 2 != 0) {
                count++;
            }
        }

        // 奇度顶点不为0且不为2说明不存在欧拉通路
        if (count != 0 && count != 2) {
            return false;
        }


        // 构造边的邻接矩阵
        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < edge.length; i += 2) {
            int v = edge[i];
            int w = edge[i + 1];
            graph[v][w]++;
            graph[w][v]++;
        }

        // 清空顶号入度标记，将它作为访问标记使用，0表示没有访问过，1表示访问过
        for (int i = 0; i < vertex.length; i++) {
            vertex[0] = 0;
        }

        List<Integer> list = new ArrayList<>(n);

        // 有向图连通，那么从任意一个顶点都可以访问到其它的顶点
        // 从第一个顶点开始访问，进行广度优先遍历
        vertex[1] = 1;
        list.add(1);
        while (!list.isEmpty()) {
            int v = list.remove(0);
            for (int i = 1; i <= n; i++) {
                // 边(v, w)
                int w = graph[v][i];
                // 如果顶点w没有被访问过，就标记已经访问过，添加到访问队列中
                if (vertex[w] == 0) {
                    vertex[w] = 1;
                    list.add(w);
                }
            }
        }


        for (int i = 1; i < vertex.length; i++) {
            // 还有顶点没有访问到，说明图不连通
            if (vertex[i] == 0) {
                return false;
            }
        }

        return true;
    }
}
