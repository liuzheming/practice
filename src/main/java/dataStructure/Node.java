package dataStructure;

/**
 * Description: 节点类
 * <p>
 * Created by lzm on 2017/9/26.
 */
public class Node<T> {


    private Node pre;

    private Node next;

    private T data;

    public Node(T data) {
        this.data = data;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Object getValue() {
        return data;
    }

    public void setValue(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "pre=" + pre +
                ", next=" + next +
                ", data=" + data +
                '}';
    }
}
