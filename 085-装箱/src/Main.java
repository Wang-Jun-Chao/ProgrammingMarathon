import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 08:33
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (scanner.hasNext()) {
            int[] num = new int[6];
            for (int i = 0; i < 6; i++) {
                num[i] = scanner.nextInt();
            }

            System.out.println(needMinBox(num));
        }

        scanner.close();
    }

    /**
     * 需要最少的箱子数目
     *
     * @param num num[i]表示长宽都是(i+1)的箱子数目
     * @return 最少的箱子数目
     */
    private static int needMinBox(int[] num) {

        // 按可以组成x*x最大的计算
        // left[0]：剩余2*2的格子数
        // left[1]：剩余1*1的格子数
        // left[2]：只为计算使用
        int[] left = new int[3];
        // n[0]：6*6的包裹装了4*4的长方体后，剩下的2*2的数目
        // n[1]：6*6的包裹装了5*5的长方体后，剩下的1*1的数目
        // n[2]：6*6的包裹装了6*6的长方体后，没有剩下的，只为计算使用
        int[] n = {5, 11, 0};

        int min = 0;

        // 处理4*4,5*5,6*6
        for (int i = 3; i < num.length; i++) {
            if (num[i] != 0) {
                min += num[i];
                // 余下不同种类的格子数
                left[i - 3] = n[i - 3] * num[i];
            }
        }

        // 处理3*3
        min += num[2] / 4;
        int r = num[2] % 4;

        if (r > 0) {
            min++;
            // 还还要占用r个3*3，剩下(4-r)个3*3要被拆分成2*2和1*1
            switch (4 - r) {
                case 1:
                    left[0] += 1;
                    left[1] += 5;
                    break;
                case 2:
                    left[0] += 3;
                    left[1] += 6;
                    break;
                case 3:
                    left[0] += 5;
                    left[1] += 7;
                    break;
            }
        }

        // 剩下的2*2足够分配
        if (num[1] <= left[0]) {
            left[0] -= num[1];
            // 将余下的2*2分配成1*1
            left[1] += left[0] * 4;
            left[0] = 0;
        }
        // 剩下的2*2不够分配
        else {
            num[1] -= left[0];
            left[0] = 0;

            min += num[1] / 9;
            r = num[1] % 9;

            // 还有剩下的
            if (r > 0) {
                min++;
                // 将剩下的转换成1*1
                left[1] += (9 - r) * 4;
            }

        }

        // 处理1*1，剩下的1*1不够放
        if (num[0] > left[1]) {
            num[0] -= left[1];

            min += num[0] / 36;
            r = num[0] % 36;
            if (r > 0) {
                min++;
            }
        }

        return min;
    }
}
