import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 20:22
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

            int[] chinese = new int[m];
            int[] american = new int[n];

            for (int i = 0; i < m; i++) {
                chinese[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                american[i] = scanner.nextInt();
            }

            System.out.println(minDiff(chinese, american));

        }

        scanner.close();
    }

    /**
     * 中美两支代表队中身高最接近的两位队员的身高之差。
     *
     * @param chinese  中国队
     * @param american 美国队
     * @return 最小的身高差
     */
    private static int minDiff(int[] chinese, int[] american) {
        // 排序操作
        Arrays.sort(chinese);
        Arrays.sort(american);

        // 记录最小的身高差
        int min = Integer.MAX_VALUE;
        int m = chinese.length;
        int n = american.length;
        int i = 0;
        int j = 0;

        // 找最小的身高差
        while (i < m && j < n) {

            // 求本次最小的身高差
            int v = Math.abs(chinese[i] - american[j]);

            // 新的身高差比记录到的小就更新最小的身高差
            if (v < min) {
                min = v;
            }

            // 如果相等就直接返回0，不可能有比0还小身高差
            if (chinese[i] == american[j]) {
                return 0;
            }
            // 当前中国代表身高高，取美国下一个不低于现在这个美国代表身高的代表进行比较
            else if (chinese[i] > american[j]) {
                j++;
            }
            // 当前美国代表身高高，取中国下一个不低于现在这个中国代表身高的代表进行比较
            else {
                i++;
            }
        }

        return min;
    }

}
