import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 13:19
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
            System.out.println(firstParent(m, n));
        }

        scanner.close();
    }

    /**
     * 求第一个公共父节点，参数都大于0
     *
     * @param m 子节点
     * @param n 子节点
     * @return 第一个公共父节点
     */
    private static int firstParent(int m, int n) {

        while (m != n) {
            if (m < n) {
                n /= 2;
            } else {
                m /= 2;
            }
        }

        return n;
    }
}
