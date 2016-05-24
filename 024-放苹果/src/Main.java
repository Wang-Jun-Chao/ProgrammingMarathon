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
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            System.out.println(placeApple3(m, n));
        }

        scanner.close();
    }

    /**
     * 解题分析：
     * 设f(m,n) 为m个苹果，n个盘子的放法数目，则先对n作讨论，
     * 当n>m：必定有n-m个盘子永远空着，去掉它们对摆放苹果方法数目不产生影响。即if(n>m) f(m,n) = f(m,m)
     * 当n<=m：不同的放法可以分成两类：
     * 1、有至少一个盘子空着，即相当于f(m,n) = f(m,n-1);
     * 2、所有盘子都有苹果，相当于可以从每个盘子中拿掉一个苹果，不影响不同放法的数目，即f(m,n) = f(m-n,n).
     * 而总的放苹果的放法数目等于两者的和，即 f(m,n) =f(m,n-1)+f(m-n,n)
     * 递归出口条件说明：
     * 当n=1时，所有苹果都必须放在一个盘子里，所以返回１；
     * 当没有苹果可放时，定义为１种放法；
     * 递归的两条路，第一条n会逐渐减少，终会到达出口n==1;
     * 第二条m会逐渐减少，因为n>m时，我们会return f(m,m)　所以终会到达出口m==0．
     */

    /**
     * 放苹果
     *
     * @param m 苹果个数
     * @param n 盘子个数
     * @return 共的放法数目
     */
    private static int placeApple3(int m, int n) {

        int row = m + 1;
        int col = n + 1;

        int[][] dp = new int[row][col];

        for (int i = 1; i < row; i++) {
            dp[i] = new int[n + 1];
        }

        for (int i = 1; i < row; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < col; i++) {
            dp[1][i] = 1;
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (j > i) {
                    dp[i][j] = dp[i][i];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - j][j];
                }
            }
        }


        return dp[m - 1][n - 1];
    }


    /**
     * 放苹果
     *
     * @param m 苹果个数
     * @param n 盘子个数
     * @return 共的放法数目
     */
    private static int placeApple2(int m, int n) {  //m个苹果放在n个盘子中共有几种方法
        //因为我们总是让m>=n来求解的，所以m-n>=0,所以让m=0时候结束，如果改为m=1，
        //则可能出现m-n=0的情况从而不能得到正确解
        if (m == 0 || n == 1) {
            return 1;
        }

        if (n > m) {
            return placeApple2(m, m);
        } else {
            return placeApple2(m, n - 1) + placeApple2(m - n, n);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // 下面的方法时间复杂度过高，发生了子问题重叠
    /////////////////////////////////////////////////////////////////////////////////////

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
