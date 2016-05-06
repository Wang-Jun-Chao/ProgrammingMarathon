import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-06 17:52
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
            System.out.println(countOne(n));
        }

        scanner.close();
    }

    private static int countOne(int n) {


        return 0;
    }
}
