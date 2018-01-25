package dataStructure.sort;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/25.
 */
public class QuickSort {

    private static <T extends Comparable<T>> boolean less(T i, T j) {
        return i.compareTo(j) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T extends Comparable<T>> void sort(T[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }


    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable x = arr[lo];
        while (true) {
            while (less(arr[++i], x)) if (i == hi) break;
            while (less(x, arr[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }


    public static void main(String... args) {

        // 测试sort方法
        int k = (int) (Math.random() * 100);
        Integer[] a = new Integer[k];
        for (int i = 0; i < k; i++) a[i] = (int) (Math.random() * 100);
        System.out.println();
        System.out.print(" sort前 " + Arrays.asList(a));
        System.out.print(" isSorted " + isSorted(a));
        sort(a);
        System.out.println();
        System.out.print(" sort后 " + Arrays.asList(a));
        System.out.print(" isSorted " + isSorted(a));
        System.out.println();

    }

    public static boolean isSorted(Comparable[] arr) {
        if (arr.length < 2) {
            return true;
        }
        boolean boo = arr[0].compareTo(arr[1]) > 0;
        for (int i = 1; i < arr.length; i++) {
            if (boo != (arr[i - 1].compareTo(arr[i]) > 0)) return false;
        }
        return true;
    }
}
