import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 14:43
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            String list = scanner.nextLine();
            String name = scanner.nextLine();

            if (important(list, name)) {
                System.out.println("Important!");
            } else {
                System.out.println("Ignore");
            }
        }

        scanner.close();
    }

    private static boolean important(String list, String name) {

        int i = 0;
        int j = 0;
        int m = list.length();
        int n = name.length();

        while (i < m) {
            // name从头开始
            j = 0;
            // 记录名字的开始位置
            int beg = i;
            // 记录名字的结束位置
            int end = i;
            // 如果名字中含有多个单词，"的一下一个才是名字的内容
            if (end < m && list.charAt(end) == '"') {
                end++;
                beg++;
            }

            // 如果名字以"开头（名字含有多个单词），就要找下一个”"“的位置
            if (list.charAt(i) == '"') {
                i = end;
                do {
                    i++;
                } while (i < m && list.charAt(i) != '"');

                end = i;

                i += 2;
            }
            // 名字是单个单词
            else {
                i = end;
                // 找下一个,号的位置
                do {
                    i++;
                } while (i < m && list.charAt(i) != ',');

                end = i;
                i++;
            }

            if (n == end - beg) {
                while (j < n && beg < end && list.charAt(beg) == name.charAt(j)) {
                    j++;
                    beg++;
                }

                if (j == n) {
                    return false;
                }
            }
        }

        return true;
    }
}
