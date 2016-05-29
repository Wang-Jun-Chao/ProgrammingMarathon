import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-06 17:52
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
            System.out.println(countOne(n));
        }

        scanner.close();
    }

    /**
     * 计算[1-n]中包含数字1的数字个数
     *
     * @param n 最在范围
     * @return 包含数字1的数字个数
     */
    private static int countOne(int n) {

        int countedN = 0;
        int result = 0;

        // 从右向左分析n的每一位；for循环中：i 表示分析到了哪一位，i=1表示个位，i=10表示十位，以此类推；
        // onesPerI 表示从0到i-1含有1的数的个数，0，1，19 ...；
        // cur 是目前分析的那一位的数值；
        // 举个例子： f(m,n) 表示从m到n，含有1的数字的个数。
        // f(1,500) = f(1, 99)+f(100, 199)+f(200, 299)+(300, 399)+f(400, 499)
        // f(1, 99) = f(200, 299) = f(300, 399) = f(400, 499)
        // f(100, 199) = 100
        for (int i = 1, onesPerI = 0, cur; n != 0; onesPerI = onesPerI * 9 + i, i *= 10, n /= 10) {

            // 当前数位的数值
            cur = n % 10;

            if (cur == 0) {
                continue;
            } else if (cur == 1) {
                result = onesPerI + countedN + 1;
            } else {
                result += (cur - 1) * onesPerI + i;
            }

            // 表示比第i位以及比第i位低的各位的数值，比如abcdef，现在处理万位，那么countN就是bcdef
            countedN += cur * i;

        }

        return result;
    }
}
