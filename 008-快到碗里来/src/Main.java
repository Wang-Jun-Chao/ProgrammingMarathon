import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-09 08:44
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String cat = scanner.next();
            String bowl = scanner.next();
            System.out.println(toTheBowl(cat, bowl));
        }

        scanner.close();
    }

    /**
     * 判断猫是否可以进到碗里
     *
     * @param cat  猫的长度
     * @param bowl 碗的半径
     * @return Yes：猫可以到碗里，false：猫不可以到碗里
     */
    private static String toTheBowl(String cat, String bowl) {

        // 200*PI
        int[] PI = {8, 2, 6};
        // cat的值要放大100倍
        int[] n = new int[cat.length() + 2];
        int[] m = new int[bowl.length()];
        // 将cat转换成数值，并且放大100倍
        for (int i = 0; i < cat.length(); i++) {
            n[i + 2] = cat.charAt(cat.length() - i - 1) - '0';
        }

        // bowl转换成数值
        for (int i = 0; i < bowl.length(); i++) {
            m[i] = bowl.charAt(bowl.length() - i - 1) - '0';
        }

        int[] r = calculate(m, PI);

        if (compare(r, n) >= 0) {
            return "Yes";
        } else {
            return "No";
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
     * 两个数相乘
     *
     * @param m 乘数
     * @param n 乘数
     * @return 结果
     */
    private static int[] calculate(int[] m, int[] n) {

        if (n == null || n.length < 1 || m == null || m.length < 1) {
            return null;
        }

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
}
