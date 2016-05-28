import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-28 20:22
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    private static class Lender {
        private String name;
        private int val;

        Lender(String name, int val) {
            this.name = name;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int money = scanner.nextInt();
            List<Lender> lenders = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                Lender lender = new Lender(scanner.next(), scanner.nextInt());
                lenders.add(lender);
            }

            List<String> names = lend(lenders, money);
            for (String s : names) {
                System.out.println(s);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static List<String> lend(List<Lender> lenders, int money) {
        List<String> names = new ArrayList<>();

        int t = money;
        while (!lenders.isEmpty()) {

            for (int i = 0, j = lenders.size(); i < j; i++, j = lenders.size()) {
                Lender lender = lenders.get(i);
                if (lender.val <= money) {
                    names.add(lender.name);
                    money -= lender.val;
                    lenders.remove(i);
                    // lenders删除元素，后面的元素会向前挪，所以i要保持不变,所以需要减一操作
                    i--;
                }
            }

            money = t;
        }

        return names;
    }


}
