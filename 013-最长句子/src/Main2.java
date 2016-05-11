import java.util.*;

/**
 * 解法二
 * Author: 王俊超
 * Time: 2016-05-10 22:01
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();

            // 顶点集合，同是记录顶点为终点的最长有向线段的顶点数
            // key（=String）为起始顶点，value（=Integer）为以key结点的最长有向线段的顶点数，当只有一个顶点时value为1
            Map<String, Integer> vertex = new HashMap<>();
            // 图
            // 记录以key（=String）开为起始顶点的有向边，value（List<String>）邻接顶点集合
            Map<String, List<String>> graph = new HashMap<>();

            for (int i = 0; i < row; i++) {
                // 输入两个单词，同时也表示两个顶表示的有向边
                String a = scanner.next();
                String b = scanner.next();

                // 如果是新的顶点，就加入到顶点集合中
                if (!vertex.containsKey(a)) {
                    vertex.put(a, 1);
                }
                if (!vertex.containsKey(b)) {
                    vertex.put(b, 1);
                }

                // 获取顶点a的有邻接顶点集合，如果集合不存就创建
                List<String> list = graph.get(a);
                if (list == null) {
                    list = new ArrayList<>();
                    graph.put(a, list);
                }
                // 添加a的邻接顶点b
                list.add(b);
                visitAll(a, b, vertex, graph);
            }

            int max = 0;
            for (Integer val : vertex.values()) {
                if (val > max) {
                    max = val;
                }
            }
            System.out.println(max);
        }

    }

    /**
     * 更新以b为终点的最长有向线段的顶点数，其中(a, b)表示新添加的有向线段
     *
     * @param a      顶点
     * @param b      顶点
     * @param vertex 顶点集合
     * @param graph  有向图
     */
    private static void visitAll(String a, String b, Map<String, Integer> vertex, Map<String, List<String>> graph) {
        // 以b为终点的最长线段包含的顶点数
        int val = vertex.get(b);
        // 原先以a为终点的最长线段包含的顶点数，再加上1，表示从包含(a, b)，
        // 以b为终点的最长线段包含的顶点数
        int t = vertex.get(a) + 1;
        // 记录以b为终点的最长有向线段的顶点数
        if (val < t) {
            vertex.put(b, t);

            // 接在b后面的顶点都要进行更新
            List<String> list = graph.get(b);
            if (list != null) {
                for (String s : list) {
                    visitAll(b, s, vertex, graph);
                }
            }
        }
        // 以b为终点的最长有向线段的顶点数没有发生变化，就不需要再进行处理
    }
}
