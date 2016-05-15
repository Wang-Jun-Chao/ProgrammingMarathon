import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author: 王俊超
 * Time: 2016-05-15 09-26
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main {

    /**
     * 图书类
     */
    private static class Book {
        // 图书名
        private String name;
        // 图书种类
        private String type;

        private int compare(Book other) {
            if (other == null) {
                return -1;
            }

            // 忽略大小写比较
            int v = this.type.compareToIgnoreCase(other.type);
            if (v == 0) {
                return this.name.compareToIgnoreCase(other.name);
            } else {
                return v;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data2.txt"));


        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            Book[] books = new Book[n];

            for (int i = 0; i < n; i++) {
                books[i] = new Book();
                books[i].name = scanner.next();
                books[i].type = scanner.next();
            }
            List<String> tmp = arrangeBook(books);

            for (String s : tmp) {
                System.out.println(s);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static List<String> arrangeBook(Book[] books) {

        quickSort(books, 0, books.length - 1);

        List<String> rst = new ArrayList<>();

        for (Book b : books) {
            rst.add(b.name);
        }

        return rst;
    }


    /**
     * 快速排序
     *
     * @param books 书本数组
     * @param left  第一个待排序数组的下标
     * @param right 最后一个待排序数组的下标
     */
    private static void quickSort(Book[] books, int left, int right) {
        if (left < right) {
            Book pivot = books[left];
            int lo = left;
            int hi = right;

            while (lo < hi) {
                while (lo < hi && books[hi].compare(pivot) >= 0) {
                    hi--;
                }
                books[lo] = books[hi];

                while (lo < hi && books[lo].compare(pivot) <= 0) {
                    lo++;
                }
                books[hi] = books[lo];
            }

            books[lo] = pivot;
            quickSort(books, left, lo - 1);
            quickSort(books, lo + 1, right);
        }
    }
}
