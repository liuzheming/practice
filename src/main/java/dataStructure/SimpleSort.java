package dataStructure;

/**
 * Description: 简单排序
 * <p>
 * Created by lzm on 2017/9/11.
 */
public class SimpleSort {


    /**
     * 冒泡排序,正序
     * 长度为n的数组,先排除数组中最大的数,放在最后一位
     * 然后排除第二大的数,放在倒数第二位...
     * 直到第n-1趟,排出最小的的数,则排序完成
     */
    public static void bubbleSort(long[] arr) {
        // 第一趟:从第一位开始,依次和它的后一位进行比较,两个中间较大的那个移到靠后的位置,一直比到数组最后一位
        // 第二趟:从第一位开始,依次和它的后一位进行比较,两个中间较大的那个移到靠后的位置,一直比到数组倒数第二位
        long x; // 中间变量

        // 外层循环控制排序趟数,共进行n-1趟排序
        for (int i = 1; i < arr.length; i++) {

            // 内层循环控制冒泡,每趟冒泡所排出的最大值,不需要参与下一趟排序
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换两个元素位置
                    x = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = x;
                }
            }
        }
    }


    public static void bubbleSortDesc(long[] arr) {

        long item; // 中间变量
        for (int i = 1; i < arr.length; i++) {
            for (int index = arr.length - 1; index > i - 1; index--) {
                if (arr[index] > arr[index - 1]) {
                    item = arr[index - 1];
                    arr[index - 1] = arr[index];
                    arr[index] = item;
                }
            }
        }
    }
}
