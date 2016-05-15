import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 14:48
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    /**
     * 字典树
     */
    private static class Node {
        // 节点保存的内容
        private char val;
        // 从根结点到当前节点的序列数，当序列为1说明其可以成为一个前缀
        private int num;
        // 子节点
        private Node[] next = new Node[26];

        Node(char val) {
            this.val = val;
            this.num = 1;
        }

        /**
         * 计数加一
         */
        void inc() {
            num++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            List<String> words = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                words.add(scanner.next());
            }

            List<String> rst = shortestPrefix(words);

            for (String s : rst) {
                System.out.println(s);
            }

            System.out.println();
        }

        scanner.close();
    }

    /**
     * 求字词集合各个单词的最短前缀
     *
     * @param words 单词集合
     * @return 各单词的最短前缀
     */
    private static List<String> shortestPrefix(List<String> words) {

        Node root = createTier(words);
        List<String> rst = new ArrayList<>();

        // 求最短前缀
        for (String s : words) {
            rst.add(prefix(root, s));
        }

        return rst;
    }

    /**
     * 在字典树中找s的最短前缀
     *
     * @param root 字典树的根节点
     * @param s    字符串
     * @return 最短前缀
     */
    private static String prefix(Node root, String s) {

        // 记录前缀的长度
        int len;
        Node prev = root;

        // 从s的第一个字符开始处理
        for (len = 0; len < s.length(); len++) {
            char c = s.charAt(len);

            // node表示与c对应的结点，比如c第s的第三个字符，node表示字典树第三层的结点
            // （从数据层开始计算），并且如果node不为null有node.val=c成立
            Node node = prev.next[c - 'a'];

            // 结点为空，说明无c字符对应的结点，退出for
            if (node == null) {
                break;
            } else {
                // 刚好是唯一前缀
                if (node.num == 1) {
                    // 设置前缀长度，退出for
                    len++;
                    break;
                } else {
                    // 不是唯一节点，处理下一个字符
                    prev = node;
                }
            }
        }

        // 长度小于0，说明没有前缀
        if (len < 1) {
            return null;
        }

        // 求出最短前缀
        return s.substring(0, len);
    }

    /**
     * 创建字典树
     *
     * @param words 字词集合
     * @return 字典树的根节点
     */
    private static Node createTier(List<String> words) {
        // 根结点，不用于存放数据，只作操作用
        Node root = new Node('-');
        Node prev;
        for (String s : words) {
            prev = root;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (prev.next[c - 'a'] != null) {
                    prev.next[c - 'a'].inc();
                } else {
                    prev.next[c - 'a'] = new Node(c);
                }

                prev = prev.next[c - 'a'];
            }
        }

        return root;
    }

    private static void printTier(Node root) {
        StringBuilder b = new StringBuilder();
        for (Node n : root.next) {
            if (n != null) {
                printTier(n, b);
            }
        }
    }

    private static void printTier(Node n, StringBuilder b) {
        if (n != null) {
            b.append(n.val);
            boolean child = false;
            for (Node t : n.next) {
                if (t != null) {
                    child = true;
                    printTier(t, b);
                }
            }

            if (!child) {
                System.out.println(b);
            }

            b.deleteCharAt(b.length() - 1);
        }
    }
}
