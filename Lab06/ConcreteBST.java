// Zhenhao Zhang zzh133@u.rochester.edu


import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ConcreteBST<Key extends Comparable<Key>, Value> extends UR_BST<Key, Value> {

    private UR_Node root; // root of BST

    private class UR_Node {
        private Key key;
        private Value val;
        private UR_Node left, right;
        private int size;

        UR_Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }


    public ConcreteBST() {
        root = null;
    }

    // BST ctor with a root node
    public ConcreteBST(Key key, Value val) {
        root = new UR_Node(key, val, 1);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(UR_Node x) {
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(key) != null;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(root, key);
    }

    private Value get(UR_Node x, Key key) {
        if (x == null) return null;
        // compare the key with the root node's key
        int cmp = key.compareTo(x.key);
        // if the key is less than the root node's key, go to the left subtree
        if (cmp < 0) return get(x.left, key);
        // if the key is greater than the root node's key, go to the right subtree
        else if (cmp > 0) return get(x.right, key);
        // if the key is equal to the root node's key, return the value
        else return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private UR_Node put(UR_Node x, Key key, Value val) {
        if (x == null) return new UR_Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST is empty");
        root = deleteMin(root);
    }

    private UR_Node deleteMin(UR_Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST is empty");
        root = deleteMax(root);
    }

    private UR_Node deleteMax(UR_Node x) {
        // delete the maximum node and return the root of the new tree
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int sizeBefore = size();
        root = delete(root, key);
        if(sizeBefore == size()) {
            throw new NoSuchElementException("Key not found: " + key);
        }
    }

    private UR_Node delete(UR_Node x, Key key) {
        // if the node is null, nothing to delete
        if (x == null) {
            return null;
        }
        // compare the key with the root node's key
        int cmp = key.compareTo(x.key);
        // if the key is less than the root node's key, go to the left subtree
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        // if the key is greater than the root node's key, go to the right subtree
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        // if the key is equal to the root node's key, delete the node
        else {
            // if the node has only one child, return the child
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            // if the node has two children, replace the node with its successor
            UR_Node t = x;
            x = findMin(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        // update the size of the node
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(root);
    }

    private Iterable<Key> keys(UR_Node x) {
        // add the key to the queue
        return levelOrder();
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(UR_Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    @Override
    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new LinkedList<>();
        Queue<UR_Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            UR_Node x = queue.poll();
            if (x == null) continue;
            keys.add(x.key);
            queue.add(x.left);
            queue.add(x.right);
        }
        return keys;
    }

    private UR_Node findMin(UR_Node x) {
        if (x.left == null) return x;
        else return findMin(x.left);
    }

    private UR_Node findMax(UR_Node x) {
        if (x.right == null) return x;
        else return findMax(x.right);
    }
}
