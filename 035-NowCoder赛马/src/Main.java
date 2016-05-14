import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 19:43
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

            if (n == 0) {
                break;
            }

            int[] nowcode = new int[n];
            int[] other = new int[n];

            for (int i = 0; i < n; i++) {
                nowcode[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                other[i] = scanner.nextInt();
            }

            if (win(nowcode, other)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        scanner.close();
    }

    /**
     * 判断NowCode赛马是否会赢
     *
     * @param nowcode NowCode参赛的马匹速度
     * @param other   对手参赛的马匹速度
     * @return true：NowCode赢，false：对手赢
     */
    private static boolean win(int[] nowcode, int[] other) {

        // 选择排序，方便操作
        Arrays.sort(nowcode);
        Arrays.sort(other);

        int n = nowcode.length;

        // 记录赢的场次
        int w = 0;

        // 依次找比对手最慢的马快的最少的马
        for (int i = 0, j = 0; i < n && j < n; i++) {
            while (j < n) {
                // 找到比对手马快的最小的马
                if (nowcode[j] > other[i]) {
                    // 本轮可以赢，增加记数
                    w++;

                    // 如果可以赢的场次多于一半可以直接返回结果
                    if (w > n / 2) {
                        return true;
                    } else {
                        // NowCode下次可以选择的第一匹马
                        j++;
                        // 退出本次while
                        break;
                    }
                }
                // NowCode本次选择的马不比对手的快，选择下一匹
                else {
                    j++;
                }
            }
        }

        // 执行到此处说明获胜的场次不足一半
        return false;
    }
}
