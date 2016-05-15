import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 08:09
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = 24;
            int[] num = new int[24];

            for (int i = 0; i < n; i++) {
                num[i] = scanner.nextInt();
            }

            System.out.println(floating(num));
        }

        scanner.close();
    }

    /**
     * 按浮动价格的变化从大到小，输出对应时刻的变化
     *
     * @param num 价格变数组
     * @return 结果
     */
    private static String floating(int[] num) {


        // 处理操作
        int[] arr = new int[num.length * 2];

        for (int i = 0; i < num.length; i++) {
            // 记录序号
            arr[2 * i] = i;
            // 记录值
            arr[2 * i + 1] = Math.abs(num[i]);
        }

        sort(arr);

        // 记录结果
        StringBuilder b = new StringBuilder(num.length);

        for (int i = 0; i < arr.length; i += 2) {
            b.append(arr[i]).append(' ');
        }

        return b.substring(0, b.length() - 1);
    }


    /**
     * 插入排序
     * 对数组进行排序，偶数下标的内容表示时刻，奇数下标表示对应时刻的价格波动的绝对值
     *
     * @param arr 数组
     */
    private static void sort(int[] arr) {
        int j;
        int idx;
        int tmp;

        // 奇数下标表示对应时刻的价格波动的绝对值
        // 从第二个浮动价格开始处理，对应的下标是3
        for (int i = 3; i < arr.length; i += 2) {
            tmp = arr[i];
            idx = arr[i - 1];
            for (j = i; j > 1 && tmp > arr[j - 2]; j -= 2) {
                arr[j] = arr[j - 2];
                arr[j - 1] = arr[j - 3];
            }

            arr[j] = tmp;
            arr[j - 1] = idx;
        }
    }

    /**
     * 交接数组中x，y两个位置的值
     *
     * @param arr 数组
     * @param x   位置
     * @param y   位置
     */
    private static void swap(int[] arr, int x, int y) {
        int t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }
}
