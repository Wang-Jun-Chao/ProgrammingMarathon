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
    }

    /**
     * 排序枚举对象
     */
    private static enum Sort {
        // 按图书名排序
        NAME,
        // 按图书类型排序
        TYPE
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            Book[] books = new Book[n];

            for (int i = 0; i < n; i++) {
                books[i] = new Book();
                books[i].name = scanner.next();
                books[i].type = scanner.next();
            }
            List<String> rst = arrangeBook(books);

            for (String s : rst) {
                System.out.println(s);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static List<String> arrangeBook(Book[] books) {

        sort(books, Sort.TYPE);
        sort(books, Sort.NAME);

        List<String> rst = new ArrayList<>();

        for (Book b : books) {
            rst.add(b.name);
        }

        return rst;
    }

    /**
     * 按排序类型对书本进行排序
     *
     * @param books 书本数组
     * @param type  排序类型
     */
    private static void sort(Book[] books, Sort type) {
        quickSort(books, 0, books.length - 1, type);
    }

    /**
     * 快速排序
     *
     * @param books 书本数组
     * @param left  第一个待排序数组的下标
     * @param right 最后一个待排序数组的下标
     * @param type  排序类型
     */
    private static void quickSort(Book[] books, int left, int right, Sort type) {
        if (left < right) {
            Book pivot = books[left];
            int lo = left;
            int hi = right;

            while (lo < hi) {
                while (lo < hi && compare(books[hi], pivot, type) >= 0) {
                    hi--;
                }

                books[lo] = books[hi];
                while (lo < hi && compare(books[lo], pivot, type) <= 0) {
                    lo++;
                }

                books[hi] = books[lo];
            }

            books[lo] = pivot;
            quickSort(books, left, lo - 1, type);
            quickSort(books, lo + 1, right, type);
        }
    }

    /**
     * 比较方法
     *
     * @param x    图书
     * @param y    图书
     * @param type 排序类型
     * @return 比较，x比y大，返回-1，相等返回0，x比y小，返回1
     */
    private static int compare(Book x, Book y, Sort type) {
        switch (type) {
            case NAME:
                return y.name.compareTo(x.name);
            case TYPE:
                return y.type.compareTo(x.type);
        }

        return -1;
    }

}
