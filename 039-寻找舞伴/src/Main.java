import java.util.Arrays;
import java.util.Scanner;


/**
 * Author: 王俊超
 * Time: 2016-05-15 08:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            int[] boy = new int[m];
            int[] girl = new int[n];

            for (int i = 0; i < m; i++) {
                boy[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                girl[i] = scanner.nextInt();
            }

            System.out.println(match(boy, girl));
        }

        scanner.close();
    }

    private static int match(int[] boy, int[] girl) {

        Arrays.sort(boy);
        Arrays.sort(girl);

        int rst = 0;
        int i = 0;
        int j = 0;

        while (i < boy.length && j < girl.length) {
            if (boy[i] == girl[j]) {
                rst++;
                i++;
                j++;
            } else if (boy[i] - girl[j] > 0) {
                j++;
            } else {
                i++;
            }
        }

        return rst;
    }
}
