package LRU;

import com.oracle.tools.packager.Log;
import java.util.HashMap;

/**
 * Description:  Least Recently Used
 *
 * Created by lzm on 2019/11/18.
 */
public class LRUCache {


  private int capacity;

  private HashMap<String, Node> map;

  private Node head;

  private Node tail;


  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>();
  }


  public void put(String key, Object val) {
    Node node = map.get(key);
    if (node != null) {
      // 如果已存在,替换原val,并将node移动到head位置
      node.val = val;
      remove(node, false);
    } else {
      // 如果不存在,容量是否已满
      if (map.size() >= capacity) {
        remove(tail, true);
      }
      // 创建一个node,并将其置于队头,同时放入map中
      node = new Node(key, val);
      map.put(key, node);
    }
    setHead(node);
  }

  public Object get(String key) {
    Node node = map.get(key);
    if (node != null) {
      remove(node, false);
      setHead(node);
      return node.val;
    }
    return null;
  }

  public void setHead(Node node) {
    if (head != null) {
      node.next = head;
      head.prev = node;
    }
    head = node;
    if (tail == null) {
      tail = node;
    }
  }

  public void remove(Node node, boolean flag) {
    if (node.prev != null) {  // 如果不是队头
      node.prev.next = node.next;
    } else {
      head = node.next;
    }
    if (node.next != null) {  // 如果不是队尾
      node.next.prev = node.prev;
    } else {
      tail = node.prev;
    }
    node.next = null;
    node.prev = null;
    if (flag) {
      map.remove(node.key);
    }
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

    private Node(String key, Object val) {
      this.key = key;
      this.val = val;
    }

  }


  public static void main(String... args) {

    LRUCache cache = new LRUCache(10);

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

