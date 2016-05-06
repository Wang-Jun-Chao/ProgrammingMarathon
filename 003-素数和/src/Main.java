import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-06 15:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private final static int NUM = 100_000 + 1;
    private final static int[] ANS = new int[NUM];

    public static void main(String[] args) {

        init();
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            System.out.println(ANS[n]);
        }

        scanner.close();
    }

    private static void init() {

        // 标记是否是素数
        // false表示是素数
        // true表示不是素数
        boolean[] mark = new boolean[NUM];

        mark[0] = true;
        mark[1] = true;

        for (int i = 2; i < mark.length; i++) {
            // i是质数
            if (!mark[i]) {
                for (int j = 2 * i; j < mark.length; j += i) {
                    mark[j] = true;
                }
            }
        }
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i < mark.length; i++) {
            if (!mark[i]) {
                list.add(i);
            }
        }

        int v;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {

                v = list.get(i) + list.get(j);
                if (v < NUM) {
                    ANS[v]++;
                }
            }
        }
    }
}
