import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-16 21:10
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    private final static char[] UPPER = {
            'E', 'C', 'F', 'A', 'J', 'K', 'L', 'B', 'D', 'G',
            'H', 'I', 'V', 'W', 'Z', 'Y', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'X'};
    private final static char[] LOWER = {
            'e', 'r', 'w', 'q', 't', 'y', 'g', 'h', 'b', 'n',
            'u', 'i', 'o', 'p', 's', 'j', 'k', 'd', 'l', 'f',
            'a', 'z', 'x', 'c', 'v', 'm'};

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            System.out.println(convert(s));
        }

        scanner.close();
    }

    private static String convert(String s) {
        StringBuilder b = new StringBuilder();

        for (int i = 0, j = s.length(); i < j; i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                b.append(UPPER[c - 'A']);
            } else if (c >= 'a' && c <= 'z') {
                b.append(LOWER[c - 'a']);
            } else {
                b.append(c);
            }
        }

        return b.toString();
    }


}
