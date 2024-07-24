// Zhenhao Zhang zzh133@u.rochester.edu

import java.util.NoSuchElementException;

abstract public class UR_BST<Key extends Comparable<Key>, Value> {
    UR_Node root; // root of BST

    private class UR_Node {
        private Key key; // sorted by key
        private Value val; // associated data
        private UR_Node left, right; // left and right subtrees
        private int size; // number of nodes in subtree
    }

    abstract public boolean isEmpty();

    abstract public int size();

    /**
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    abstract public boolean contains(Key key);

    /**
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    abstract public Value get(Key key);

    /**
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    abstract public void put(Key key, Value val);

    /**
     * @throws NoSuchElementException if the symbol table is empty
     */
    abstract public void deleteMin();

    /**
     * @throws NoSuchElementException if the symbol table is empty
     */
    abstract public void deleteMax();

    /** @throws IllegalArgumentException if {@code key} is {@code null} */
    abstract public void delete(Key key) ;
    abstract public Iterable<Key> keys();
    abstract public int height() ;
    /**
     * Returns the keys in the BST in level order (for debugging).
     * @return the keys in the BST in level order traversal
     * usually requires a supplemental Queue
     * include this in your test case
     */
    abstract public Iterable<Key> levelOrder() ;

}