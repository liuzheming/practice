package dataStructure.sort;

/**
 * Description:堆排序
 * <p>
 * Created by lzm on 2018/1/18.
 */
public class JHeapSort {

    private static void exchange(int i, int j, int[] arr) {
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

    private static boolean less(int i, int j, int[] arr) {
        return arr[i] <= arr[j];
    }

//    /**
//     * 上浮,当加入新节点的时候,如果新节点的位置较大,则上浮至一个
//     * 大于此节点的父节点之下
//     *
//     * @param arr  完全二叉堆
//     * @param size 二叉堆的元素个数
//     */
//    private static void swim(int[] arr, int size) {
//        int idx = size;
//        // 如果子节点大于它的父节点,则交换
//        while (idx > 1 && less(idx / 2, idx, arr)) {
//            exchange(idx, idx / 2, arr);
//            idx = idx / 2;
//        }
//    }

    /**
     * 对索引为n的元素所在的子树进行下沉操作
     *
     * @param idx  子树的树根
     * @param arr  完全二叉堆
     * @param size 二叉堆元素的个数
     */
    private static void sink(int idx, int[] arr, int size) {
        while (2 * idx <= size) {
            // 找到较大的子节点所在的位置
            int j = 2 * idx;
            if (j < size && less(j, j + 1, arr)) j++;
            // 如果较大的子节点仍然小于父节点,则停止下沉
            if (less(j, idx, arr)) break;
            // 如果较大的子节点小于父节点,则执行交换操作
            exchange(idx, j, arr);
            // 索引指向下一级子树的根节点,循环继续进行下一轮比较
            idx = j;
        }
    }


    /**
     * 堆排序
     *
     * @param arr 输入数组
     */
    private static void sort(int[] arr) {
        int size = arr.length - 1;
        // 先进行下沉得到堆有序的数组
        for (int n = size / 2; n >= 1; n--)
            sink(n, arr, size);
        // 先把数组第一个(即堆内最大的元素)和数组末尾元素互换,然后忽略此元素(即size--)
        // 对剩余的数组进行下沉操作数组进行下沉排序
        while (size >= 0) {
            exchange(1, size--, arr);
            sink(1, arr, size);
        }
    }


    public static void main(String... args) {

        int[] arr = new int[]{
                -1, 10, 28, 5, 3, 88, 1001, 2, 55, 100
        };

        sort(arr);
        for (int i = 1; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
