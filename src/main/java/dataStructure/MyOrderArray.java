package dataStructure;

import java.util.Arrays;

/**
 * Description: 有序数组
 * <p>
 * Created by lzm on 2017/9/6.
 */
public class MyOrderArray {

    private Long[] arr;

    /**
     * the number of elements it contains
     */
    private int size;


    public MyOrderArray() {
        arr = new Long[50];
    }

    public MyOrderArray(int max) {
        arr = new Long[max];
    }


    /**
     * 末尾处插入一个元素
     */
    public void insert(long value) {
        // 插入数据,需要知道数组下标的位置
        if (size >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (size == 0) {    // 如果数组为空,直接插入第一个位置
            arr[size] = value;
        } else {
            // 不为空,从最后一个开始,同数组里元素挨个比较,遇到大于value的值,
            // 则element向后移一位,直到element<value停止
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i] < value) {
                    arr[i + 1] = value;
                    break;
                } else {
                    arr[i + 1] = arr[i];
                    arr[i] = value;
                }
            }
        }
        size++;
    }

    /**
     * 删除某位上的元素,后面的元素依次向前移动一位
     */
    public long remove(int index) {
        checkIndex(index);
        long item = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
            arr[i + 1] = null;
        }
        size--;
        return item;
    }

//    /**
//     * 更新元素
//     *
//     * @param index 目标位置
//     * @param value 替换后的值
//     */
//    public void update(int index, long value) {
//        checkIndex(index);
//        arr[index] = value;
//    }

    /**
     * 获取下标为index的元素
     */
    public Long get(int index) {
        checkIndex(index);
        return arr[index];
    }

    /**
     * 返回数组所包含的元素个数
     */
    public int size() {
        return size;
    }

    /**
     * TODO 折半查找
     */
    public int search(long value) {
        if (value < arr[0] || value > arr[size - 1]) {
            return -1;
        }
        return binarySearch(value, 0, size());
    }

    /**
     * 二分法在给定的有序数组内查找某个值:在有序数组中查找时,将数组根据中间值分为前后两部分,
     * 用目标数组和数组的中间值比较,根据结果可以确定下一轮需要和哪部分进行比较,然后继续前面的
     * 步骤。直到找到目标,或者确定数组中不存在目标值。
     * <p>
     * 判断中间位置的值是否和相等,如果相等则返回中间位置的下标;
     * 如果不相等,检查给定数组长度是否大于1,
     * 如果否,则说明未能找到value,返回-1;
     * 如果是,则根据中间值和value的大小关系判断下一次查找的范围,然后再次调用自身进行查找。
     * <p>
     * 使用二分法查找有什么好处?
     * <p>
     * 最差时间复杂度:O(log2N)  当输入量很大时,比较节约时间
     * 假设共有N个元素,需要执行x次才能找到目标,最坏的情况是查找到最后一个元素时,
     * 才能找到目标元素, 即 N / 2^x = 1  => x=log2N
     *
     * @param value 目标
     * @param start 起始下标
     * @param end   终止下标
     * @return 目标在数组中的下标
     */
    private int binarySearch(long value, int start, int end) {
        long middle = arr[(start + end) / 2];
        if (middle == value) {
            return (start + end) / 2;
        } else {
            if (start >= end) {
                return -1;
            } else if (middle > value) {
                return binarySearch(value, start, (start + end) / 2 - 1);
            } else {
                return binarySearch(value, (start + end) / 2 + 1, end);
            }
        }
    }


    /**
     * 验证下标是否越界
     * 如果下标小于0或大于数组长度,则抛出异常
     *
     * @param index 目标位置
     */
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("数组下标越界 [index=" + index + "]");
        }
    }

    public void display() {
        System.out.print(Arrays.asList(arr));
    }


}
