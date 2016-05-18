import java.util.HashMap;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-18 11:19
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            HashMap<Integer, Integer> rowMax = new HashMap<>();
            HashMap<Integer, Integer> colMin = new HashMap<>();
            // System.out.println("row = " + row + " , col = " + col);
            int[][] array = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int num = scanner.nextInt();
                    array[i][j] = num;
                    if (rowMax.get(i) == null) {
                        rowMax.put(i, num);
                    } else if (rowMax.get(i) < num) {
                        rowMax.put(i, num);
                    }

                    if (colMin.get(j) == null) {
                        colMin.put(j, num);
                    } else if (colMin.get(j) > num) {
                        colMin.put(j, num);
                    }
                }
            }

            // 接下来需要做的就是遍历数组，然后寻找鞍点
            boolean flag = false;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (array[i][j] == rowMax.get(i) && array[i][j] == colMin.get(j)) {
                        System.out.println((i + 1) + " " + (j + 1));
                        flag = true;
                    }
                }
            }
            if (!flag) System.out.println("No Point");
            System.out.println();
        }
    }
}
