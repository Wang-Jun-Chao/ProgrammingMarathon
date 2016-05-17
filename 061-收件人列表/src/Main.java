import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-17 18:57
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            String[] name = new String[n];

            for (int i = 0; i < n; i++) {
                name[i] = scanner.nextLine();
            }

            System.out.println(toNameList(name));
        }

        scanner.close();
    }

    /**
     * 根据名称生成收件人列表
     *
     * @param name 名字数组
     * @return 收件人列表
     */
    private static String toNameList(String[] name) {
        StringBuilder b = new StringBuilder(256);
        boolean add = false;

        for (String s : name) {
            add = false;
            for (int i = 0, j = s.length(); i < j; i++) {
                if (s.charAt(i) == ' ' || s.charAt(i) == ',') {
                    b.append(", \"").append(s).append("\"");
                    add = true;
                    // 跳出内层循环
                    break;
                }
            }

            if (!add) {
                b.append(", ").append(s);
            }

        }

        return b.substring(2);
    }


}
