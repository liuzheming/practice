package dataStructure.skiplist2;

import lombok.Data;

import java.util.Random;

/**
 * Create by lzm on 2020/11/9
 */
public class MySkipList {


    private SkipNode head, tail;

    private int size;

    private int level;

    private Random random;

    private static final double PROBABILITY = 0.5;

    public void clear() {
        head = new SkipNode(SkipNode.HEAD_KEY, null);
        tail = new SkipNode(SkipNode.HEAD_KEY, null);
        horizontalLink(head, tail);
        size = 0;
        level = 0;
    }


    public void horizontalLink(SkipNode left, SkipNode right) {
        left.right = right;
        right.left = left;
    }


    /**
     * 在最底下的一层，找到要插入位置前面的那个key值
     */
    private SkipNode findBeforeNode(int key) {
        SkipNode curr = head;
        while (true) {
            while (curr.right.key < key) {

            }
        }
    }

    private void put(int k, Object v) {
        SkipNode node = findNode(k);
        if (node != null) {
            node.val = v;
            return;
        }
        node = new SkipNode(k, v);
        // 判断是否要上升层级
    }

    private SkipNode findNode(int k) {

        return null;
    }

    @Data
    class SkipNode {

        private SkipNode up, down, left, right;

        private int key;

        private Object val;

        public static final int HEAD_KEY = Integer.MIN_VALUE;
        public static final int TAIL_KEY = Integer.MAX_VALUE;

        public SkipNode(int key, Object val) {
            this.key = key;
            this.val = val;
        }

    }

}
