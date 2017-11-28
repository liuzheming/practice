package dataStructure.search;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/27.
 */
public class SeparatialSearchST<K, V> {


    private class Node {

        private K key;

        private V val;

        private Node next;

        private Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

    }

    private Node first;

    private int n;

    public SeparatialSearchST() {
        first = new Node(null, null);
    }

    public void put(K key, V val) {
        Node current = first;
        while (current.next != null) {
            if (current.next.key == key) {
                current.next.val = val;
                return;
            }
            current = current.next;
        }
        current.next = new Node(key, val);
        n++;
    }

    public V get(K key) {
        Node current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                return current.next.val;
            }
            current = current.next;
        }
        return null;
    }

    public void delete(K key) {
        Node current = first;
        while (current.next != null) {
            if (current.next.key.equals(key)) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }


}
