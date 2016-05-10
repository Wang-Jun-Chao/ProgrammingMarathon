import java.util.*;

/**
 * Author: 王俊超
 * Time: 2016-05-10 22:01
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int row = scanner.nextInt();
            Map<String, Integer> wordMap = new HashMap<>();
            Map<String, List<String>> listMap = new HashMap<>();

            for (int i = 0; i < row; i++) {
                String a = scanner.next();
                String b = scanner.next();

                if (!wordMap.containsKey(a)) {
                    wordMap.put(a, 1);
                }
                if (!wordMap.containsKey(b)) {
                    wordMap.put(b, 1);
                }

                List<String> list;
                if (listMap.get(a) == null) {
                    list = new ArrayList<>();
                } else {
                    list = listMap.get(a);
                }

                list.add(b);
                listMap.put(a, list);
                visitAll(a, b, listMap, wordMap);
            }

            int max = 0;
            for (Integer val : wordMap.values()) {
                if (val > max) {
                    max = val;
                }
            }
            System.out.println(max);
        }

    }

    private static void visitAll(String a, String b, Map<String, List<String>> mapList, Map<String, Integer> wordMap) {
        int val = wordMap.get(b);
        int t = wordMap.get(a) + 1;
        if (val < t) {
            val = t;
            wordMap.remove(b);
            wordMap.put(b, val);
        } else {
            return;
        }

        List<String> list = mapList.get(b);
        if (list == null) {
            return;
        }

        for (String s : list) {
            visitAll(b, s, mapList, wordMap);
        }
    }
}
