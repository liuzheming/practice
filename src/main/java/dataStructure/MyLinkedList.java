package dataStructure;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/9/26.
 */
public class MyLinkedList {


    private int size;

    private Node first;

    private Node last;


    public MyLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    public void add(Node node) {
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.setNext(node);
            last = node;
        }
        size++;
    }


    public Node remove(int index) {
        Node temp;
        checkIndex(index);
        if (index == 0) {
            temp = first;
            first = first.getNext();
        } else {
            temp = get(index - 1).getNext();
            get(index - 1).setNext(temp.getNext());
        }
        size--;
        return temp;
    }

    public Node get(int index) {
        checkIndex(index);
        Node temp = first; // 如果index==1,返回first
        if (index > 0) {   // 如果index >1,逐个向后查找
            for (int i = 1; i <= index; i++) {
                temp = temp.getNext();
            }
        }
        return temp;
    }

    public void checkIndex(int index) {
        if (index > size - 1) {
            throw new IndexOutOfBoundsException("下标越界 [index=" + index + "]");
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isEmpty()) {
            sb.append(first.getValue() + ",");
            Node temp = first.getNext();
            while (temp != null) {
                sb.append(temp.getValue() + ",");
                temp = temp.getNext();
            }
        }
        return "[" + sb.substring(0, sb.length() - 1) + "]";
    }

}
