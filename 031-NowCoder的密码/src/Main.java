import java.util.LinkedList;
import java.util.List;
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
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            if (n == 0) {
                break;
            }

            int[] num = new int[n];

            for (int i = 0; i < n; i++) {
                num[i] = scanner.nextInt();
            }

            List<Integer> rst = getPrime(num);

            if (rst.isEmpty()) {
                System.out.println("NONE");
            } else {
                for (Integer i : rst) {
                    System.out.println(i);
                }
            }

        }

        scanner.close();
    }

    private static List<Integer> getPrime(int[] num) {

        List<Integer> rst = new LinkedList<>();
        List<Integer> odds = new LinkedList<>();

        if (num.length == 1) {
            int v = num[0];
            if (v == 2 || v == 3 || v == 5 || v == 7) {
                rst.add(v);
            }

            return rst;
        }


        // 记录所有的奇数
        for (int i : num) {
            if (i % 2 != 0) {
                odds.add(i);
            }
        }

        // 没有奇数，一定不可以组成素数
        if (odds.isEmpty()) {
            return rst;
        }

        for (int v : odds) {
            for (int i = 0; i < num.length - 1; i++) {
                if (num[i] == v) {
                    // 奇数固定在最后一个数字
                    swap(num, i, num.length - 1);
                    getPrime(num, 0, rst);
                }
            }
        }


        return rst;
    }

    /**
     * 将数组中的[0:(num.length-2)]的数据进行排列，并判断是不是奇数
     *
     * @param num 数组
     * @param n   当前处理的下标
     * @param rst 结果
     */
    private static void getPrime(int[] num, int n, List<Integer> rst) {

        if (n == num.length - 2 || n == num.length - 1) {
            // 求排列后的值
            int v = 0;
            for (int i : num) {
                v = v * 10 + i;
            }

            if (isPrime(v)) {
                rst.add(v);
            }

        }
        // 处理第一个到倒数第三个数字
        else if (n >= 0 && n < num.length - 2) {
            for (int i = n, j = num.length - 1; i < j; i++) {
                swap(num, i, n);
                getPrime(num, n + 1, rst);
                // 现场还原
                swap(num, i, n);
            }
        }


    }

    private static boolean isPrime(int v) {

        if (v < 2) {
            return false;
        }

        int q = (int) Math.sqrt(v);
        int i = 2;

        while (i <= q) {
            if (v % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 交接x和y两个位置的数据
     *
     * @param num 数组
     * @param x   位置
     * @param y   位置
     */
    private static void swap(int[] num, int x, int y) {
        int t = num[x];
        num[x] = num[y];
        num[y] = t;
    }
}
