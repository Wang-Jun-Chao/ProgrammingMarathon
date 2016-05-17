import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Author: 王俊超
 * Time: 2016-05-17 20:27
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    // 大写字母转换成对应数字的掩码表
    private static char[] MASK = {
            '2', '2', '2', '3', '3', '3', '4', '4', '4', '5',
            '5', '5', '6', '6', '6', '7', '7', '7', '7', '8',
            '8', '8', '9', '9', '9', '9'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] tel = new String[n];
            for (int i = 0; i < n; i++) {
                tel[i] = scanner.next();
            }

            Set<String> ret = convert(tel);
            for (String s : ret) {
                System.out.println(s);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static Set<String> convert(String[] tel) {
        Set<String> rst = new TreeSet<>();
        char[] arr = new char[8];
        int count;
        for (String s : tel) {
            count = 0;
            for (int i = 0, j = s.length(); i < j; i++) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    arr[count] = c;
                    count++;
                } else if (c >= 'A' && c <= 'Z') {
                    arr[count] = MASK[c - 'A'];
                    count++;
                }

                if (count == 3) {
                    arr[count] = '-';
                    count++;
                }
            }
            rst.add(new String(arr));
        }
        return rst;
    }
}
