package dataStructure.argorithmsTest;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/13.
 */
public class RedBlackBST<K extends Comparable<K>, V> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private Node root;


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }


    public void add(K key, V val) {
        root = add(key, val, root);
    }

    /**
     * 插入一个新节点,如果被插入的键已经存在,则更新此键所对应的值
     *
     * @param key key
     * @param val val
     * @param x   当前子树的树根
     * @return 返回当前子树的树根, 如果当前子树为空, 则返回新创建的节点
     */
    private Node add(K key, V val, Node x) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = add(key, val, x.right);
        else if (cmp < 0) x.left = add(key, val, x.left);
        else x.val = val;
        return x;
    }


    private class Node {

        private K key;
        private V val;
        private boolean color = RED;
        private Node left;
        private Node right;
        private int size = 1;

        private Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        private Node(K key, V val) {
            this(key, val, null, null);
        }


    }

}
