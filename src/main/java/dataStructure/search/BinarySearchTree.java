package dataStructure;

/**
 * Description:搜索二叉树,每个节点的左子树内任意节点,都要小于该节点;而每个子树的右子树内的任意节点
 * 都要大于该节点
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
     * <p>
     * 注意测试的顺序,
     * 最重要的,首先必须要对是否空树进行测试,以避免出现空指针异常。
     * 其次,剩下的测试中,应该把最不可能出现的情况,放在最后面进行。
     * 另外,本例子中的递归调用时可以用一个while循环来代替的,这里使用了尾递归是合理的,
     * 因为尽管算法表达式的简明性是以降低速度为代价的,但是这里所使用的栈空间的量也只不
     * 过是 O(logN) 而已。
     *
     * @param x    is item to search for.
     * @param root the node that roots the subTree
     * @return dose the subtree contains the matched item.
     */
    private boolean contains(T x, BinaryNode<T> root) {
        if (root == null) return false;
        int compareResult = x.compareTo(root.element);
        if (compareResult < 0) return contains(x, root.left);
        else if (compareResult > 0) return contains(x, root.right);
        else return true;  // Match
    }

    /**
     * Internal method to insert into a subtree.
     * 由于root引用该树的根,而根又在第一次插入数据时会发生改变,
     * 所以insert被写成一个返回新树根的引用的方法。
     *
     * @param x    the item to insert.
     * @param root the node that root the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> root) {
        if (root == null) return new BinaryNode<>(x);
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
     * <p>
     * 有三种情况:
     * 1、目标节点为叶子节点,直接删除。
     * 2、目标节点只一颗子树,删除目标节点,并用将其子节点替换在目标节点的位置。
     * 3、目标节点有两颗子树,删除目标节点,将其右子树中最小的节点替换到其位置上。
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
