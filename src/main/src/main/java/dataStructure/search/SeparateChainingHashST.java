package dataStructure.search;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/27.
 */
public class SeparateChainingHashST<K, V> {

    private int n;  // 键值对总数

    private int m;  // 散列表大小

    private SeparatialSearchST<K, V>[] st;  // 存放链表对象的数组

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = new SeparatialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SeparatialSearchST<>();
    }


    private int hash(K key) {
        return (key.hashCode() & 0xfffffff) % m;
    }

    public V get(K key) {
        return st[hash(key)].get(key);
    }


    public void put(K key, V val) {
        st[hash(key)].put(key, val);
        n++;
    }

    public void delete(K key) {
        st[hash(key)].delete(key);
        n--;
    }

    public int size() {
        return n;
    }


}
