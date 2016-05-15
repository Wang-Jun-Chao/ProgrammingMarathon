import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 20:19
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
            int[] rope = new int[n];
            for (int i = 0; i < n; i++) {
                rope[i] = scanner.nextInt();
            }

            System.out.println(maxWeight(rope));
        }

        scanner.close();
    }

    /**
     * 求选择的绳子可以承受的最大重量
     *
     * @param rope 绳子数组
     * @return 最大的重量
     */
    private static int maxWeight(int[] rope) {
        // 假设从一组绳子中必须包含w[x]承受能力的绳子，要想总的承受重量最大，那么只要承受能力不比
        // w[x]低的都要选择，先对数组进行排序，可以在知道某条绳子的承受能力同时还可以知道不小于它
        // 承受能力的绳子有多个少，这样子就可以求得最大值

        // 反绳子进行排序
        Arrays.sort(rope);

        int max = 0;

        for (int i = 0; i < rope.length; i++) {
            int t = rope[i] * (rope.length - i);
            if (t > max) {
                max = t;
            }
        }

        return max;
    }
}
