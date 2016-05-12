import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-12 07:44
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
     * 计算逆波兰式
     *
     * @param suffix 逆波兰式
     * @return 结果
     */
    private static int calculate(String[] suffix) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (String s : suffix) {
            char c = s.charAt(0);
            // 如果是操作符
            if (s.length() == 1 && (c == '+' || c == '-' || c == '*' || c == '/')) {
                int b = stack.removeFirst();
                int a = stack.removeFirst();
                stack.addFirst(calculate(a, b, c));
            }
            // 操作数就入栈
            else {
                stack.addFirst(Integer.parseInt(s));
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
