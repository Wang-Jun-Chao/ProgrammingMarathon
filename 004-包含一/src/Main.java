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

        // 以数字n（假设其表示为：abcdef）为例
        // i表示每一位上的权重，f为第一位，权重为1，e为第二位，权重为10，d为第三位权重是100，以此类推
        // cur表求当前处理的位的数值，表示a、b、c、d、e、f
        //
        for (int i = 1, onesPerI = 0, cur; n != 0; onesPerI = onesPerI * 9 + i, i *= 10, n /= 10) {

            cur = n % 10;

            if (cur == 0) {
                continue;
            } else if (cur == 1) {
                result = onesPerI + countedN + 1;
            } else {
                result += (cur - 1) * onesPerI + i;
            }

            countedN += cur * i;

        }

        return result;
    }
}
