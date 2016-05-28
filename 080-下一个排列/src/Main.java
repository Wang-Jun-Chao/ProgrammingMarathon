import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 07:09
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
            int k = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            nextPermutation(arr, k);

            for (int i = 0; i < n; i++) {
                if (i != n - 1) {
                    System.out.print(arr[i] + " ");
                } else {
                    System.out.println(arr[i]);
                }
            }
        }

        scanner.close();
    }

    private static void nextPermutation(int[] arr, int k) {
        for (int i = 0; i < k; i++) {
            nextPermutation(arr);
        }
    }

    /**
     * 求下一个排列
     *
     * @param arr 当前的排列
     * @return true：当前输入的排列是最大的，false：当前输入的排列不是最大的
     */
    private static boolean nextPermutation(int[] arr) {
        int j = arr.length - 2;
        // 【第一步】
        // 找j
        while (j >= 0 && arr[j] > arr[j + 1]) {
            j--;
        }

        // 说明当前的输入是从大到小有序的，这个排列是最大的，翻转整个排列就是下一个排列
        if (j < 0) {
            for (int v = 0, w = arr.length - 1; v < w; v++, w--) {
                swap(arr, v, w);
            }

            return true;
        }

        // 找k
        int k = arr.length - 1;
        while (k > j && arr[k] <= arr[j]) {
            k--;
        }

        // 步聚二
        swap(arr, k, j);

        // 步骤三
        for (int v = j + 1, w = arr.length - 1; v < w; v++, w--) {
            swap(arr, v, w);
        }

        return false;
    }

    /**
     * 交换数组中两个元素的位位置
     *
     * @param arr 数组
     * @param v   位置一
     * @param w   位置二
     */
    private static void swap(int[] arr, int v, int w) {
        int t = arr[v];
        arr[v] = arr[w];
        arr[w] = t;
    }
}
