package LRU;

import java.util.HashMap;

/**
 * Description:
 *
 * 最近最少使用--当需要向cache中添加新元素时,如果发现缓存已满,则移除最近最少使用的元素,并存入新元素
 *
 * 如何能快速找到使用最少的元素
 * 维护一个双向链表,每次被使用过的Node,移植头部,使用最少的Node逐渐会被移到队尾
 *
 * 如何实现快速查找
 * 将链表中的Node,维护在一个map<key,Node>用以实现时间复杂度为O(1)的查找
 *
 * 添加的逻辑
 * 当需要向cache中添加值时,先判定cache是否已满,如果已满,则将队尾的元素删除,并且将其在map中的映射一同删除,
 * 然后在向cache中添加新元素
 *
 * 查询的逻辑
 * 查询时,先判断是否存在,如果存在,将元素移至表头,并返回元素中的val
 *
 * 为何使用双向链表而不是单项链表
 * 因为单链表删除时的时间复杂度较高
 *
 * Created by lzm on 2019/11/18.
 */
public class LRUCache2 {

  private int capacity;

  private Node head;

  private Node tail;

  private HashMap<String, Node> map;

  public LRUCache2(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>(capacity);
  }


  private void moveToHead(Node node) {
    remove(node, false);
    setHead(node);
  }


  private void setHead(Node node) {
    if (head != null) {
      head.prev = node;
      node.next = head;
    }
    head = node;
    if (tail == null) {
      tail = node;
    }
  }

  private void remove(Node node, boolean removeFromMap) {

    if (node.prev != null) {
      node.prev.next = node.next;
    } else {
      head = node.next;
    }

    if (node.next != null) {
      node.next.prev = node.prev;
    } else {
      tail = node.prev;
    }

    node.next = null;
    node.prev = null;
    if (removeFromMap) {
      map.remove(node.key);
    }
  }

  public void put(String key, Object val) {
    //
    Node node = map.get(key);
    if (node != null) {
      node.val = val;
      moveToHead(node);
    } else {
      if (map.size() >= capacity) {
        remove(node, true);
      }
      node = new Node(key, val);
      setHead(node);
      map.put(key, node);
    }
  }

  public Object get(String key) {
    Node node = map.get(key);
    if (node != null) {
      moveToHead(node);
      return node.val;
    }
    return null;
  }

  @Override
  public String toString() {
    String map = "[";
    Node node = head;
    while (node != null) {
      map += "(" + node.key + ") ";
      node = node.next;
    }
    map += "]";
    return "LRUCache{" +
        "capacity=" + capacity +
        ", map=" + map +
        ", head=" + head +
        ", tail=" + tail +
        '}';
  }

  class Node {

    private Node prev;

    private Node next;

    private String key;

    private Object val;

    public Node(String key, Object val) {
      this.key = key;
      this.val = val;
    }

  }


  public static void main(String... args) {

    LRUCache2 cache = new LRUCache2(10);

    for (int i = 0; i < 10; i++) {
      cache.put(i + "", i);
      System.out.println(cache.toString());
    }
    System.out.println("---" + cache.toString());
    for (int i = 0; i < 9; i++) {
      cache.get(i + "");
    }
    System.out.println(cache.toString());

  }
}
