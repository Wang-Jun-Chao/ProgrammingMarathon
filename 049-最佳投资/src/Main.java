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
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
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
     * @param stock
     * @return
     */
    private static double bestInvest(double[] stock) {


        return 0;
    }
}
