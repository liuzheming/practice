package dataStructure;

/**
 * Description:栈, 先进后出
 * <p>
 * Created by lzm on 2017/9/26.
 */
public class MyStack {

    private Long arr[];

    private int top;

    public MyStack(int size) {
        arr = new Long[size];
        top = -1;
    }

    public void push(long value) {
        arr[++top] = value;
    }

    public long peek() {
        return arr[top];
    }

    public long pop() {
        return arr[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

}
