package dataStructure;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/10/12.
 */
public class BinarySearchTree<T extends Comparable<T>> {


    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMax() {
        if (isEmpty())
            throw new RuntimeException("这棵树是空的!");
        return findMax(root).element;
    }

    public T findMin() {
        if (isEmpty())
            throw new RuntimeException("这课树是空的!");
        return findMin(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {
        printTree(root);
    }

    /**
     * Internal method to find the smallest in a subtree.
     *
     * @param root the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<T> findMin(BinaryNode<T> root) {

        if (root == null) {
            throw null;
        } else if (root.left == null) {
            return root;
        }
        return findMin(root.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param root the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<T> findMax(BinaryNode<T> root) {
        if (root != null)
            while (root.right != null) {
                root = root.right;
            }
        return root;
    }


    /**
     * Internal method to find an item in a subTree.
     *
     * @param x    is item to search for.
     * @param root the node that roots the subTree
     * @return dose the subtree contains the matched item.
     */
    private boolean contains(T x, BinaryNode<T> root) {
        if (root == null) {
            return false;
        }
        int compareResult = x.compareTo(root.element);
        if (compareResult < 0) {
            return contains(x, root.left);
        } else if (compareResult > 0) {
            return contains(x, root.right);
        } else {
            return true;  // Match
        }
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x    the item to insert.
     * @param root the node that root the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> root) {
        if (root == null) {
            return new BinaryNode<>(x);
        }
        int compareResult = x.compareTo(root.element);
        if (compareResult > 0) {
            root.right = insert(x, root.right);
        } else if (compareResult < 0) {
            root.left = insert(x, root.left);
        } else {
            // Duplicate item, do nothing
        }

        return root;
    }


    /**
     * Internal method to remove from a subtree
     *
     * @param x    the item to remove.
     * @param root the node that roots to the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> root) {

        if (root == null) {
            return root; // Item not found; do nothing.
        }

        int compareResult = x.compareTo(root.element);

        if (compareResult < 0) {
            root.left = remove(x, root.left);
        } else if (compareResult > 0) {
            root.right = remove(x, root.left);
        } else if (root.left != null && root.right != null) {
            root.element = findMin(root.left).element;
            root.right = remove(root.element, root.right);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }
        return root;
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param root the node that roots the subtree.
     */
    private void printTree(BinaryNode<T> root) {
        if (root != null) {
            printTree(root.left);
            System.out.println(root.element);
            printTree(root.right);
        }
    }

    /**
     * Internal method to compute height of the subtree.
     *
     * @param root the node that roots the subtree.
     * @return the height of the subtree.
     */
    private int height(BinaryNode<T> root) {

        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }

    }


    private static class BinaryNode<T extends Comparable<T>> {

        BinaryNode(T element) {
            this(element, null, null); // 构造方法中可用 this() 调用构造方法,且只有在构造方法中才能这么做?
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        private T element;

        private BinaryNode<T> left;

        private BinaryNode<T> right;

    }

}
