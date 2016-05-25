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
        // 左子节点
        private Node left;
        // 右子节点
        private Node right;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    /**
     * 节点比较器类
     */
    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {

            if (o1 == null && o2 == null) {
                return 0;
            }

            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            return o1.value - o2.value;
        }
    }

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
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

        // 没有数据
        if (freq == null || freq.size() == 0) {
            return 0;
        }
        // 只有一个数据
        else if (freq.size() == 1) {
            return freq.get(0);
        }

        SortedSet<Node> set = new TreeSet<>(new NodeComparator());

        for (int i : freq) {
            set.add(new Node(i));
        }

        // 构造哈夫曼树
        while (set.size() > 1) {
            System.out.println(set);
            Node n1 = set.first();
            set.remove(n1);
            Node n2 = set.first();
            set.remove(n2);

            Node n3 = new Node(n1.value + n2.value);
            n3.left = n1;
            n3.right = n2;
            set.add(n3);
        }

        System.out.println(set);

        int[] rst = {0};
        minSpace(set.first(), 0, rst);
        return rst[0];
    }

    private static void minSpace(Node root, int i, int[] rst) {

        if (root != null) {
            if (root.left == null && root.right == null) {
                rst[0] += i * root.value;
                return;
            }

            if (root.left != null) {
                minSpace(root.left, i + 1, rst);
            }

            if (root.right != null) {
                minSpace(root.right, i + 1, rst);
            }
        }

    }
}
