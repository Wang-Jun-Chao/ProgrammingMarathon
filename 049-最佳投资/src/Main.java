import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 11:13
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNext()) {
            double[] stock = new double[9];
            for (int i = 0; i < stock.length; i++) {
                stock[i] = scanner.nextDouble();
            }

            System.out.printf("%.2f\n", bestInvest(stock));
        }

        scanner.close();
    }

    /**
     * 最佳投资方案，求股票买入卖出可以获得的最大收益
     *
     * @param stock 股票价格格数据，下标小的表示较早的时间
     * @return 最大收益，0表示不投资
     */
    private static double bestInvest(double[] stock) {

        // 记录最大的差价，卖出时间不早于买入时间
        double maxDiff = 0;
        // 股票买入时间
        int in = 0;
        // 股票卖出时间
        int out = 0;

        // 从第二个数据开始处理
        for (int i = 1, j = stock.length; i < j; i++) {

            // i时刻的股票价格比记录到的最小的价格低，在此处买进，有可能赚到钱
            if (stock[i] < stock[in]) {
                in = i;
                out = i;
                continue;
            }

            // i时刻的股票价格比记录到的最高的价格不高，并且比之前记录到的最大差值还大
            // 在此处卖出，能赚到更多的钱
            if (stock[i] > stock[out] && maxDiff < stock[i] - stock[in]) {
                out = i;
                maxDiff = stock[out] - stock[in];
            }
        }


        return maxDiff;
    }
}
