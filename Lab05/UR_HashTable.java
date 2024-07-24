// Zhenhao Zhang zzh133@u.rochester.edu


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class UR_HashTable<Key, Value> implements Iterable<Key>{
    private static final int INIT_CAPACITY = 5;
    private int m;  // size of the hash table
    private int n;  // number of key-value pairs
    private Key[] keys;
    private Value[] vals;

    public UR_HashTable() {
        this(INIT_CAPACITY);
    }

    // constructor
    public UR_HashTable(int cap) {
        m = cap;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }


    public void put(Key key, Value val) {
        // if the size of the elements is greater than half of the size of the hash table,
        // double the size of the hash table
        if (n >= m / 2) resize(2 * m);

        int i; // find the position to insert the key-value pair
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                // key already exists, update the value
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        // linear probing
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        // not contain, return
        if (!contains(key)) return;

        // find the position of the key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete the key-value pair
        keys[i] = null;
        vals[i] = null;

        // do rehashing, in order to keep the hash table continuous
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }
        // update the size of the hash table
        n--;

        if (n > 0 && n <= m/8) resize(m/2);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

//    public Iterable<Key> keys() {
//        // return the iterable keys
//        Queue<Key> queue = new LinkedList<>();
//        for (int i = 0; i < m; i++) {
//            if (keys[i] != null) {
//                queue.add(keys[i]);
//            }
//        }
//        return queue;
//    }

    // Useful helpers
    private int hash(Key key) {
        return Math.abs(key.hashCode() % m);
    }

    private void resize(int capacity) {
        UR_HashTable<Key, Value> temp = new UR_HashTable<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    @Override
    public Iterator<Key> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<Key> {
        private int i = 0;
        private Queue<Key> queue = new LinkedList<>();
        public HashTableIterator() {
            for (int i = 0; i < m; i++) {
                if (keys[i] != null) {
                    queue.add(keys[i]);
                }
            }
        }
        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Key next() {
            return queue.poll();
        }
    }
}
