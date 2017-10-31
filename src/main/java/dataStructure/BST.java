package dataStructure;

/**
 * Description: 本类中递归调用的思想: 传入一个节点,递归的方法将会返回此节点的新值
 * <p>
 * Created by lzm on 2017/10/20.
 */
public class BST<T extends Comparable<T>> {

    private BinaryNode<T> root;

    private int size;

    public BST() {
        root = null;
        size = 0;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        this.root = null;
        this.size = 0;
    }

    public void display() {
        display(root);
    }

    private void display(BinaryNode<T> root) {
        if (root == null) {
            return;
        }
        display(root.left);
        System.out.println(root);
        display(root.right);

    }

    public void insert(T item) {
        root = insert(item, root);
    }

    /**
     * 在指定子树上插入新的节点,返回值为该子树的root节点
     *
     * @param item 将被插入的值
     * @param root 子树的跟节点
     * @return 当前子树的根节点
     */
    private BinaryNode<T> insert(T item, BinaryNode<T> root) {

        // 如果当前子树为空,则创建一个新节点并返回
        if (root == null) {
            root = new BinaryNode<>(item);
            size++;
        }

        // 如果当前节点不为空,则比较新的item和当前节点的item大小
        // 如果前者大,则对当前子树的右子树递归执行本方法
        // 如果后者大,则对当前子树的左子树递归执行本方法
        // 如果两者相等,注意这种情况是最不可能出现的,所以放在最后面,则什么都不做

        int cmp = item.compareTo(root.item);

        if (cmp > 0) {
            root.right = insert(item, root.right);
        } else if (cmp < 0) {
            root.left = insert(item, root.left);
        }

        return root;
    }

    public boolean contains(T item) {
        return contains(item, root);
    }

    /**
     * 在指定子树中查找item
     *
     * @param item 查找目标
     * @param root 子树树根
     * @return 子树中是否包含查找目标
     */
    private boolean contains(T item, BinaryNode<T> root) {
        // 如果子树为空,说明不包含
        if (root == null) {
            return false;
        }
        // 如果子树不为空,判断并做相应的递归调用
        int cmp = item.compareTo(root.item);
        if (cmp > 0) {
            return contains(item, root.right);
        } else if (cmp < 0) {
            return contains(item, root.left);
        } else {
            return true;
        }
    }


    public void remove(T item) {
        // 删除节点,判断每个节点的子节点,如果子节点匹配,则删除,如果不匹配,则继续查找
        root = remove(item, root);
    }


    /**
     * 在指定子树内查找并删除节点,返回值为当前子树的根节点
     * 注意在调用此方法的客户端出,必须使用被查找的子树的根节点来接收返回值,因为
     * 需要使用这种方式来给被删除的节点重新赋值
     *
     * @param item 目标元素
     * @param root 当前子树的根节点
     * @return 子树的根节点
     */
    private BinaryNode<T> remove(T item, BinaryNode<T> root) {

        // 如果子树为空,说明不包含
        if (root == null) {
            return null;
        }

        // 如果子树不为空,判断并做相应的递归调用
        // 删除当前节点时,需要判断两种情况:1、当前节点有一颗子树; 2、当前节点有两颗子树或者没有子树
        int cmp = item.compareTo(root.item);
        if (cmp > 0) {
            root.right = remove(item, root.right);
        } else if (cmp < 0) {
            root.left = remove(item, root.left);
        } else {
            if (root.left != null && root.right != null) {   // 当前节点有两颗子树
                root.item = findMin(root.right).item;
                remove(root.item, root.right);
            } else {                                                // 当前节点有一颗子树或者没有子树
                root = root.right == null ? root.left : root.right;
            }
            size--;
        }
        return root;
    }


    /**
     * 查找二叉树里最大的元素
     * <p>
     * 从根元素开始,如果根元素的右子节点不为空,就对他的右子节点做递归查询
     *
     * @return 最大值
     */
    public BinaryNode<T> findMax() {
        if (root == null) {
            return null;
        } else {
            return findMax(root);
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> root) {
        if (root.right == null) {
            return root;
        } else {
            return findMax(root.right);
        }
    }

    public BinaryNode<T> findMin() {
        if (root == null) {
            return null;
        } else {
            return findMin(root);
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> root) {
        if (root.left == null) {
            return root;
        } else {
            return findMax(root.left);
        }
    }


    public Iterable<T> range(T lo, T hi) {
        MyArrayList<T> list = new MyArrayList<>();
        range(root, list, lo, hi);
        return list;
    }

    /**
     * 找出给定节点为根的子树所有在[lo,hi]之间的元素,满足条件的元素添加到参数list中
     * 利用二叉搜索树每个节点的左子树都小于此节点,而其所有的右子树有都大于此节点,
     * 可以跳过部分不可能落在查询范围内的子树。
     * <p>
     * 步骤:
     * 1、比较用当前节点和上界下界比较大小;
     * 2、对当前节点的左子树进行递归判断(条件:当前节点在(lo,hi]范围内);
     * 3、判断点钱节点,如果满足条件,则加入list中;
     * 4、对当前节点的右子树进行递归判断(条件:当前节点在[lo,hi)范围内);
     *
     * @param x    树根
     * @param list 盛放满足条件的元素对象
     * @param lo   下界
     * @param hi   上界
     */
    private void range(BinaryNode<T> x, MyArrayList<T> list, T lo, T hi) {

        if (x == null) {
            return;
        }
        int cmpLo = x.item.compareTo(lo);
        int cmpHi = x.item.compareTo(hi);

        // 前序遍历
        // 遍历左子树
        if (cmpLo > 0) range(x.left, list, lo, hi); // 如果当前节点大于条件下界,则继续遍历左子树
        // 遍历中间节点
        if (cmpLo >= 0 && cmpHi <= 0) list.add(x.item);
        // 遍历右子树
        if (cmpHi < 0) range(x.right, list, lo, hi);// 如果当前节点小于条件上界,则继续遍历右子树

    }

    private static class BinaryNode<T> {

        private T item;

        private BinaryNode<T> left;

        private BinaryNode<T> right;


        private BinaryNode(T item, BinaryNode<T> left, BinaryNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        private BinaryNode(T item) {
            this.item = item;
        }


        @Override
        public String toString() {
            return "BinaryNode{" +
                    "item=" + item +
                    '}';
        }
    }


}
