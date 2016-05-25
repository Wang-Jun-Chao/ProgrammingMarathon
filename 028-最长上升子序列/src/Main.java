import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 06:53
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
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

//            System.out.println(lis(arr));
//            System.out.println(lis2(arr));
            System.out.println(lis3(arr));
        }

        scanner.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // O(n*log(n))算法实现
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 【最优解法】
     * 求最长公共子序列长度
     *
     * @param arr 数组
     * @return 最长公共子序列长度
     */
    private static int lis3(int[] arr) {
        int len;
        int[] d = new int[arr.length + 1];

        d[0] = -1;
        d[1] = arr[0];
        len = 1;

        for (int i = 1, j; i < arr.length; i++) {
            j = find(d, 0, len, arr[i]);
            d[j] = arr[i];

            if (j > len) {
                len = j;
            }
        }

        // d[1:len]就是所求的上升序列

        return len;
    }

    /**
     * 【次优解法】
     * 求最长公共子序列长度
     *
     * @param arr 数组
     * @return 最长公共子序列长度
     */
    private static int lis2(int[] arr) {
        int[] len = new int[arr.length];
        int[] d = new int[arr.length + 1];


        // 使用最大值对d进行填充，保证在处理[0,k]时，单调递增
        Arrays.fill(d, Integer.MAX_VALUE);

        d[0] = -1;
        d[1] = arr[0];
        len[0] = 1;

        for (int i = 1, j; i < arr.length; i++) {
            j = find(d, 0, i, arr[i]);
            d[j] = arr[i];
            len[i] = j;
        }

        int max = 0;
        for (int i : len) {
            if (max < i) {
                max = i;
            }
        }

        return max;
    }

    private static int find(int[] arr, int lo, int hi, int val) {
        int mid;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;

            if (arr[mid] < val) {
                lo = mid + 1;
            } else if (arr[mid] > val) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return lo;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // O(n^2)算法实现
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 求最长公共子序列长度
     *
     * @param arr 数组
     * @return 最长公共子序列长度
     */
    private static int lis(int[] arr) {
        // len[i]表示包含arr[i]的序列的长度
        int[] len = new int[arr.length];
        // next[i]表示第i个下标的下一个数的下标是next[i]，值为-1表示结束
        int[] next = new int[arr.length];


        // 初始初化数据
        len[len.length - 1] = 1;
        for (int i = 0; i < arr.length; i++) {
            next[i] = -1;
            len[i] = 1;
        }

        for (int i = arr.length - 2; i >= 0; i--) {

            // 找最大值和下标
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[i] && len[j] >= len[i]) {
                    // 记录最大值和前一个元素
                    len[i] = len[j] + 1;
                    next[i] = j;
                }
            }
        }

        // 找最大值和初始下标
        int max = 0;
        int j = -1;
        for (int i = 0; i < len.length; i++) {
            if (max < len[i]) {
                max = len[i];
                j = i;
            }
        }

//        System.out.println(Arrays.toString(next));
//        System.out.println(Arrays.toString(len));
//        // 输出上升子序列
//        while (j != -1) {
//            System.out.print(arr[j] + " ");
//            j = next[j];
//        }
//        System.out.println();

        return max;
    }
}
