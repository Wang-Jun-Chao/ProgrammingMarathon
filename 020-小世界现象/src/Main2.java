import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-12 22:11
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            int[][] graph = new int[n][n];
            int[][] distance = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = sc.nextInt();
                    if (graph[i][j] == 0) distance[i][j] = Integer.MAX_VALUE / 100;
                    else distance[i][j] = 0;
                }
            }
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    for (int k = 0; k < n; ++k)
                        distance[j][k] = distance[j][k] > distance[j][i] + distance[i][k] + 1 ? distance[j][i] + distance[i][k] + 1 : distance[j][k];
            int num = sc.nextInt();
            for (int i = 0; i < num; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                if (distance[x - 1][y - 1] >= Integer.MAX_VALUE / 100) System.out.println("Sorry");
                else System.out.println(distance[x - 1][y - 1]);
            }
            t--;
        }
    }
}
