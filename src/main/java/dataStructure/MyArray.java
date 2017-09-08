package dataStructure;

import java.util.Arrays;

/**
 * Description: 数组
 * <p>
 * Created by lzm on 2017/9/4.
 */
public class MyArray {


    private Long[] arr;

    /**
     * the number of elements it contains
     */
    private int size;


    public MyArray() {
        arr = new Long[50];
    }

    public MyArray(int max) {
        arr = new Long[max];
    }


    /**
     * 末尾处插入一个元素
     */
    public long insert(long value) {
        // 插入数据,需要知道数组下标的位置
        if (size >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[size++] = value;
        return arr[size - 1];
    }

    /**
     * 删除某位上的元素,后面的元素依次向前移动一位
     */
    public long delete(int index) {
        checkIndex(index);
        long item = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index);
        size--;
        return item;
    }

    /**
     * 更新元素
     *
     * @param index 目标位置
     * @param value 替换后的值
     */
    public void update(int index, long value) {
        checkIndex(index);
        arr[index] = value;
    }

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
     * 查找某个值在数组中的位置
     */
    public int search(long value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
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
