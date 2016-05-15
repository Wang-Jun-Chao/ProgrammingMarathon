import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 08:57
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

        private boolean isLeaf;
        // 子节点
        private Node[] next = new Node[10];

        Node(char val) {
            this.val = val;
            this.num = 1;
            this.isLeaf = true;
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

            boolean rst = conflictNumber(words);
            if (rst) {
                System.out.println("Yes");
            } else {
                System.out.println("No");

            }
        }

        scanner.close();
    }

    /**
     * 冲突的电话号码
     *
     * @param words 电话号码集合
     * @return 电话号码集合是否冲突
     */
    private static Boolean conflictNumber(List<String> words) {
        Node root = createTier(words);
        return root == null;
    }

    /**
     * 创建字典树，并且在创建过程中判断是不是发生冲突。冲突的条件就是一个数字串不能是
     * 另一个数字串的前缀，表现在创建过程中的是，每添加一个数字串必须要创建节点，并且
     * 第一次添加的节点是非叶子节点的子结点，第一个添加的数字串除外
     *
     * @param words 数字串集合
     * @return 字典树的根节点，如果发生冲突返回null
     */
    private static Node createTier(List<String> words) {

        // 根结点，不用于存放数据，只作操作用
        Node root = new Node('-');
        // 第几个数字串，用于判断是不是第一次添加
        int count = 0;

        // 每一次添加一个数字串，其创建的结点数，用于判断每一个数字串，
        // 是不是第一次添节结点
        int countNode;
        Node prev;
        for (String s : words) {
            count++;
            countNode = 0;
            prev = root;
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (prev.next[c - '0'] != null) {
                    prev.next[c - '0'].inc();
                } else {

                    countNode++;
                    // 不第一次添加数字串，但是是第一次之后的数字串添加过程中第一次创建子节点
                    // 说明发生了冲突
                    if (count > 1 && countNode == 1 && prev.isLeaf) {
                        return null;
                    }

                    // 设置父节点不是子节点
                    prev.isLeaf = false;
                    prev.next[c - '0'] = new Node(c);
                }

                prev = prev.next[c - '0'];
            }

            // 添加一个数字串，如果没有创建过结点，说明发生了冲突，
            // 最新加入的数字串是另一个的前缀
            if (countNode == 0) {
                return null;
            }
        }

        return root;
    }
}
