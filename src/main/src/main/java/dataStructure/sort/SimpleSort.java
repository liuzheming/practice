package dataStructure.sort;

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

    /**
     * 第一轮排序从数组的第二位开始
     * 第二轮排序从数组的第三位开始,共进行n-1轮排序
     * 每轮排序时,把指针指向的temp和它前面的数字依次进行比较
     * 比较的结果,如果前面的值大于temp,则继续向前比较,直到前
     * 面的的值小于temp时,将temp插入到此较小值的后面
     */
    public static void insertSort(Long[] arr) {
        long temp;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int index;
            for (index = i - 1; index >= 0 && arr[index] > temp; index--) {
                arr[index + 1] = arr[index];
            }
            arr[++index] = temp;
        }
    }

    /**
     * 选择排序
     * 第一趟,把数组第一个元素和它后面最小的元素互换
     * 第二趟,把数组第二个元素和它后面最小的元素互换
     * 共进行n-1趟排序,排序进行的比较总次数为 (n-1) + (n-2) + ··· + 1 = n * n /2
     */
    public static void selectSort(Long[] arr) {

        long temp;
        int index;
        for (int i = 0; i < arr.length - 1; i++) {
            temp = arr[i];
            index = i;
            for (int j = i; j < arr.length; j++) {
                //逐个和temp进行比较,发现更小的时,替换为temp值,并将新值的下标赋给index
                if (temp > arr[j]) {
                    temp = arr[j];
                    index = j;
                }
            }
            arr[index] = arr[i];
            arr[i] = temp;
        }

    }

}
