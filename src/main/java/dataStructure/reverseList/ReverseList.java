package dataStructure.reverseList;

/**
 * Create on 2020/11/23
 */
public class ReverseList {

    public static Node reverse(Node head) {
        if (head == null || head.after == null) {
            return head;
        }
        Node temp = head.after;
        Node newHead = reverse(temp);
        temp.after = head;
        head.after = null;
        return newHead;
    }

    public static class Node {

        private Node after;

        private Integer value;

        public Node() {

        }

        public Node(Integer x) {

        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node temp = this;
            while (temp != null) {
                sb.append(temp.value).append(",");
                temp = temp.after;
            }
            return sb.toString();
        }

    }

    public static void main(String... args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        head.after = node1;
        node1.after = node2;
        node2.after = node3;
        reverse(head);
        System.out.println(node3);
    }

}