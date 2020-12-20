package dataStructure.binarytree;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeMap;

/**
 * Description:
 * <p>
 * Created by lzm on 2020/12/17.
 */
@Slf4j
public class PrintBinaryTree {

    public static void main(String[] args) {

        // 构造一颗二叉树
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // 打印二叉树,从跟节点开始,逐层打印,每层一次换行

        printFromTopToBottom(root);

    }

    public static void printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return;
        }
        int depth = depth(root);
        log.info("tree high:{}", depth);
        for (int i = 1; i <= depth; i++) {
            levelOrder(root, i);
            System.out.print("\n");
        }
    }

    public static void levelOrder(TreeNode node, int level) {

        if (node == null || level < 0) {
            return;
        }
        if (level == 1) {
            System.out.print(node.value + " ");
        }
        levelOrder(node.left, level - 1);
        levelOrder(node.right, level - 1);

    }

    public static int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = depth(node.left);
        int r = depth(node.right);
        return l > r ? (l + 1) : (r + 1);
    }

}


class TreeNode {

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode left;

    public TreeNode right;

    public int value;

}

