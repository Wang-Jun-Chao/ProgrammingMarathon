import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-13 10:32
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            List<String> visit = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                String s = scanner.next();
                visit.add(s);
                // 如果是访问页面就再读取访问的页面RUL
                if ("VISIT".equals(s)) {
                    visit.add(scanner.next());
                }
            }

            List<String> result = history(visit);
            for (String url : result) {
                System.out.println(url);
            }
            System.out.println();

        }

        scanner.close();
    }

    /**
     * 记录浏览历史
     *
     * @param visit 访问序列，为VISIT、BACK和FORWARD。如果是VISIT后面会一个就是直接访问的RUL
     * @return 访问的页面URL
     */
    private static List<String> history(List<String> visit) {
        List<String> result = new ArrayList<>();

        // 记录访问的历史页面
        List<String> history = new ArrayList<>(visit.size());
        // 记录上一个访问的页面位置
        int prev = -1;

        for (int i = 0, j = visit.size(); i < j; i++) {
            String act = visit.get(i);
            if ("VISIT".equals(act)) {

                // 取URL
                i++;
                String url = visit.get(i);
                // 移除上一个页面的所有FROWARD页面
                while (history.size() > prev + 1) {
                    history.remove(history.size() - 1);
                }
                prev++;
                history.add(url);

                result.add(history.get(prev));

            } else if ("BACK".equals(act)) {

                if (prev <= 0) {
                    result.add("ignore");
                } else {
                    prev--;
                    result.add(history.get(prev));
                }
            } else if ("FORWARD".equals(act)) {

                if (prev + 1 >= history.size()) {
                    result.add("ignore");
                } else {
                    prev++;
                    result.add(history.get(prev));
                }
            }
        }

        return result;
    }


}
