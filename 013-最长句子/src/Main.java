import java.util.*;

/**
 * 解法一会生产超时
 * Author: 王俊超
 * Time: 2016-05-10 10:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    /**
     * 有向图
     */
    private static class G {
        // 顶点集合，通过顶点的名称来找顶点。
        private final Map<String, V> VERTEX = new HashMap<>();
        // 有向无环图的起始顶点，通过顶点的名称来找起始顶点。
        private final Map<String, V> STARTING = new HashMap<>();
    }


    /**
     * 图的顶点对象，使用图的邻接表表示
     */
    private static class V {
        // 顶点的名称
        private String n;

        private V p;
        // 邻接点
        private final Set<V> ADJ = new HashSet<>();

        V(String n) {
            this.n = n;
        }
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            // 创建一个图对象
            G g = new G();
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

        int[] r = {0};
        int[] t = {0};
        Collection<V> vs = g.STARTING.values();
        for (V v : vs) {
            t[0] = 0;
            findLongestPathLength(v, t, r);
        }

        return r[0];
    }

    /**
     * 找以v顶点开始的最长路径的长度
     *
     * @param v      顶点
     * @param curr   从最开始到当前处理的顶点的上一个顶点，一个有curr个顶点
     * @param result 长度为1的数组，用于记录结果，记录最长路径的顶点数
     */
    private static void findLongestPathLength(V v, int[] curr, int[] result) {
        curr[0]++;
        if (result[0] < curr[0]) {
            result[0] = curr[0];
        }

        Collection<V> vs = v.ADJ;
        // 处理领接点
        for (V t : vs) {
            findLongestPathLength(t, curr, result);
        }

        // 现场还原
        curr[0]--;
    }

    /**
     * 向图g中添加边(a, b);
     *
     * @param g 图
     * @param a 边的起始点
     * @param b 边的终点
     */
    private static void addEdge(G g, String a, String b) {


        // 判断两个顶点是否都在图中
        boolean ca = g.VERTEX.containsKey(a);
        boolean cb = g.VERTEX.containsKey(b);

        // 两个顶点都已经存在了
        if (ca && cb) {
            // 将b设置为a的邻接点
            g.VERTEX.get(a).ADJ.add(g.VERTEX.get(b));
        }
        // 顶点a已经存在，b不存在
        else if (ca && !cb) {
            V bv = new V(b);
            // 将顶点b放到顶点集合中
            g.VERTEX.put(b, bv);
            // 将b设置为a的邻接点
            g.VERTEX.get(a).ADJ.add(bv);
        }
        // 顶点a不存存，b存在
        else if (!ca && cb) {
            V av = new V(a);
            // 将顶点a放到顶点集合中
            g.VERTEX.put(a, av);
            // 将b设置为a的邻接点
            av.ADJ.add(g.VERTEX.get(b));

            // 如果b起始顶点，加入(a, b)边之后，b就不是起始顶点了
            if (g.STARTING.containsKey(b)) {
                g.STARTING.remove(b);
            }

            // a是新的起始顶点
            g.STARTING.put(a, av);

        }
        // 两个顶点都不在图中
        else {
            // 构造两个顶点
            V av = new V(a);
            V bv = new V(b);
            // 将b设置为a的邻接点
            av.ADJ.add(bv);
            // 将顶点a、b放到顶点集合中
            g.VERTEX.put(a, av);
            g.VERTEX.put(b, bv);
            // a为起始顶点
            g.STARTING.put(a, av);
        }
    }
}
