package dataStructure;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/10/28.
 */
public class BlackRedBST<K extends Comparable<K>, V> {


    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) return get(key, node.right);
        else if (cmp < 0) return get(key, node.left);
        else return node.val;
    }

    public void put(K key, V val) {
        root = put(key, val, root);
    }

    public Node put(K key, V val, Node node) {
        if (node == null) {
            node = new Node(key, val);
        } else {
            int cmp = key.compareTo(node.key);
            if (cmp > 0) node.right = put(key, val, node.right);
            if (cmp < 0) node.left = put(key, val, node.left);
        }
//        node.size++;
        node.size = size(node.left) + size(node.right);
        return node;
    }

    public V min() {
        if (root == null) return null;
        return min(root).val;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    public V max() {
        if (root == null) return null;
        else return max(root).val;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        else return max(node.right);
    }

    public K floor(K key) {
        Node node = floor(key, root);
        return node == null ? null : node.key;
    }

    /**
     * 找到以node为根的二叉树中所有
     * 小于等于key的元素当中的最大的元素
     * <p>
     * 极端的情况:
     * 1:本树为空树,所以 floor = null
     * 2:树里每个节点都大于key,所以 floor = null
     * 3:树里每个节点都小于key,所以 floor = max(all of tree)
     * 4:树里存在小于key的元素,所以 floor = max(小于key的元素)
     * <p>
     * key < root,
     *
     * @param key  目标key
     * @param node 当前比较的节点
     * @return 小于且最接近key的节点, 如果是空树, 则返回null
     */
    private Node floor(K key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);


        if (cmp < 0) {
            // key小于node,则floor一定在左子树中
            return floor(key, node.left);
        } else if (cmp > 0) {
            // 目标值大于node,
            // 那么floor可能在当前节点的右子树中,也可能就是当前node节点
            Node temp = floor(key, node.right);
            return temp == null ? node : temp;
        } else {    // key == node.key  返回node
            return node;
        }
    }

    public K ceiling(K key) {
        Node node = ceiling(key, root);
        return node == null ? null : node.key;
    }

    private Node ceiling(K key, Node node) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        if (cmp > 0) ceiling(key, node.right);
        // else if (cmp < 0)
        Node temp = ceiling(key, node.left);
        if (temp == null) return node;
        else return temp;
    }


    public K select(int k) {
        Node node = select(k, root);
        return node == null ? null : node.key;
    }

    /**
     * 查找当前子树中排名为k的节点,排名从0开始算起
     *
     * @param k 排名
     * @param x 当前子树的根节点
     * @return 二叉树内排名为k的节点
     */
    private Node select(int k, Node x) {
        if (x == null) return null;
        int t = size(x.left);
        if (k > t) return select(k - t - 1, x.right);
        else if (k < t) return select(k, x.left);
        else return x;
    }

    public int rank(K key) {
        return rank(key, root);
    }

    /**
     * 查询key在二叉树里的排序,排序从0开始
     *
     * @param key target
     * @param x   当前子树的树根
     * @return key键在子树里的排名
     */
    private int rank(K key, Node x) {
        if (x == null) return -1;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return rank(key, x.right) + 1 + size(x.left);
        else return size(x.left);
    }

    public void delete(K key) {
        root = delete(key, root);
    }

    private Node delete(K key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = delete(key, x.right);
        else if (cmp < 0) x.left = delete(key, x.left);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node node = min(x.right);
            node.left = x.left;
            node.right = deleteMin(x.right);
            return node;
        }
        return x;
    }

    public void deleteMin() {
        if (root == null) return;
        root = deleteMin(root);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        else x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (root == null) return;
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        else x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public MyLinkedList<K> keys(K lo, K hi) {
        return keys(lo, hi, root, new MyLinkedList<>());
    }

    private MyLinkedList<K> keys(K lo, K hi, Node x, MyLinkedList<K> list) {
        if (x == null) return null;
        int cmplo = x.key.compareTo(lo);
        int cmphi = x.key.compareTo(hi);
        if (cmplo > 0) keys(lo, hi, x.left, list);
        if (cmplo >= 0 && cmphi <= 0) list.add(x.key);
        if (cmphi < 0) keys(lo, hi, x.right, list);
        return list;
    }


    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int height;
        private int size; // 以本节点为根的子树的大小

        private Node(K k, V v, Node left, Node right) {
            this.key = k;
            this.val = v;
            this.left = left;
            this.right = right;
        }

        private Node(K k, V v) {
            key = k;
            val = v;
        }
    }

}
