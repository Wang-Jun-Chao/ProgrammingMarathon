import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-18 08:06
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    // 矩阵大小
    private final static int N = 10;
    // 移动的四个方向，两个一组，上右下左
    private final static int[] D = {-1, 0, 0, 1, 1, 0, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {

            char[][] maze = new char[N][N];
            for (int i = 0; i < N; i++) {
                maze[i] = scanner.next().toCharArray();
            }

            int n = scanner.nextInt();
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = scanner.next();
            }

//
//            for (char[] cs : maze) {
//                System.out.println(Arrays.toString(cs));
//            }
//            System.out.println();
//            for (String s : words) {
//                System.out.println(s);
//            }
//            System.out.println();

            boolean[] match = findWord(maze, words);

            for (boolean b : match) {
                if (b) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
//            System.out.println();

        }

        scanner.close();
    }

    private static boolean[] findWord(char[][] maze, String[] words) {
        boolean[] rst = new boolean[words.length];
        int len = words.length;
        for (int i = 0; i < len; i++) {
            char c = words[i].charAt(0);
            for (int j = 0; !rst[i] && j < N; j++) {
                for (int k = 0; !rst[i] && k < N; k++) {
                    if (maze[j][k] == c) {
                        rst[i] = findWord2(maze, j, k, 0, words[i]);
                    }
                }
            }
        }

        return rst;
    }

    /**
     * 【方法一】
     * 在字母矩阵中找chars表示的字符数组
     *
     * @param maze 字母矩阵
     * @param x    开始查找的行号
     * @param y    开始查找的列号
     * @param i    当前处理字符下标
     * @param s    字符数串
     * @return true：找到目标字符串，false：没有找到
     */
    private static boolean findWord(char[][] maze, int x, int y, int i, String s) {

        int len = s.length();
        // 从左向右找
        if (N - y >= len) {
            int j;
            int k;
            for (j = 0, k = y; j < len; j++, k++) {
                // 有不相等的就退出循环
                if (s.charAt(j) != maze[x][k]) {
                    break;
                }
            }

            // 全部都匹配返回true
            if (j == len) {
                return true;
            }
        }

        // 从右向左找
        if (y + 1 >= len) {
            int j;
            int k;
            for (j = 0, k = y; j < len; j++, k--) {
                // 有不相等的就退出循环
                if (s.charAt(j) != maze[x][k]) {
                    break;
                }
            }

            // 全部都匹配返回true
            if (j == len) {
                return true;
            }
        }

        // 从下向上找
        if (x + 1 >= len) {
            int j;
            int k;
            for (j = 0, k = x; j < len; j++, k--) {
                // 有不相等的就退出循环
                if (s.charAt(j) != maze[k][y]) {
                    break;
                }
            }

            // 全部都匹配返回true
            if (j == len) {
                return true;
            }
        }


        // 从上向下找
        if (N - x >= len) {
            int j;
            int k;
            for (j = 0, k = x; j < len; j++, k++) {
                // 有不相等的就退出循环
                if (s.charAt(j) != maze[k][y]) {
                    break;
                }
            }

            // 全部都匹配返回true
            if (j == len) {
                return true;
            }
        }

        return false;
    }

    /**
     * 【方法二】
     * 在字母矩阵中找chars表示的字符数组，对findWord方法中的上下左右进行改进
     *
     * @param maze 字母矩阵
     * @param x    开始查找的行号
     * @param y    开始查找的列号
     * @param i    当前处理字符下标
     * @param s    字符数串
     * @return true：找到目标字符串，false：没有找到
     */
    private static boolean findWord2(char[][] maze, int x, int y, int i, String s) {

        int len = s.length();


        for (int di = 0; di < D.length; di += 2) {
            int j;
            int xx;
            int yy;
            for (j = 0, xx = x, yy = y; xx >= 0 && xx < N && yy >= 0 && yy < N && j < len; j++, xx += D[di], yy += D[di + 1]) {
                // 有不相等的就退出循环
                if (s.charAt(j) != maze[xx][yy]) {
                    break;
                }
            }

            // 全部都匹配返回true
            if (j == len) {
                return true;
            }
        }

        return false;
    }
}
