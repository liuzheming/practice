package dataStructure.mylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description: 在链表的两头,分别有一个节点作为逻辑上的开始节点,这样做的好处是,避免了很多特殊情况从而简化了编码
 * <p>
 * Created by lzm on 2017/9/28.
 */
public class MyLinkedList<T> implements Iterable<T> {


    private int theSize;

    private int modCount = 0;

    private Node<T> beginMarker;

    private Node<T> endMarker;

    public MyLinkedList() {
        clear();
    }


    public void clear() {

        beginMarker = new Node<>(null); // 表头部的标记节点:头结点
        endMarker = new Node<>(null, beginMarker, null);   // 表尾部的标记节点:尾节点
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void add(T val) {
        add(size(), val);
    }

    public void add(int idx, T val) {
        addBefore(getNode(idx), val);
    }


    public T remove(int idx) {
        return remove(getNode(idx));
    }


    public T set(int idx, T val) {
        Node<T> node = getNode(idx);
        T oldVal = node.data;
        node.data = val;
        return oldVal;
    }

    public T get(int idx) {
        return getNode(idx).data;
    }


    private void addBefore(Node<T> current, T val) {
        Node<T> newNode = new Node<>(val, current.prev, current);
        current.prev = current.prev.next = newNode;
        theSize++;
        modCount++;
    }

    /**
     * Removes the object contains in Node p.
     *
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private T remove(Node<T> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * Gets the Node at position idx,which must range from 0 to size().
     *
     * @param idx index of node being obtained.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size().
     */
    private Node<T> getNode(int idx) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> current;
        if (idx > size() >> 1) {
            current = endMarker;
            for (int i = size() - 1; i >= idx; i--) {
                current = current.prev;
            }
        } else {
            current = beginMarker;
            for (int i = 0; i <= idx; i++) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public T next() {

            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T nextVal = current.data;
            current = current.next;
            okToRemove = true;
            return nextVal;
        }

        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }

    }

    private static class Node<T> {

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.prev = pre;
            this.next = next;
        }


        private Node<T> prev;

        private Node<T> next;

        private T data;

    }

    @Override
    public String toString() {
        Iterator<T> it = this.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next()).append(",");
        }
        return isEmpty() ? "[]" : "[" + sb.substring(0, sb.length() - 1) + "]";
    }
}
