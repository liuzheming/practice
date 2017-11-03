package dataStructure.argorithmsTest;

import dataStructure.MyArrayList;

/**
 * Description:红黑二叉树
 * <p>
 * Created by lzm on 2017/10/31.
 */
public class BlackRedBST<K extends Comparable<K>, V> {


    private Node root;


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
    }

    private Node put(K key, V val, Node x) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, val, x.right);
        else if (cmp < 0) x.left = put(key, val, x.left);
        x.size = size(x);
        return x;
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

    public void deleteMax() {
        if (root != null) root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = deleteMax(x.right);
        x.size = size(x);
        return x;
    }

    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        else x.left = deleteMin(x.left);
        x.size = size(x);
        return x;
    }

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

    private class Node {

        private K key;
        private V val;
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
