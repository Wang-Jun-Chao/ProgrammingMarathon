import java.util.*;

/**
 * Author: 王俊超
 * Time: 2016-05-15 21:45
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    /**
     * 哈夫曼树的节点类
     */
    private static class Node {
        // 节点保存的值
        private int value;
        // 节点在树中的高度，根节点是0层
        private int height;
        // 左子节点
        private Node left;
        // 右子节点
        private Node right;
    }

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            List<Integer> freq = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                freq.add(scanner.nextInt());
            }

            System.out.println(minSpace(freq));
        }

        scanner.close();
    }

    /**
     * 使用霍夫曼编码需要多少位空间
     *
     * @param freq 字符出现次数
     * @return
     */
    private static int minSpace(List<Integer> freq) {
        // 用于创建哈夫曼树
        List<Node> nodes = new ArrayList<>(freq.size());
        // 用于保存所有的叶子节点
        List<Node> leafs = new ArrayList<>(freq.size());

        for (int i = 0, j = freq.size(); i < j; i++) {
            Node node = new Node();
            node.value = freq.get(i);

            nodes.add(node);
            leafs.add(node);
        }

        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node u, Node v) {
                if (u == null && v == null) {
                    return 0;
                }

                // null最小
                if (u == null) {
                    return -1;
                }

                if (v == null) {
                    return 1;
                }

                return u.value = v.value;
            }
        });

        return 0;


    }
}
