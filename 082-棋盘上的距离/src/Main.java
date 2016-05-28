import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-23 08:09
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            String source = scanner.next();
            String target = scanner.next();

            List<String> rst = reach(source, target);
            for (String s : rst) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * 根据起始和终点坐标，分别求王、后、车和象要走多少步才可以到达目标位置
     *
     * @param source 起始坐标
     * @param target 结点坐标
     * @return 各个坐标
     */
    private static List<String> reach(String source, String target) {
        List<String> rst = new ArrayList<>(4);

        char x1 = source.charAt(0);
        char y1 = source.charAt(1);
        char x2 = target.charAt(0);
        char y2 = target.charAt(1);

        int diffX = Math.abs(x1 - x2);
        int diffY = Math.abs(y1 - y2);

        // 王的走法
        // 先走 m = min{diffX, diffY} 步直线，再走 n = abs{diffX - diffY} 步斜线
        // 一共走了 m + n -> max{diffX, diffY}
        rst.add(Math.max(diffX, diffY) + "");

        // 后的走法
        if (x1 == x2 || y1 == y2 || diffX == diffY) {
            rst.add("1");
        } else {
            rst.add("2");
        }

        // 车的走法
        if (x1 == x2 || y1 == y2) {
            rst.add("1");
        } else {
            rst.add("2");
        }

        // 象的走法
        // 最多只要折一次
        if (diffX == diffY) {
            rst.add("1");
        }

        // 要折一次
        else if (Math.abs(diffX - diffY) % 2 == 0) {
            rst.add("2");
        } else {
            rst.add("Inf");
        }


        return rst;
    }

}
