package dataStructure.argorithmsTest;

import dataStructure.mylist.MyArrayList;

/**
 * Description:红黑二叉树
 * <p>
 * Created by lzm on 2017/10/31.
 */
public class BlackRedBST<K extends Comparable<K>, V> {


    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public V get(K key) {
        Node node = get(key, root);
        return node == null ? null : node.val;
    }

    private Node get(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(key, x.left);
        else if (cmp > 0) return get(key, x.right);
        else return x;
    }


    public void put(K key, V val) {
        root = put(key, val, root);
        root.color = BLACK; // 根节点永远为黑色
    }

    private Node put(K key, V val, Node x) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, val, x.right);
        else if (cmp < 0) x.left = put(key, val, x.left);
        else x.val = val;

        // 平衡操作,如果x右连接为红且左链接不为红,左旋
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        // 如果连续两条向左的红链,则先右旋,再变色
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        // 两个红色子节点,变色
        if (isRed(x.right) && isRed(x.left)) flipColors(x);

        x.size = size(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node temp = x.right;
        x.color = RED;
        temp.color = BLACK;
        x.right = temp.left;
        temp.left = x;
        temp.size = x.size;
        x.size = size(x);
        return temp;
    }

    private Node rotateRight(Node x) {
        Node temp = x.left;
        x.color = RED;
        temp.color = BLACK;
        x.left = temp.right;
        temp.right = x;
        temp.size = x.size;
        x.size = size(x);
        return temp;
    }

    private void flipColors(Node x) {
        x.left.color = x.right.color = BLACK;
        x.color = RED;
    }

    private boolean isRed(Node x) {
        return x.color == RED;
    }


    public K max() {
        return root == null ? null : max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public K min() {
        return root == null ? null : min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }


    public K floor(K key) {
        Node node = floor(key, root);
        return node == null ? null : node.key;
    }

    private Node floor(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return floor(key, x.left);
        else if (cmp > 0) {
            Node temp = floor(key, x.right);
            return temp == null ? x : temp;
        } else
            return x;
    }

    private Node moveRedLeft(Node x) {
        // 假设节点x为红色,x.right和x.right.left都是黑色
        // 将x.right或者x.right的子节点之一变红
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateLeft(x.right);
            x = rotateLeft(x);
        }
        return x;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x); // 向兄弟节点借一个元素,或者,把当前节点、父节点中最小的节点、兄弟节点中最近的节点进行合并
        x.left = deleteMin(x.left);
        return balance(x);
    }

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.size = size(x);
        return x;
    }

//    public void deleteMin() {
//        if (root != null) root = deleteMin(root);
//    }
//
//    private Node deleteMin(Node x) {
//        if (x.left == null) return x.right;
//        else x.left = deleteMin(x.left);
//        x.size = size(x);
//        return x;
//    }

    public void delete(K key) {
        delete(key, root);
    }

    private Node delete(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(key, x.left);
        else if (cmp > 0) x.right = delete(key, x.right);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node newNode = min(x.right);
            deleteMin(x.right);
            newNode.left = x.left;
            newNode.right = x.right;
            return newNode;
        }
        x.size = size(x);
        return x;
    }

    public K ceiling(K key) {
        Node node = ceiling(key, root);
        return node == null ? null : node.key;
    }

    private Node ceiling(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return ceiling(key, x.right);
        else if (cmp < 0) {
            Node temp = ceiling(key, x.left);
            return temp == null ? x : temp;
        } else
            return x;
    }

    public int rank(K key) {
        return root == null ? -1 : rank(key, root);
    }

    private Integer rank(K key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return rank(key, x.right) + size(x.left) + 1;
        else return size(x.left);
    }

    public K select(int index) {
        return index < size(root) ? select(index, root).key : null;
    }

    private Node select(int index, Node x) {
        int temp = size(x.left);
        if (temp > index) return select(index, x.left);
        else if (temp < index) return select(index - size(x.left) - 1, x.right);
        else return x;
    }

    public MyArrayList<K> keys(K lo, K hi) {
        MyArrayList<K> list = new MyArrayList<>();
        keys(lo, hi, root, list);
        return list;
    }

    private void keys(K lo, K hi, Node x, MyArrayList<K> list) {
        if (x == null) return;
        int cmplo = x.key.compareTo(lo);
        int cmphi = x.key.compareTo(hi);
        if (cmplo >= 0 && cmphi <= 0) list.add(x.key);
        if (cmplo > 0) keys(lo, hi, x.left, list);
        if (cmphi < 0) keys(lo, hi, x.right, list);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private class Node {

        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size = 1;
        private boolean color = RED;

        private Node(K key, V val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        private Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    ", size=" + size +
                    '}';
        }
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null) return;
        print(node.left);
        System.out.println(node.toString());
        print(node.right);
    }

    public static void main(String[] args) {

        BlackRedBST<Integer, Integer> tree = new BlackRedBST<>();
        tree.put(1, 11);
        tree.put(2, 22);
        tree.put(3, 33);
        tree.put(4, 44);
        tree.put(44, 446);
        tree.put(66, 445);
        tree.put(33, 333);
        tree.put(22, 434);
        tree.put(50, 555);
        tree.put(60, 666);
        tree.put(70, 777);
//        tree.deleteMax();
//        tree.deleteMin();
//        tree.delete(50);
//        tree.delete(3);

        tree.print();
        System.out.println(tree.size());
        System.out.println(tree.get(5));
        System.out.println(tree.get(4));
        System.out.println(tree.get(3));
        System.out.println(tree.get(2));
        System.out.println(tree.floor(68));
        System.out.println(tree.ceiling(77));
        System.out.println(tree.rank(3));


        System.out.println(tree.select(3));
        System.out.println(tree.select(2));
        System.out.println(tree.select(1));
        System.out.println();
        System.out.println(tree.keys(5, 568));
    }
}
