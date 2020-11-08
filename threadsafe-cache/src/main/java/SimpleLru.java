import java.util.HashMap;
import java.util.Map;

/**
 * Create by lzm on 2020/11/8
 */
public class SimpleLru {

    int size;
    int capacity;
    Node head = new Node();
    Node tail = new Node();
    Map<Object, Node> cache = new HashMap<>();

    public SimpleLru(int capacity) {
        this.capacity = capacity;
        head.after = tail;
        tail.before = head;
    }

    int size() {
        return size;
    }

    Object remove(Object key) {
        if (cache.containsKey(key)) {
            Node removed = cache.remove(key);
            removed.before.after = removed.after;
            removed.after.before = removed.before;
            size--;
            return removed.val;
        }
        return null;
    }

    Object put(Object key, Object val) {
        if (size >= capacity) {
            Node last = tail.before;
            remove(last.key);
        }
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // remove from list
            node.before.after = node.after;
            node.after.before = node.before;
            // add node to first
            moveToFirst(node);
            return node.val;
        } else {
            Node curr = new Node(key, val);
            cache.put(key, curr);
            moveToFirst(curr);
            return null;
        }
    }

    private void moveToFirst(Node node) {
        head.after.before = node;
        node.before = head;
        node.after = head.after;
        head.after = node;
    }

    public String myToString() {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr.after != null) {
            sb.append(curr.key).append(",");
            curr = curr.after;
        }
        return sb.toString();
    }

    class Node {

        Object key;
        Object val;

        Node before;
        Node after;

        Node() {

        }

        Node(Object key, Object val) {
            this.key = key;
            this.val = val;
        }

    }


    public static void main(String... args) throws Exception {
        SimpleLru lru = new SimpleLru(10);

        lru.put(1, 111);
        System.out.println(lru.myToString());
        lru.put(2, 222);
        System.out.println(lru.myToString());
        lru.put(3, 333);
        System.out.println(lru.myToString());
        lru.remove(2);
        System.out.println(lru.myToString());

    }


}
