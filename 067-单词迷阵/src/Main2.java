/**
 * Author: 王俊超
 * Time: 2016-05-18 08:44
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static final int SIZE = 10;
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> check = new ArrayList<>();

    public static void main(String[] args) {

//        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("data3.txt"));
        while (sc.hasNext()) {
            list.clear();
            check.clear();
            for (int i = 0; i < SIZE; i++)
                list.add(sc.next());
            int nums = Integer.parseInt(sc.next()); // 要匹配的单词个数
            for (int i = 0; i < nums; i++)
                check.add(sc.next());
            getResult(nums);
        }
    }

    private static void getResult(int nums) {
        // 初始化行列值
        ArrayList<String> value = new ArrayList<String>(list);
        StringBuilder sb = new StringBuilder();
        // 将列添加进去
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sb.append(list.get(x).charAt(y));
            }
            value.add(sb.toString());
            sb.setLength(0);
        }
        // 接下来就需要取值做判断了
        for (int i = 0; i < check.size(); i++) {
            String regex = check.get(i);
            if (isFind(value, regex, sb.append(regex).reverse().toString())) System.out
                    .println("Yes");
            else System.out.println("No");
            sb.setLength(0);
        }
        //System.out.println();
    }

    private static boolean isFind(ArrayList<String> valueList, String regex, String reverRegex) {

        for (int i = 0; i < valueList.size(); i++) {
            String value = valueList.get(i);
            if (value.contains(regex) || value.contains(reverRegex))
                return true;

        }
        return false;
    }
}
