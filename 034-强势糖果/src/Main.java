import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 15:09
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String older = scanner.next();
            String younger = scanner.next();

            System.out.println(candy(older, younger));
        }

        scanner.close();
    }

    /**
     * 判断糖果。如果哥哥拥有的糖果数量比弟弟多，并且弟弟拥有的糖果类型哥哥同样都有，
     * 则输出“Yes”；否则输出“No”。
     *
     * @param older   哥哥拥有的糖果
     * @param younger 弟弟拥有的糖果
     * @return Yes 或者 No
     */
    private static String candy(String older, String younger) {

        if (younger.length() >= older.length()) {
            return "No";
        }

        boolean[] type = new boolean[26];

        // 标记哥哥拥有的糖果种类
        for (int i = 0, j = older.length(); i < j; i++) {
            int x = older.charAt(i) - 'A';
            type[x] = true;
        }

        for (int i = 0, j = younger.length(); i < j; i++) {
            int x = younger.charAt(i) - 'A';
            // 弟弟拥有的糖果哥哥没有
            if (!type[x]) {
                return "No";
            }
        }

        return "Yes";
    }
}
