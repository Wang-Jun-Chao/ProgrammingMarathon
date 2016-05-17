import java.util.*;

/**
 * Author: 王俊超
 * Time: 2016-05-16 19:44
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
        private String value;
        // 从根结点到当前节点的序列数，当序列为1说明其可以成为一个前缀
        private int num;
        // 子节点
        private Map<String, Node> next = new HashMap<>();

        Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            List<String> dirs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                dirs.add(scanner.next());
            }

            dirs = makeDir(dirs);
            for (String s : dirs) {
                System.out.println("mkdir -p " + s);
            }

            System.out.println();

        }

        scanner.close();
    }

    /**
     * 创建目录
     *
     * @param dirs 目录集合
     * @return 真正要创建的目录
     */
    private static List<String> makeDir(List<String> dirs) {
        List<String> rst = new ArrayList<>();

        // 首先进行排序操作
        Collections.sort(dirs);

        // 第一个到倒数第二个目录，只如果目录数小于2，for不会执行
        for (int i = 0, j = dirs.size() - 1; i < j; i++) {
            String curr = dirs.get(i);
            String next = dirs.get(i + 1);
            // 如果后一个目录不是以前一个目录开头，就要创建前一个目录，加上/是考虑下面的情况
            // /a/b
            // /a/ba
            if (!next.startsWith(curr + "/")) {
                rst.add(curr);
            }
        }

        // 最后一个目录是一定要创建的
        if (!dirs.isEmpty()) {
            rst.add(dirs.get(dirs.size() - 1));
        }

        return rst;
    }


    /**
     * 创建目录
     *
     * @param dirs 目录集合
     * @return 真正要创建的目录
     */
    private static List<String> makeDir2(List<String> dirs) {
        List<String> rst = new ArrayList<>();
        List<String> curr = new ArrayList<>();

        // 根据目录创建字典树，有多少个叶子节点就要创建多少个目录
        Node root = createTier(dirs);

        // 从每一个数据节点的根节点开始遍历
        for (Node n : root.next.values()) {
            findPath(n, curr, rst);
        }


        return rst;
    }

    /**
     * 找根节点到叶子节点的路径
     *
     * @param root 根节点
     * @param curr 记录当前的路径
     * @param rst  保存根节点到叶子节点路径
     */
    private static void findPath(Node root, List<String> curr, List<String> rst) {
        // 节点为空不执行
        if (root == null) {
            return;
        }

        // 当前节点值添加到路径中
        curr.add(root.value);

        // 如果已经是根结点
        if (root.next.isEmpty()) {
            StringBuilder b = new StringBuilder(256);
            // 构造目录路径
            for (String s : curr) {
                b.append('/').append(s);
            }

            // 添加到结果当中
            rst.add(b.toString());
        } else {

            // 如果不是叶子节点，对每一个子节点做递归操作
            for (Node t : root.next.values()) {
                findPath(t, curr, rst);
            }
        }

        // 现场还原
        curr.remove(curr.size() - 1);
    }


    /**
     * 创建字典树
     *
     * @param words 字词集合
     * @return 字典树的根节点
     */
    private static Node createTier(List<String> words) {


        // 根结点，不用于存放数据，只作操作用
        Node root = new Node(null);
        Node prev;
        for (String s : words) {

            String[] part = s.split("/");
            prev = root;
            int len = part.length;
            for (String str : part) {
                if (str.length() > 0) {
                    if (!prev.next.containsKey(str)) {
                        prev.next.put(str, new Node(str));
                    }
                    prev = prev.next.get(str);
                }
            }
        }

        return root;
    }

}
