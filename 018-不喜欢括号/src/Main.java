import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-12 08:41
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            String[] suffix = new String[num];

            for (int i = 0; i < num; i++) {
                suffix[i] = scanner.next();
            }

            System.out.println(calculate(suffix));
        }
        scanner.close();
    }

    /**
     * 计算前缀式
     *
     * @param suffix 前缀式
     * @return 结果
     */
    private static int calculate(String[] suffix) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = suffix.length - 1; i >= 0; i--) {

            char c = suffix[i].charAt(0);
            // 如果是操作符
            if (suffix[i].length() == 1 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                int a = stack.removeFirst();
                int b = stack.removeFirst();
                stack.addFirst(calculate(a, b, c));
            }
            // 操作数就入栈
            else {
                stack.addFirst(Integer.parseInt(suffix[i]));
            }
        }

        return stack.removeFirst();
    }

    /**
     * 计算acb
     *
     * @param a 操作数
     * @param b 操作数
     * @param c 操作符
     * @return 结果
     */
    private static int calculate(int a, int b, char c) {

        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                // do nothing
        }

        throw new IllegalArgumentException("操作符只能是(+-*/):" + c);
    }
}
