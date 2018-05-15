package dataStructure;

import java.util.Arrays;

/**
 * Description:循环队列
 * <p>
 * Created by lzm on 2017/9/26.
 */
public class MyCycleQueue<T> {

    private Long[] arr;

    private int front;

    private int end;

    private int eleNum;

    public MyCycleQueue(int size) {
        arr = new Long[size];
        front = 0;
        end = -1;
        eleNum = 0;
    }

    public boolean isFull() {
        return eleNum == arr.length;
    }

    public boolean isEmpty() {
        return eleNum == 0;
    }

    @Override
    public String toString() {
        return "MyCycleQueue{" +
                "arr=" + Arrays.toString(arr) +
                ", front=" + front +
                ", end=" + end +
                ", eleNum=" + eleNum +
                '}';
    }

    public void insert(long value) {
        if (!isFull()) {
            // 如果队列末尾下标到达数组边界且数组尚未全满,
            // 则末尾转移至数组首位
            if (end == arr.length - 1) {
                end = -1;
            }
            arr[++end] = value;
            eleNum++;
        } else {
            throw new IndexOutOfBoundsException("循环队列已满,插入失败[value=" + value + ";cycleQueue=" + this.toString() + "]");
        }
    }


    public long remove() {
        if (!isEmpty()) {
            long value = arr[front++];
            if (front > arr.length - 1) {
                front = 0;
            }
            eleNum--;
            return value;
        } else {
            throw new IndexOutOfBoundsException("循环队列为空,弹出失败[cycleQueue=" + this.toString() + "]");
        }
    }

    public long peek() {
        if (!isEmpty()) {
            return arr[front];
        } else {
            throw new IndexOutOfBoundsException("循环队列为空,弹出失败[cycleQueue=" + this.toString() + "]");
        }
    }

}
