package dataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description: 动态数组
 * <p>
 * Created by lzm on 2017/9/27.
 */
public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }


    public T get(int idx) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];

    }

    public T set(int idx, T newVal) {
        if (idx < 0 || idx > size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }


    public boolean add(T newVal) {
        add(size(), newVal);
        return true;
    }

    public void add(int idx, T newVal) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2);
        }
        for (int i = size(); i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newVal;

        theSize++;
    }


    public T remove(int idx) {
        T removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedItem;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }
        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];

    }


    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    private class ArrayListIterator implements Iterator<T> {

        private int current = 0; //此指针指向 .next() 方法所返回item

        public boolean hasNext() {
            return current < size();
        }

        private boolean okToRemove = false;

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            okToRemove = true;
            return theItems[current++];
        }

        public void remove() {
            if (!okToRemove) {
                throw new NoSuchElementException();
            }
            MyArrayList.this.remove(--current);
            okToRemove = false;
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append(",");
        }
        return "[" + sb.substring(0, sb.length() - 1) + "]";
    }

}
