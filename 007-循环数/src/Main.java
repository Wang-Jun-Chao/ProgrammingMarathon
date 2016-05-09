import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-09 06:56
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String n = scanner.next();
            System.out.println(cycleNumber(n));
        }

        scanner.close();
    }

    /**
     * 判断n是不是循环数
     *
     * @param n 数字字符串
     * @return No：不是循环数，Yes：是循环数
     */
    private static String cycleNumber(String n) {

        int[] num = new int[n.length()];

        // 数字字符串转换成数组表示的数字
        // 下标由小到大表示低位到高位，
        for (int i = 0; i < n.length(); i++) {
            num[i] = n.charAt(n.length() - 1 - i) - '0';
        }

        //System.out.println(n + Arrays.toString(num));


        for (int i = 2; i <= 6; i++) {
            if (!check(num, i)) {
                return "No";
            }
        }

        return "Yes";
    }

    /**
     * 检查用数组表示的数字与n相乘，是否是循环数
     *
     * @param num 数组表示的数字，标由小到大表示低位到高位
     * @param n   数字
     * @return true：是循环数，false：不是循环数
     */
    private static boolean check(int[] num, int n) {

        // 来自低位的进位
        int carry = 0;
        // 结果数组
        int[] ret = new int[num.length];
        int t;

        for (int i = 0; i < num.length; i++) {
            t = carry + num[i] * n;
            ret[i] = t % 10;
            carry = t / 10;
        }

        // 计算之后还有一位说明相乘后结果多出一位，一定不循环数
        if (carry != 0) {
            return false;
        }

        // 将ret中的数字进行循环，构造新的ret，看是否与num相等
        for (int i = 0; i < ret.length; i++) {

            // 找要进行循环移动的位置
            if (num[0] == ret[i]) {
                exchange(ret, i);

                if (equal(ret, num)) {
                    return true;
                }

                // 如果不相等就还原
                exchange(ret, ret.length - i);
            }
        }


        return false;
    }

    /**
     * 将数组arr循环移动num位
     * num将数组arr分成[0, num-1]、[num, arr.length-1]两部分，先将[0, num-1]翻转
     * 再将[num, arr.length-1]翻转，最后将[0, arr.length-1]翻转可得结果
     *
     * @param arr 数组
     * @param num 移动的位数
     */
    private static void exchange(int[] arr, int num) {

        if (arr == null || num < 1 || num > arr.length) {
            return;
        }

        exchange(arr, 0, num - 1);
        exchange(arr, num, arr.length - 1);
        exchange(arr, 0, arr.length - 1);

    }

    /**
     * 将级数arr中从from到to位置的所有元素进行翻转
     *
     * @param arr  数组
     * @param from 开始位置
     * @param to   结束位置
     */
    private static void exchange(int[] arr, int from, int to) {
        if (arr == null || from < 0 || from > arr.length || to < 0 || to > arr.length) {
            return;
        }

        int t;
        while (from < to) {
            t = arr[from];
            arr[from] = arr[to];
            arr[to] = t;
            from++;
            to--;
        }
    }

    /**
     * 比较两个数组的内容是否相等
     *
     * @param a 数组
     * @param b 数组
     * @return true：相等，false：不相等
     */
    private static boolean equal(int[] a, int[] b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null || a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
