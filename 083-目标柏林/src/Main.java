import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 08:15
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println(decrypt(s));
        }

        scanner.close();
    }

    /**
     * 对字符串进行解密
     *
     * @param s 字符串
     * @return 解密后的字符串
     */
    private static String decrypt(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int t = len / 2; t > 0; t--) {
            rotate(chars, t);
        }

        return new String(chars);
    }

    /**
     * 以t步长对chars进行旋转，t为奇数就逆时针旋转，偶数就顺时针
     *
     * @param chars 字符数组
     * @param t     步长
     */
    private static void rotate(char[] chars, int t) {
        int len = chars.length;
        // 偶数要顺时针旋转
        if (t % 2 == 0) {
            int lastIdx = len - len % t - 1;
            char tmp = chars[lastIdx];
            for (int i = lastIdx; i - t >= 0; i -= t) {
                chars[i] = chars[i - t];
            }

            chars[t - 1] = tmp;
        }
        // 奇数要逆时针旋转
        else {
            char tmp = chars[t - 1];
            int i;
            for (i = t - 1; i + t < len; i += t) {
                chars[i] = chars[i + t];
            }
            chars[i] = tmp;
        }
    }
}
