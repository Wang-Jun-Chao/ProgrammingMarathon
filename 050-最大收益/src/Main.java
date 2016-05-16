import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 14:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            double[] stock = new double[n];
            for (int i = 0; i < n; i++) {
                stock[i] = scanner.nextDouble();
            }

            System.out.printf("%.2f\n", maxProfit(stock));
        }

        scanner.close();
    }

    /**
     * 求最大的利润，可以买卖多次
     *
     * @param stock 股票的价格
     * @return 最大的利润
     */
    private static double maxProfit(double[] stock) {

        // 记录最大的差价，卖出时间不早于买入时间
        double profit = 10000.0;

        // 本次买入卖出可以赚的钱
        double diff = 0;
        // 股票买入时间
        int in = 0;
        // 股票卖出时间
        int out = 0;

        // 从第二个数据开始处理
        for (int i = 1, j = stock.length; i < j; i++) {

            // i时刻的股票价格比记录到的最小的价格低，在此处买进，有可能赚到钱
            if (stock[i] <= stock[in]) {

                // 说明本次股票价格比买入的还低，但是之前还有一个涨幅
                if (in != out) {
                    profit = profit * stock[out] / stock[in];
                }

                // 本次记录为买入
                in = i;
                out = i;
                // 本次买卖的差值设置为0
                diff = 0;
            }
            // 比原来买进的价格高，
            else if (stock[i] > stock[in]) {

                // 并且比之前记录到的最大差值还大。在此处卖出，能赚到更多的钱
                if (diff < stock[i] - stock[in]) {
                    out = i;
                    diff = stock[i] - stock[in];
                }
                // 比之前记录到的值小，说明股票开始走下坡路了，要卖掉股票了
                else if (diff > stock[i] - stock[in]) {
                    profit = profit * stock[out] / stock[in];
                    // 本次记录为买入
                    in = i;
                    out = i;
                    // 本次买卖的差值设置为0
                    diff = 0;
                }
            }
        }

        // 最后还要剩是因为最后一段可能是一个一直上涨的趋势
        return profit * stock[out] / stock[in];
    }

    /**
     * 第二种解法
     * 求最大的利润，可以买卖多次
     *
     * @param stock 股票的价格
     * @return 最大的利润
     */
    private static double maxProfit2(double[] stock) {
        // 最开始可使用的钱
        double profit = 10000;
        // 记录买入的时间
        int label = -1;
        for (int i = 0, j = stock.length - 1; i < j; i++) {
            // 股价在上升
            if (stock[i] < stock[i + 1]) {
                // 如果已经买入，那么就卖出
                if (label != -1) {
                    profit = profit / stock[label] * stock[i];
                }
                // 记录本次为买入
                label = i;
            }
            // 如果股票在下跌
            else {
                // 在i时间买出之前的股票
                if (label != -1) {
                    profit = profit / stock[label] * stock[i];
                }

                // 记录本次不买入
                label = -1;
            }
        }
        return profit;
    }
}
