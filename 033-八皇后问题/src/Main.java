import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 14:14
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    // 保存8皇后问题的所有解，并且解有序
    private final static List<String> R = new ArrayList<>();

    public static void main(String[] args) {
        init();
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            list.add(n);
        }
        scanner.close();

        List<String> rst = nQueen(list);

        for (String s : rst) {
            System.out.println(s);
        }

    }

    /**
     * 求N皇后的指定编号的解法
     *
     * @param list 解法的编号
     * @return 编号对应的解法
     */
    private static List<String> nQueen(List<Integer> list) {
        List<String> rst = new ArrayList<>();

        for (int i : list) {
            rst.add(R.get(i - 1));
        }
        return rst;
    }

    /**
     * 求8皇后的所有解法
     */
    private static void init() {

        int N = 8;
        // 0号下标不使用
        int[] q = new int[N + 1];

        StringBuilder b = new StringBuilder(N);

        // 【说明】因为k的值从小到到大变化，皇后放置的位置从左到右进行尝试，
        // 所以每次求出来的最新结点都比之前的大，结果产生的先后顺序表明了结
        // 果在结果集中有序的位置。

        // 表示处理第几个皇后
        int k = 1;
        while (k > 0) {
            // 将第k个皇后向右移动一个方格
            q[k]++;
            // 判断方案是否可以放置皇后，不可以就一直向右移动，直到移出了棋盘
            // q[k] > N 表示移动了棋盘
            while (q[k] <= N && !canPlace(k, q)) {
                q[k]++;
            }

            // 第k个皇后找到了合适的位置
            if (q[k] <= N) {
                // 如果是已经最后一个皇后了，说明已经找到了一种解决方案
                if (k == N) {
                    // 删除原来的数据，记录新的结果
                    b.delete(0, N);
                    for (int i = 1; i <= N; i++) {
                        b.append(q[i]);
                    }

                    // 结果添加到结果集中
                    R.add(b.toString());
                } else {
                    // 处理下一个皇后
                    k++;
                    // 清零操作，说明第k个皇后还未放到棋盘中
                    q[k] = 0;
                }
            }
            // 没有找到第k个皇后的合适位置，说明要修改上一个皇后的位置
            else {
                k--;
            }
        }
    }

    /**
     * 判断是否可以放第k个皇后
     *
     * @param k 第k个皇后
     * @param q 已经放好1~k-1个皇后的棋盘
     * @return true可以放，false不可以放
     */
    private static boolean canPlace(int k, int[] q) {
        for (int i = 1; i < k; i++) {
            // Math.abs(k - i) == Math.abs(q[k] - q[i]说明是对角线
            // q[k] == q[i]说明放在了同一列
            if (Math.abs(k - i) == Math.abs(q[k] - q[i]) || q[k] == q[i]) {
                return false;
            }
        }
        return true;
    }
}
