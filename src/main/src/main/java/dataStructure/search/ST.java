package dataStructure.search;

import java.util.Iterator;

/**
 * Description: 符号表(键值对表) : search table API
 * <p>
 * Created by lzm on 2017/11/25.
 */
public interface ST<K extends Comparable<K>, V> {


    int size();                 // 返回表中键值对的个数

    boolean isEmpty();          // 表是否为空

    void put(K key, V val);     // 将键值对存入表中(若值为空,则为将键从表中删除)

    V get(K key);               // 获取键key的对应值

    void delete(K key);         // 从表中删去键key

    boolean contains(K key);    // 键key是否存在于表中

    K max();                    // 最大的键

    K min();                    // 最小的键

    K floor(K key);             // 小于等于key的最大键

    K ceiling(K key);           // 大于等于key的最小键

    int rank(K key);            // 小于key的键的数量

    K select(int idx);          // 排名为idx的键

    void deleteMin();           // 删除最小值

    void deleteMax();           // 删除最大值

    int size(K lo, K hi);       // 返回[lo,hi]之间键的个数

    Iterator<K> keys(K lo, K hi);   // 返回[lo,hi]之间的键,已排序

    Iterator keys();            // 返回所有键的集合,已排序


}
