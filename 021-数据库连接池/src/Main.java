import java.util.*;

/**
 * Author: 王俊超
 * Time: 2016-05-13 09:57
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
            n *= 2;
            List<String> request = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                request.add(scanner.next());
            }
            System.out.println(maxConnection(request));
        }

        scanner.close();
    }

    /**
     * 求最大的连接数
     *
     * @param request 请求操作，两个一组(a, b)，a表示连接名称，b表操作内容，connect或者disconnect
     * @return 最大的连接数
     */
    private static int maxConnection(List<String> request) {

        // 记录最大的连接数
        int max = 0;
        // 保存连接数
        Set<String> conn = new HashSet<>(request.size() / 2);

        // 处理每一个连接请求
        for (int i = 0, j = request.size(); i < j; i += 2) {
            String r = request.get(i);
            String o = request.get(i + 1);

            // 如果是连接操作就添加到连接池中
            if ("connect".equals(o)) {
                conn.add(r);

                // 当前连接池中的大小比之前记录到的最大值还要大，就更新最大值
                if (conn.size() > max) {
                    max = conn.size();
                }
            }
            // 删除连接
            else if ("disconnect".equals(o)) {
                conn.remove(r);
            }
        }

        return max;
    }
}
