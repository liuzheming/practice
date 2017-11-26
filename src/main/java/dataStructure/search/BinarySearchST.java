package dataStructure.search;

import dataStructure.MyArrayList;

import java.util.Iterator;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/25.
 */
public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {


    private K[] keys;

    private V[] vals;

    private int N;


    public BinarySearchST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Comparable[capacity];
    }

    @Override
    public void put(K key, V val) {
        if (keys.length == N) ensureCapacity(N * 2);
        int before = rank(key);
        if (before < N && keys[before].compareTo(key) == 0) {
            vals[before] = val;
            return;
        }
        for (int i = N; i >= before; i--) {
            keys[i + 1] = keys[i];
            vals[i + 1] = vals[i];
        }
        keys[before] = key;
        vals[before] = val;
        N++;
    }

    @Override
    public V get(K key) {
        int x = rank(key);
        if (x > 0 && x < N) return vals[x];
        else return null;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public boolean contains(K key) {
        int x = rank(key);
        return x > 0 && x < N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public K max() {
        return keys[N - 1];
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K floor(K key) {
        int x = rank(key);
        return null;
    }

    @Override
    public K ceiling(K key) {
        return null;
    }

    @Override
    public int rank(K key) {
        int mid, cmp;
        int lo = 0, hi = N - 1;
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
        return keys[idx];
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
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
        if (newCapacity < N) return;
        K[] oldKeys = keys;
        V[] oldVals = vals;
        keys = (K[]) new Comparable[newCapacity];
        vals = (V[]) new Comparable[newCapacity];
        for (int i = 0; i < N; i++) {
            keys[i] = oldKeys[i];
            vals[i] = oldVals[i];
        }
    }
}
