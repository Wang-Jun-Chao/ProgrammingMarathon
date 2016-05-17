import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 10:46
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String anemone = scanner.next();
            String crab = scanner.next();

            if ("0".equals(crab) || "0".equals(anemone)) {
                break;
            }

            if (symbiosis(anemone, crab)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        scanner.close();
    }

    /**
     * 寄居蟹和海葵的能不能共生
     *
     * @param anemone 海葵的褶皱
     * @param crab    寄居蟹的褶皱
     * @return true：可以共生，false：不可以共生
     */
    private static boolean symbiosis(String anemone, String crab) {

//        System.out.println(anemone + " " + crab);

        if (crab.length() > anemone.length()) {
            return false;
        }

        // 将寄居蟹的褶皱转换成要与寄居蟹的褶皱
        crab = convert(crab);
//        System.out.println(anemone + " " + crab);
        // 问题转换成求anemone中是否包含序列crab的问题

        int i = 0;
        int j = 0;
        int m = anemone.length();
        int n = crab.length();

        while (i < m && j < n && m - i >= n - j) {
            if (anemone.charAt(i) == crab.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }

        return j == n;
    }

    /**
     * 转换方法，因为寄居蟹和海葵的褶皱有一一对应关系，所在可以将寄居蟹的褶皱转换成要与海葵匹配的褶皱
     *
     * @param crab 寄居蟹的褶皱
     * @return 与海葵匹配的褶皱
     */
    private static String convert(String crab) {
        StringBuilder b = new StringBuilder();

        for (int i = 0, j = crab.length(); i < j; i++) {
            b.append((char) ('Z' - crab.charAt(i) + 'A'));
        }

        return b.toString();
    }


}
