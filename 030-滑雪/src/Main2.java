import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-14 11:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static int h[][] = new int[101][101];
    public static int m[][] = new int[101][101];
    public static int r, c;


    private static int GetHigh(int i, int j) {
        if (m[i][j] != -1)
            return m[i][j];
        else {
            int max = 0;
            int temp;
            if (j > 1) {
                if (h[i][j] > h[i][j - 1]) {
                    temp = GetHigh(i, j - 1);
                    if (max < temp)
                        max = temp;
                }
            }
            if (j < c) {
                if (h[i][j] > h[i][j + 1]) {
                    temp = GetHigh(i, j + 1);
                    if (max < temp)
                        max = temp;
                }
            }
            if (i > 1) {
                if (h[i][j] > h[i - 1][j]) {
                    temp = GetHigh(i - 1, j);
                    if (max < temp)
                        max = temp;
                }
            }
            if (i < r) {
                if (h[i][j] > h[i + 1][j]) {
                    temp = GetHigh(i + 1, j);
                    if (max < temp)
                        max = temp;
                }
            }
            m[i][j] = max + 1;//填充备忘录
            return m[i][j];
        }
    }

    public static int getMaxHeight() {
        int temp;
        int Max = -1;
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                temp = GetHigh(i, j);
                if (Max < temp)
                    Max = temp;
            }
        }
        return Max;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            r = sc.nextInt();
            c = sc.nextInt();
            for (int i = 1; i <= r; i++)
                for (int j = 1; j <= c; j++)
                    h[i][j] = sc.nextInt();
            for (int i = 1; i <= r; i++)
                for (int j = 1; j <= c; j++)
                    m[i][j] = -1;

            System.out.println(getMaxHeight());
        }

    }
}
