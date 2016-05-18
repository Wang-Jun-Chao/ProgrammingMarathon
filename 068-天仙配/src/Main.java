import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-18 09:27
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt() * 2;
            if (n == 0) {
                break;
            }

            int[] num = new int[n];
            for (int i = 0; i < n; i++) {
                num[i] = scanner.nextInt();
            }

            System.out.println(count2(num));
        }

        scanner.close();
    }

    /**
     * 统计天仙配的方法数
     *
     * @param num 地面数字
     * @return 天仙配的方法数
     */
    private static int count(int[] num) {

        int len = num.length;
        // 男生所站位置的数字和
        int[] man = new int[len];
        // 女生所站位置的数字和
        int[] woman = new int[len];
        int u = 0;
        int v = 0;
        for (int i = 0; i < len; i++) {
            u += num[i];
            man[i] = u;
            v += num[len - i - 1];
            woman[len - i - 1] = v;
        }

        int count = 0;
        int i = 0;
        int j = len - 1;
        int ni;
        int nj;

        while (i < len && j >= 0) {

            if (man[i] == woman[j]) {
                ni = i + 1;
                // 找第一个与man[i]值不同的数字
                while (ni < len && man[i] == man[ni]) {
                    ni++;
                }

                nj = j - 1;
                // 找第一个与woman[j]值不同的数字
                while (nj >= 0 && woman[j] == woman[nj]) {
                    nj--;
                }

                count += (ni - i) * (j - nj);

                ni--;
                nj++;

                // 还要除去[i, ni]和[nj, j]重合区间的个数
                // i-------------------------ni
                //     ^-----------------^
                //     nj                j
                if (i <= nj && j <= ni) {
                    count -= (j - nj + 1);
                }
                // i-------------------------ni
                //               ^-----------------^
                //               nj                j
                else if (i <= nj && nj <= ni) {
                    count -= (ni - nj + 1);
                }
                //          i------------------ni
                // ^-----------------^
                // nj                j
                else if (nj <= i && i <= j) {
                    count -= (j - i + 1);
                }

                i = ni + 1;
                j = nj - 1;
            } else if (man[i] > woman[j]) {
                j--;
            } else {
                i++;
            }
        }

        return count;
    }

    /**
     * 统计天仙配的方法数
     *
     * @param num 地面数字
     * @return 天仙配的方法数
     */
    private static int count2(int[] num) {

        //////////////////////////////////////////////
        // 省去一个辅助数组
        //////////////////////////////////////////////

        int len = num.length;
        // 女生所站位置的数字和
        int[] woman = new int[len];
        int u = 0;
        int v = 0;
        for (int i = 0; i < len; i++) {
            v += num[len - i - 1];
            woman[len - i - 1] = v;
        }

        // 男生所站位置的数字和
        for (int i = 1; i < len; i++) {
            num[i] += num[i - 1];
        }

        int count = 0;
        int i = 0;
        int j = len - 1;
        int ni;
        int nj;

        while (i < len && j >= 0) {

            if (num[i] == woman[j]) {
                ni = i + 1;
                // 找第一个与man[i]值不同的数字
                while (ni < len && num[i] == num[ni]) {
                    ni++;
                }

                nj = j - 1;
                // 找第一个与woman[j]值不同的数字
                while (nj >= 0 && woman[j] == woman[nj]) {
                    nj--;
                }

                count += (ni - i) * (j - nj);

                ni--;
                nj++;

                // 还要除去[i, ni]和[nj, j]重合区间的个数
                // i-------------------------ni
                //     ^-----------------^
                //     nj                j
                if (i <= nj && j <= ni) {
                    count -= (j - nj + 1);
                }
                // i-------------------------ni
                //               ^-----------------^
                //               nj                j
                else if (i <= nj && nj <= ni) {
                    count -= (ni - nj + 1);
                }
                //          i------------------ni
                // ^-----------------^
                // nj                j
                else if (nj <= i && i <= j) {
                    count -= (j - i + 1);
                }

                i = ni + 1;
                j = nj - 1;
            } else if (num[i] > woman[j]) {
                j--;
            } else {
                i++;
            }
        }

        return count;
    }
}
