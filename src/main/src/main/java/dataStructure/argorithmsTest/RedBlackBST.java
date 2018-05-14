package dataStructure.argorithmsTest;

import java.util.NoSuchElementException;

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
        return 1 + size(x.left) + size(x.right);
    }


    public void put(K key, V val) {
        root = put(key, val, root);
    }

    /**
     * 插入一个新节点,如果被插入的键已经存在,则更新此键所对应的值。
     * 被插入的新节点默认是红色的,插入新的红色节点,可能导致红黑树出现违反规则的情况,需要进行平衡操作
     * 三种违反红黑树规则的情况:
     * 1、出现红色的右链 -- 左旋,然后可能变平衡,也可能变为第二种情况
     * 2、出现两条连续向左的红链 -- 右旋,然后会变为第三种情况
     * 3、某个节点同时拥有两个红色节点 -- 变色,当前节点的平衡操作结束
     *
     * @param key key
     * @param val val
     * @param x   当前子树的树根
     * @return 返回当前子树的树根, 如果当前子树为空, 则返回新创建的节点
     */
    private Node put(K key, V val, Node x) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, val, x.right);
        else if (cmp < 0) x.left = put(key, val, x.left);
        else x.val = val;
        if (isRed(x.right)) x = rotateLeft(x);                       // 左旋
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x); // 右旋
        if (isRed(x.left) && isRed(x.right)) flipColors(x);          // 变色
        x.size = size(x);
        return x;
    }


    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return null;

        if (!isRed(x.left) && !isRed(x.left.left)) // 当前节点的左子节点不为RED,需要进行合并或者向它的兄弟借一个节点
            x.left = moveRedLeft(x.left);

        x.left = deleteMin(x.left);

        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left))
            x = rotateRight(x);

        if (x.right == null)
            return null;

        if (!isRed(x.right) && !isRed(x.right.left))
            x = moveRedRight(x);

        x.right = deleteMax(x.right);

        return balance(x);
    }

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        return x;
    }


    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node rotateLeft(Node oldRoot) {
        Node newRoot = oldRoot.right;
        oldRoot.right = newRoot.left;
        newRoot.color = oldRoot.color;  // 旋转后新根节点的颜色同于旧根节点的颜色
        oldRoot.color = RED;            // 旧的根节点不论过去是什么颜色,旋转以后都变为红色
        newRoot.left = oldRoot;
        newRoot.size = oldRoot.size;
        oldRoot.size = size(oldRoot);
        return newRoot;
    }

    private Node rotateRight(Node oldRoot) {
        Node newRoot = oldRoot.left;
        oldRoot.left = newRoot.right;
        newRoot.color = oldRoot.color;
        oldRoot.color = RED;
        newRoot.right = oldRoot;
        newRoot.size = oldRoot.size;
        oldRoot.size = size(oldRoot);
        return newRoot;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }


    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
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


    public static void main(String... args) {

        RedBlackBST<Integer, Integer> rbTree = new RedBlackBST<>();

        for (int i = 0; i < 1000000; i++) {
            long start = System.currentTimeMillis();
            rbTree.put(i, i);
            long end = System.currentTimeMillis();
            if (i % 10000 == 0) {
                System.out.println("[i=" + i + "] [consume=" + (end - start) + "]");
            }
        }

        System.out.println(rbTree.height());


    }

}
