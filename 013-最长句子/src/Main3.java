import java.util.*;

/**
 * 解法三：
 * 因为解法一（Main）会生产超时，现在对他进行改进
 * Author: 王俊超
 * Time: 2016-05-10 10:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main3 {
    /**
     * 有向图
     */
    private static class G {
        // 顶点集合，通过顶点的名称来找顶点。
        private final Map<String, V> VERTEX = new HashMap<>();
    }


    /**
     * 图的顶点对象，使用图的邻接表表示
     */
    private static class V {
        // 顶点的名称
        private String n;
        // 以当前顶点为终点的最长有向线段的顶点数，只有一个顶点时为1
        private int v;
        // 邻接点
        private final Set<V> ADJ = new HashSet<>();

        V(String n, int v) {
            this.n = n;
            this.v = v;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main3.class.getClassLoader().getResourceAsStream("data2.txt"));
        // 创建一个图对象，可以重复使用
        G g = new G();
        while (scanner.hasNext()) {
            // 清空内容
            g.VERTEX.clear();

            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                String a = scanner.next();
                String b = scanner.next();
                addEdge(g, a, b);
            }

            System.out.println(getLongestPathLength(g));
        }

        scanner.close();
    }

    /**
     * 求图g最长路径的长度
     *
     * @param g 图
     * @return 最长路径的长度
     */
    private static int getLongestPathLength(G g) {

        if (g == null || g.VERTEX.isEmpty()) {
            return 0;
        }

        int max = 0;
        Collection<V> vs = g.VERTEX.values();
        for (V v : vs) {
            if (max < v.v) {
                max = v.v;
            }
        }

        return max;
    }

    /**
     * 向图g中添加边(a, b);
     *
     * @param g 图
     * @param a 边的起始点
     * @param b 边的终点
     */
    private static void addEdge(G g, String a, String b) {


//        // 判断两个顶点是否都在图中
//        boolean ca = g.VERTEX.containsKey(a);
//        boolean cb = g.VERTEX.containsKey(b);

        V av = g.VERTEX.get(a);
        V bv = g.VERTEX.get(b);

        if (av == null) {
            av = new V(a, 1);
            // 将顶点a放到顶点集合中
            g.VERTEX.put(a, av);
        }

        if (bv == null) {
            bv = new V(b, 1);
            // 将顶点b放到顶点集合中
            g.VERTEX.put(b, bv);
        }

        // 将b设置为a的邻接点
        g.VERTEX.get(a).ADJ.add(g.VERTEX.get(b));
        update(g.VERTEX.get(a), g.VERTEX.get(b), g);

    }

    /**
     * 更新结束顶点的长度记数
     *
     * @param a 顶点
     * @param b 顶点
     * @param g 图
     */
    private static void update(V a, V b, G g) {
        // 原先以a为终点的最长线段包含的顶点数，再加上1，表示从包含(a, b)，
        // 以b为终点的最长线段包含的顶点数
        int lenA = a.v + 1;
        // 以b为终点的最长线段包含的顶点数
        int lenB = b.v;

        if (lenA > lenB) {
            b.v = lenA;

            Set<V> vs = b.ADJ;
            for (V v : vs) {
                update(b, v, g);
            }
        }
    }
}
