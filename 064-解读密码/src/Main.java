import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 20:51
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(decrypt(line));
        }

        scanner.close();
    }

    private static String decrypt(String s) {
        StringBuilder b = new StringBuilder(s.length());

        for (int i = 0, j = s.length(); i < j; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                b.append(c);
            }
        }

        return b.toString();
    }


}
