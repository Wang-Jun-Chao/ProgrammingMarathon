import java.util.Scanner;


/**
 * Author: 王俊超
 * Time: 2016-05-09 13:55
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String m = scanner.next();
            String n = scanner.next();
            String[] r = pigeonAndRabbit(m, n);
            if (r == null) {
                System.out.println("Error");
            } else {
                System.out.println(r[0] + " " + r[1]);
            }
        }

        scanner.close();
    }

    /**
     * 计算鸽子和兔子的数目
     *
     * @param ms 鸽子和兔子的头数
     * @param ns 鸽子和兔子的脚数
     * @return 长度为2的数符串，分别表示鸽子的数量和兔子数量，如果无解就返回null
     */
    private static String[] pigeonAndRabbit(String ms, String ns) {

        int lastN = ns.charAt(ns.length() - 1) - '0';
        // ns为偶数
        if (lastN % 2 != 0) {
            return null;
        }

        int[] m = getNumber(ms);
        int[] n = getNumber(ns);

        // 鸽子数
        // 4 * m
        int[] x = multiply(m, new int[]{4});

        // 兔子数
        // 2 * m
        int[] y = multiply(m, new int[]{2});

        // 4m >= n && n >= 2m
        if (compare(x, n) >= 0 && compare(n, y) >= 0) {

            // 4m - n
            x = minus(x, n);
            // (4m - n) / 2
            x = divide2(x);

            // n - 2m
            y = minus(n, y);
            // (n - 2m) / 2
            y = divide2(y);

            return new String[]{toNumber(x), toNumber(y)};

        } else {
            return null;
        }
    }

    /**
     * 将整数字符串表示成整数数组
     *
     * @param n 整数字符串
     * @return 整数数组 下标从小到大表示数位的从低到高
     */
    private static int[] getNumber(String n) {
        int[] r = new int[n.length()];
        for (int i = 0; i < r.length; i++) {
            r[i] = n.charAt(n.length() - i - 1) - '0';
        }

        return r;
    }

    /**
     * 两个数相乘
     *
     * @param m 乘数
     * @param n 乘数
     * @return 结果
     */
    private static int[] multiply(int[] m, int[] n) {

        // 结果最多的位数
        int[] r = new int[m.length + n.length];
        // 来自低位的进位
        int c;

        int t;
        int k;

        for (int i = 0; i < n.length; i++) {
            // 计算n[i]*m

            if (n[i] == 0) {
                continue;
            }

            c = 0;
            for (int j = 0; j < m.length; j++) {
                t = n[i] * m[j] + r[i + j] + c;
                r[i + j] = t % 10;
                c = t / 10;

            }

            // 如果还有进位要继续处理
            k = i + m.length;
            while (c != 0) {
                t = c + r[k];
                r[k] = t % 10;
                c = t / 10;
                k++;
            }
        }

        return r;
    }

    /**
     * 两个整数相加
     *
     * @param m 整数
     * @param n 整数
     * @return 结果
     */
    private static int[] add(int[] m, int[] n) {

        // 保证n不小于m
        if (m.length > n.length) {
            int[] t = m;
            m = n;
            n = m;
        }

        // 结果的最大长度
        int[] r = new int[n.length + 1];
        // 来自低位的进位
        int c = 0;

        for (int i = 0; i < m.length; i++) {
            r[i] = m[i] + n[i] + c;
            c = r[i] / 10;
            r[i] %= 10;
        }

        // 计算余下的部分
        for (int i = m.length; i < n.length; i++) {
            r[i] = m[i] + c;
            c = r[i] / 10;
            r[i] %= 10;
        }

        // 最后还有进位
        if (c != 0) {
            r[r.length - 1] = c;
            return r;
        }
        // 没有进位
        else {
            int[] ret = new int[r.length - 1];
            System.arraycopy(r, 0, ret, 0, ret.length);
            return ret;
        }
    }

    /**
     * 比较两个整数是否相等，下标由小到大表示由低位到高位，忽略最高有效位上的前导0
     *
     * @param m 整数
     * @param n 整数
     * @return m > n返回1，m = n返回0，m < n返回-1
     */
    private static int compare(int[] m, int[] n) {

        if (m == null && n == null) {
            return 0;
        }
        // null最小
        if (m == null) {
            return -1;
        }

        if (n == null) {
            return 1;
        }

        int lastM = m.length - 1;
        int lastN = n.length - 1;

        // 找m的最高有效位的位置，至少有一位
        while (lastM >= 1 && m[lastM] == 0) {
            lastM--;
        }
        // 找n的最高有效位的位置，至少有一位
        while (lastN >= 1 && n[lastN] == 0) {
            lastN--;
        }

        // m的数位比n多，说明m比n大
        if (lastM > lastN) {
            return 1;
        }
        // m的数位比n少，说明m比n小
        else if (lastM < lastN) {
            return -1;
        } else {
            // 位数一样，比较每一个数位上的值，从高位到低位进行比较
            for (int i = lastM; i >= 0; i--) {
                if (m[i] > n[i]) {
                    return 1;
                } else if (m[i] < n[i]) {
                    return -1;
                }
            }

            return 0;
        }
    }

    /**
     * 做减法n-m，保证n大于等于m
     *
     * @param n 整数
     * @param m 整数
     * @return 结果
     */
    private static int[] minus(int[] n, int[] m) {

        n = format(n);
        m = format(m);

        int[] r = new int[n.length];

        // 当前位被借位
        int c = 0;
        int t;
        for (int i = 0; i < m.length; i++) {
            t = n[i] - c - m[i];
            // 当前位够减
            if (t >= 0) {
                r[i] = t;
                // 没有进行借位
                c = 0;
            }
            // 不够减
            else {
                r[i] = t + 10;
                // 进行借位
                c = 1;
            }
        }

        // 还有借位
        for (int i = m.length; c != 0 && i < n.length; i++) {
            t = n[i] - c;
            // 当前位够减
            if (t >= 0) {
                r[i] = t;
                // 没有进行借位
                c = 0;
            }
            // 不够减
            else {
                r[i] = t + 10;
                // 进行借位
                c = 1;
            }
        }

        return format(r);
    }

    /**
     * 将整数进行格式化，去掉高位的前导0
     *
     * @param r 整数
     * @return 结果
     */
    private static int[] format(int[] r) {
        int t = r.length - 1;
        // 找最高有效位
        while (t > 0 && r[t] == 0) {
            t--;
        }

        int[] nr = new int[t + 1];
        System.arraycopy(r, 0, nr, 0, nr.length);
        return nr;

    }

    /**
     * 将数n除以2
     *
     * @param n 整数
     * @return 结果
     */
    private static int[] divide2(int[] n) {
        // 结果
        int[] r = new int[n.length];
        // 上一位除以2后的余数
        int c = 0;
        int t;

        for (int i = n.length - 1; i >= 0; i--) {
            t = c * 10 + n[i];
            r[i] = t / 2;
            c = t % 2;
        }

        return format(r);
    }

    /**
     * 将数组表示的整数转换成字符串
     *
     * @param r 整数
     * @return 字符串表示的整数
     */
    private static String toNumber(int[] r) {
        if (r == null) {
            return null;
        }

        StringBuilder b = new StringBuilder(r.length);

        for (int i = r.length - 1; i >= 0; i--) {
            b.append(r[i]);
        }

        return b.toString();
    }
}
