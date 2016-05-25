import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 22:20
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    private final static int[] TYPE = {100, 50, 20, 10, 5, 2, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            List<Integer> money = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                money.add(scanner.nextInt());
            }


            System.out.println(noChange(money));

        }

        scanner.close();
    }

    /**
     * 不用找零需要的钱币数
     *
     * @param money 工资集合
     * @return 最少需要的钱币数
     */
    private static int noChange(List<Integer> money) {
        int min = 0;
        // 对每个员工使用贪心算法
        for (int m : money) {
            for (int i = 0; i < TYPE.length && m != 0; i++) {
                min += m / TYPE[i];
                m %= TYPE[i];
            }
        }
        return min;
    }
}
