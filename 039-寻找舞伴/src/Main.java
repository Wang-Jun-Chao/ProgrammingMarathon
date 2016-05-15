import java.util.Arrays;
import java.util.Scanner;


/**
 * Author: 王俊超
 * Time: 2016-05-15 08:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();

            int[] boy = new int[m];
            int[] girl = new int[n];

            for (int i = 0; i < m; i++) {
                boy[i] = scanner.nextInt();
            }

            for (int i = 0; i < n; i++) {
                girl[i] = scanner.nextInt();
            }

            System.out.println(match(boy, girl));
        }

        scanner.close();
    }

    /**
     * 求舞伴的对数
     *
     * @param boy  男生身高数组
     * @param girl 女生身高数组
     * @return 舞伴的对数
     */
    private static int match(int[] boy, int[] girl) {

        // 排序
        Arrays.sort(boy);
        Arrays.sort(girl);

        // 记录舞伴的对数
        int rst = 0;
        // 当前等待匹配的男生数组下标
        int i = 0;
        // 当前等待匹配的女生数组下标
        int j = 0;

        // 对男生女生进行处理，直到男生或者女生都处理完
        while (i < boy.length && j < girl.length) {
            // 身高相等，对数加一，处理下一对
            if (boy[i] == girl[j]) {
                rst++;
                i++;
                j++;
            }
            // 女生矮，选择下一个女生
            else if (boy[i] - girl[j] > 0) {
                j++;
            }
            // 男生矮，选择下一个男生
            else {
                i++;
            }
        }

        return rst;
    }
}
