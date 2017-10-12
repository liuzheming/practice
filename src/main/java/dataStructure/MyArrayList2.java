package dataStructure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/10/9.
 */
public class MyArrayList2<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;   // 集合的默认大小

    private int currentModCount = 0;

    private int theSize; // 集合所包含的元素的个数
    private T[] theItems; // 集合所包含的元素

    public MyArrayList2() {
        clear();
    }

    /**
     * 在末位增加一个元素
     *
     * @param newVal 新元素
     */
    public boolean add(T newVal) {
        add(size(), newVal);
        return true;
    }

    /**
     * 在制定位置增加一个新元素
     *
     * @param idx    指定下标
     * @param newVal 新元素
     */
    public void add(int idx, T newVal) {
        if (size() == theItems.length) {
            ensureCapacity(size() * 2);
        }
        for (int i = size(); i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newVal;
        theSize++;
        currentModCount++;
    }

    /**
     * 更改一个位置的元素
     *
     * @param idx    目标位置
     * @param newVal 新元素
     * @return 老元素
     */
    public T set(int idx, T newVal) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }


    /**
     * 查找
     */
    public T get(int idx) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    /**
     * 删除
     */
    public T remove(int idx) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removed = theItems[idx];
        for (int i = idx + 1; i < size(); i++) {
            theItems[i - 1] = theItems[i];
        }
        theSize--;
        currentModCount++;
        return removed;
    }


    /**
     * 初始化集合
     */
    private void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_SIZE);
    }


    private boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * @return 返回集合所持有的元素个数
     */
    private int size() {
        return theSize;
    }


    /**
     * 重新给集合设定新容量
     *
     * @param newCapacity 新容量
     */
    private void ensureCapacity(int newCapacity) {
        if (newCapacity <= size()) { // 如果新的容量小于集合的大小,则忽略此操作
            return;
        }
        // 如果新的容量大于现在的集合大小,则为theItems数组进行扩容
        T[] newArr = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            newArr[i] = theItems[i];
        }
        theItems = newArr;
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    /**
     * MyArrayList的迭代器
     */
    private class ArrayListIterator implements Iterator<T> {

        private int current = -1;

        private int expectedModCount = 0; // 迭代过程中不可以使用集合本身的方法改变数组大小

        private boolean okToRemove = false;

        public ArrayListIterator() {
            expectedModCount = MyArrayList2.this.currentModCount;
        }

        @Override
        public boolean hasNext() {
            checkExpectedModCount();
            return current + 1 < size();
        }

        @Override
        public T next() {
            checkExpectedModCount();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            okToRemove = true;
            return theItems[++current];
        }

        @Override
        public void remove() {
            checkExpectedModCount();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyArrayList2.this.remove(current);
            expectedModCount++;
        }

        public void checkExpectedModCount() {
            if (expectedModCount != MyArrayList2.this.currentModCount) {
                throw new ConcurrentModificationException();
            }
        }

    }

    public static void main(String... args) {

        MyArrayList2<Integer> list = new MyArrayList2<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.set(2, 100);
        Iterator<Integer> ite = list.iterator();

    }
}
