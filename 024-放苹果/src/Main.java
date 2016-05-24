import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-24 20:28
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            System.out.println(placeApple(m, n));
        }

        scanner.close();
    }

    /**
     * 放苹果
     *
     * @param m 苹果个数
     * @param n 盘子个数
     * @return 共的放法数目
     */
    private static int placeApple(int m, int n) {

        // 用于保存结果
        int[] rst = {0};
        // 第一个盘子数放的苹果数
        placeApple(m, n, m, rst);
        // 下面和上面一行实现同样的效果
//        for (int i = m; i >= 0; i--) {
//            placeApple(i, n - 1, m - i, rst);
//        }

        return rst[0];
    }

    /**
     * 放苹果，后一个盘子不能比前一个盘子放的平果数多
     *
     * @param max  当前盘子最多可以放多少个苹果
     * @param n    剩下要放的盘子数目
     * @param left 剩下的苹果数目
     * @param rst  保存结果
     */
    private static void placeApple(int max, int n, int left, int[] rst) {

        // 放最后可以放的盘子
        if (n == 1) {
            // 还剩下left个，不能为负数，可以选择的数目大于剩下的数目
            if (max >= left && left >= 0) {
                rst[0]++;
            }
        }
        // 不是最后一个可以
        else if (n > 1) {
            // 当前盘子可以放[0,max个]
            for (int i = max; i >= 0; i--) {
                placeApple(i, n - 1, left - i, rst);
            }

        }
    }


}
