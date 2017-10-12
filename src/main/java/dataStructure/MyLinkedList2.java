package dataStructure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:LikedList有一个头结点,一个尾节点
 * <p>
 * Created by lzm on 2017/10/9.
 */
public class MyLinkedList2<T> implements Iterable<T> {

    private Node<T> beginMarker;

    private Node<T> endMarker;

    private int theSize;

    private int modCount = 0;

    public MyLinkedList2() {
        clear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void clear() {
        beginMarker = new Node<>(null);
        endMarker = new Node<>(null);
        beginMarker.next = endMarker;
        endMarker.prev = beginMarker;
        theSize = 0;
        modCount++;
    }

    public void add(T newVal) {
        add(size(), newVal);
    }

    public void add(int idx, T newVal) {
        addBefore(getNode(idx), newVal);
    }

    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private T remove(Node<T> removed) {
        removed.prev.next = removed.next;
        removed.next.prev = removed.prev;
        theSize--;
        modCount++;
        return removed.data;
    }

    public T set(int idx, T newVal) {
        Node<T> node = getNode(idx);
        T oldVal = node.data;
        node.data = newVal;
        return oldVal;
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public void addBefore(Node<T> current, T newVal) {
        Node<T> newNode = new Node<>(newVal, current.prev, current);
        current.prev.next = newNode;
        current.prev = newNode;
        modCount++;
        theSize++;
    }


    private Node<T> getNode(int idx) {
        if (idx < 0 || idx > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> target;
        if (idx < size() >> 1) {
            target = beginMarker;
            for (int i = 0; i <= idx; i++) {
                target = target.next;
            }
        } else {
            target = endMarker;
            for (int i = size(); i > idx; i--) {
                target = target.prev;
            }
        }
        return target;
    }


    @Override
    public Iterator<T> iterator() {
        return new LikedListIterator();
    }

    private class LikedListIterator implements Iterator<T> {


        private Node<T> current = null;

        private int expectedModCount = 0;

        private boolean okToRemove = false;

        public LikedListIterator() {
            expectedModCount = MyLinkedList2.this.modCount;
            current = beginMarker;
        }

        @Override
        public boolean hasNext() {
            checkModCount();
            return current.next != endMarker;
        }

        @Override
        public T next() {
            checkModCount();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> item = current;
            current = current.next;
            okToRemove = true;
            return item.next.data;
        }

        @Override
        public void remove() {
            checkModCount();
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList2.this.remove(current);
            expectedModCount--;
            okToRemove = false;
        }

        private void checkModCount() {
            if (expectedModCount != MyLinkedList2.this.modCount) {
                throw new ConcurrentModificationException();
            }
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
