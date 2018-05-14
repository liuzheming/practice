package dataStructure;

/**
 * Description:队列,先进先出
 * <p>
 * Created by lzm on 2017/9/26.
 */
public class MyQueue {


    private long[] arr;

    private int front;

    private int end;

    private int theSize;

    public MyQueue(int size) {
        arr = new long[size];
        front = 0;
        end = -1;
        theSize = 0;
    }


    public void insert(int value) {
        arr[++end] = value;
        theSize++;
    }

    public long remove() {
        theSize--;
        return arr[front++];
    }

    public long peek() {
        return arr[front];
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public boolean isFull() {
        return theSize == arr.length;
    }

}
