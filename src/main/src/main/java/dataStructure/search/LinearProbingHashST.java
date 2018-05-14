package dataStructure.search;

/**
 * Description:
 * <p>
 * Created by lzm on 2017/11/27.
 */
public class LinearProbingHashST<K, V> {

    private int n;      // 键值对的个数
    private int m = 16; // 线性探测表的大小
    private K[] keys;   // 键
    private V[] vals;   // 值

    public LinearProbingHashST() {
        keys = (K[]) new Object[m];
        vals = (V[]) new Object[m];
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        K[] newKeys = (K[]) new Object[capacity];
        V[] newVals = (V[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            newKeys[i] = keys[i];
            newVals[i] = vals[i];
        }
        keys = newKeys;
        vals = newVals;
        m = capacity;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(K key, V val) {
        if (n >= m / 2) resize(m * 2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public V get(K key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) return vals[i];
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void delete(K key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % m;
        while (keys[i] != null) {
            K keyToRedo = keys[i];
            V valToRedo = vals[i];
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n == m / 8) resize(m / 2);
    }

}
