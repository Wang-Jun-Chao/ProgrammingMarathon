import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-25 10:38
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = sc.nextInt();
            }


            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (arr.isEmpty()) {
                    arr.add(input[i]);
                }
                if (arr.get(arr.size() - 1) < input[i]) {
                    arr.add(input[i]);
                } else {
                    arr.set(Bin(input[i], arr), input[i]);
                }
            }
            System.out.println(arr.size());
        }
    }

    static int Bin(int k, ArrayList<Integer> arr) {
        int lo = 0;
        int hi = arr.size();
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr.get(mid) == k) {
                return mid;
            } else {
                if (arr.get(mid) > k) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }
        return lo;
    }
}
