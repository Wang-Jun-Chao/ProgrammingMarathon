import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-19 10:44
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        while (scanner.hasNextInt()) {
            getArrayAndPrint(scanner.nextInt());
        }
    }

    private static void getArrayAndPrint(int num) {
        int[][] array = new int[num][num];
        int row = 0;
        int col = 0;
        int down = num - 1;
        int top = 0;
        int right = num - 1;
        int left = 0;
        int value = 2;
        array[0][0] = 1;
        num = num * num;
        while (value <= num) {
            // 从00开始，先往左找找到头了就转向下到头就转向右到头就转向上
            // 从右往左，行不变，列递增
            for (col = col + 1; col <= right && array[row][col] == 0; ) {
                array[row][col] = value++;
                if (col == right) {
                    top = row + 1;
                    break;
                }
                col++;
            }
            // 从上往下，列不变，行递增
            for (row = row + 1; row <= down && array[row][col] == 0; ) {
                array[row][col] = value++;
                if (row == down) {
                    right = col - 1;
                    break;
                }
                row++;
            }
            // 从右往左，行不变，列递减
            for (col = col - 1; col >= left && array[row][col] == 0; ) {
                array[row][col] = value++;
                if (col == left) {
                    down = row - 1;
                    break;
                }
                col--;
            }
            // 从下往上，列不变，行递减
            for (row = row - 1; row >= top && array[row][col] == 0; ) {
                array[row][col] = value++;
                if (row == top) {
                    left = col + 1;
                    break;
                }
                row--;
            }
        }
        printf(array);
    }

    private static void printf(int[][] array) {
        for (int[] row : array) {
            for (int j = 0; j < array.length; j++) {
                if (j != array.length - 1) {
                    System.out.print(row[j] + " ");
                } else {
                    System.out.print(row[j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
