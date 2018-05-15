package dataStructure;

/**
 * Description: 优先队列,二叉堆实现
 * <p>
 * Created by lzm on 2018/1/18.
 */
public class PriorityQueue<T extends Comparable<T>> {

    private T[] pq;

    private int size;

    public PriorityQueue(int length) {
        pq = (T[]) new Comparable[length];
    }

    public void insert(T t) {
        if (size == pq.length - 1) {
            ensureCapacity(size * 2);
        }
        pq[++size] = t;
        swim();
    }

    private void swim() {
        int idx = size;
        while (idx > 1 && less(idx / 2, idx)) {
            exch(idx, idx / 2);
            idx = idx / 2;
        }
    }

    private void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 比较大小的方法统一封装起来
     * 1、不易出错
     * 2、方便修改
     */
    private boolean less(int i, int j) {
        //TODO 升序或是降序,由此处控制
        return pq[i].compareTo(pq[j]) > 0;
    }


    public T delMin() {
        T t = pq[1];
        pq[1] = pq[size];
        pq[size--] = null;
        sink(1);
        if (size < pq.length / 4) {
            ensureCapacity(pq.length / 2);
            System.out.println("缩容一次");
        }
        return t;
    }

    public T min() {
        return pq[1];
    }

    private void sink(int idx) {
        while (idx * 2 <= size) {
            int j = idx * 2;
            if (j < size && less(j, j + 1)) j++;
            if (less(j, idx)) break;
            exch(j, idx);
            idx = j;
        }
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        T[] old = pq;
        pq = (T[]) new Comparable[newCapacity];
        for (int i = 1; i <= size(); i++)
            pq[i] = old[i];

    }


    public static void main(String... args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(3);

        pq.insert(4);
        pq.insert(10);
        pq.insert(345);
        pq.insert(3);
        pq.insert(87);
        pq.insert(66);
        pq.insert(998);
        pq.insert(4);


        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());
        System.out.println(pq.delMin());

    }


}
