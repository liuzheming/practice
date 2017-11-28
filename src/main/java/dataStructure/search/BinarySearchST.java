package dataStructure.search;

import dataStructure.MyArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/25.
 */
public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {


    private K[] keys;

    private V[] vals;

    private int n;

    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Comparable[capacity];
    }

    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("first argument to put is null!");

        if (val == null) {
            delete(key);
            return;
        }
        int x = rank(key);
        // key is already in table
        if (x < n && keys[x].compareTo(key) == 0) {
            vals[x] = val;
            return;
        }
        // insert new key-val pair
        if (keys.length == n) ensureCapacity(n * 2);
        for (int i = n; i >= x; i--) {
            keys[i + 1] = keys[i];
            vals[i + 1] = vals[i];
        }
        keys[x] = key;
        vals[x] = val;
        n++;
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null!");
        if (isEmpty()) return null;
        int x = rank(key);
        if (x < n && keys[x].compareTo(key) == 0) return vals[x];
        else return null;
    }

    @Override
    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null!");
        if (isEmpty()) return;

        // compute rank
        int x = rank(key);
        // 没找到
        if (x == n || keys[x].compareTo(key) != 0) {
            return;
        }
        // 找到了
        for (int i = x; i < n - 1; i++) {
            keys[i] = keys[i + 1];
            vals[i] = vals[i + 1];
        }
        keys[n] = null;
        vals[n] = null;
        // 总数-1
        n--;
        if (n <= keys.length / 4) ensureCapacity(keys.length / 2);
    }

    @Override
    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null!");
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public K max() {
        return keys[n - 1];
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K floor(K key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null!");
        int x = rank(key);
        if (x < n && keys[x].compareTo(key) == 0) return keys[x];
        if (x == 0) return null;
        else return keys[x - 1];
    }

    @Override
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null!");
        int x = rank(key);
        if (x == n) return null;
        else return keys[x];
    }

    @Override
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null!");
        int mid, cmp;
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            cmp = key.compareTo(keys[mid]);
            if (cmp > 0) lo = mid + 1;
            if (cmp < 0) hi = mid - 1;
            if (cmp == 0) return mid;
        }
        return lo;
    }

    private int rank2(K key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = (lo + hi) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp > 0)
            return rank2(key, mid + 1, hi);
        else if (cmp < 0)
            return rank2(key, lo, mid - 1);
        return mid;
    }

    @Override
    public K select(int idx) {
        if (idx <= 0 || idx >= size())
            throw new IllegalArgumentException("called select() with invalid argument");
        return keys[idx];
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    @Override
    public int size(K lo, K hi) {
        if (lo.compareTo(hi) > 0)
            return 0;
        else if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

    @Override
    public Iterator<K> keys(K lo, K hi) {
        MyArrayList<K> list = new MyArrayList<>();
        for (int i = rank(lo); i < rank(hi); i++)
            list.add(keys[i]);
        if (contains(hi)) list.add(keys[rank(hi)]);
        return list.iterator();
    }

    @Override
    public Iterator keys() {
        return keys(min(), max());
    }

    private void ensureCapacity(int newCapacity) {
        K[] oldKeys = keys;
        V[] oldVals = vals;
        keys = (K[]) new Comparable[newCapacity];
        vals = (V[]) new Comparable[newCapacity];
        for (int i = 0; i < n; i++) {
            keys[i] = oldKeys[i];
            vals[i] = oldVals[i];
        }
    }
}
