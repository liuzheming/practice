package dataStructure.argorithmsTest;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/1.
 */
public class BST<K extends Comparable<K>, V> {

    private Node root;


    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return size(x.left) + size(x.right) + 1;
    }

    public void put(K key, V val) {
        root = put(key, val, root);
    }

    private Node put(K key, V val, Node x) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(key, val, x.right);
        } else if (cmp < 0) {
            x.left = put(key, val, x.left);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Node get(K key) {
        Node node = get(key, root);
        return node == null ? null : node;
    }

    /**
     * @param key 被查找的目标key值
     * @param x   当前子树的root节点
     * @return key对应的节点
     */
    private Node get(K key, Node x) {
        if (x == null) return null; // 未命中
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x; // 命中
        } else if (cmp > 0) {
            return get(key, x.right);
        } else {
            return get(key, x.left);
        }
    }


    private class Node {

        private K key;

        private V val;

        private Node left;

        private Node right;

        private int size;  // 以当前节点为根的子树的大小

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }

        public Node(K key, V val, Node l, Node r) {
            this(key, val);
            this.left = l;
            this.right = r;
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


    public static void main(String[] args) {

        BST<Integer, String> tree = new BST<>();

        tree.put(6, "擎天柱");
        tree.put(3, "霸天虎");
        tree.put(5, "阿童木");
        tree.put(8, "太上老君");
        tree.put(4, "镇元大仙");
        tree.put(34,"大黄蜂");
        tree.put(22,"蜘蛛侠");

        System.out.println(tree.get(3));
        System.out.println(tree.get(5));
        System.out.println(tree.get(6));


    }

}
