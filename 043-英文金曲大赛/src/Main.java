import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 20:53
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    private final static DecimalFormat DF = new DecimalFormat("#0.00");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        double[] score = new double[7];
        while (scanner.hasNext()) {

            for (int i = 0; i < score.length; i++) {
                score[i] = scanner.nextDouble();
            }

            String name = scanner.next();
            System.out.println(name + " " + avg(score));
        }

        scanner.close();
    }

    private static String avg(double[] score) {
        Arrays.sort(score);
        double sum = 0;
        for (int i = 1, j = score.length - 1; i < j; i++) {
            sum += score[i];
        }

        sum /= score.length - 2;

        return DF.format(sum);
    }
}
