import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 07:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    // 0~9的3×5显示样式
    private final static char[][][] D = {{
            {' ', '-', ' '},// 0
            {'|', ' ', '|'},
            {' ', ' ', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}}, {

            {' ', ' ', ' '},// 1
            {' ', ' ', '|'},
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}}, {

            {' ', '-', ' '},// 2
            {' ', ' ', '|'},
            {' ', '-', ' '},
            {'|', ' ', ' '},
            {' ', '-', ' '}}, {

            {' ', '-', ' '},// 3
            {' ', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}}, {

            {' ', ' ', ' '}, // 4
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}}, {

            {' ', '-', ' '},// 5
            {'|', ' ', ' '},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}}, {

            {' ', '-', ' '},// 6
            {'|', ' ', ' '},
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}}, {

            {' ', '-', ' '}, // 7
            {' ', ' ', '|'},
            {' ', ' ', ' '},
            {' ', ' ', '|'},
            {' ', ' ', ' '}}, {

            {' ', '-', ' '},// 8
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {'|', ' ', '|'},
            {' ', '-', ' '}}, {

            {' ', '-', ' '},// 9
            {'|', ' ', '|'},
            {' ', '-', ' '},
            {' ', ' ', '|'},
            {' ', '-', ' '}}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int s = scanner.nextInt();
            String n = scanner.next();
            char[][] rst = show(s, n);
            for (char[] chs : rst) {
                System.out.println(new String(chs));
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * 显示数字字符
     *
     * @param s 一笔画的长度和宽度
     * @param n 要显示的数字字符串
     * @return 显示结果
     */
    private static char[][] show(int s, String n) {

        // 计算输出区域的高度与宽度
        // 高度
        int height = s * 2 + 3;
        // 单个数字占据的宽度
        int numWidth = s + 2;
        //整个输出区域的宽度
        int width = n.length() * numWidth + n.length() - 1;
        char[][] chars = new char[height][width];

        // 一行一行打印
        for (int row = 0; row < height; row++) {
            // 第row行，第col个
            for (int col = 0; col < width; col++) {
                // 计算idx确定输出哪个数字了。idx表示输出的序号
                int idx = col / (numWidth + 1);
                // 序号代表的输出值
                // 转换为对应的数组的编号，也是数字的值。
                int d = n.charAt(idx) - '0';


                //   行映射规则: 将第i行映射到D对应行的位置
                //   0映射到0行
                //   1 ~ s 映射到 第1行
                //   s + 1 映射到 第2行
                //   (s + + + 1) ~ ((s + 1) + s) 映射到第3行
                //   ((s + 1) + (s + 1)) 映射到第 4行
                //
                //   列映射规则:
                //   第0列映射到第零列，
                //   第1到s列映射到第一列，
                //   第s+1列映射到第二列


                // x表示映射的行
                int x = 0;
                // y表示映射到的列
                int y = 0;


                // 计算列号，就是不映射时的列号
                int yMap = col % (numWidth + 1);
                // 除了最后一个字符，其它的字符每一行打印完还要打印一个空格
                // 属于空格列
                if (yMap == numWidth) {
                    // 打印空格，因为D[?][0][0]总是空格
                    x = 0;
                    y = 0;
                }
                // 属于字符内的列
                else {

                    // 映射列
                    if (yMap == 0) {
                        y = 0;
                    } else if (yMap >= 1 && yMap <= s) {
                        y = 1;
                    } else if (yMap == s + 1) {
                        y = 2;
                    }

                    // 映射行
                    if (row == 0) {
                        x = 0;
                    } else if (row >= 1 && row <= s) {
                        x = 1;
                    } else if (row == s + 1) {
                        x = 2;
                    } else if (row >= s + 2 && row <= s * 2 + 1) {
                        x = 3;
                    } else if (row == s * 2 + 2) {
                        x = 4;
                    }
                }

                chars[row][col] = D[d][x][y];
            }
        }

        return chars;
    }
}
