// Zhenhao Zhang zzh133@u.rochester.edu



public class TestBST {
    public static void main(String[] args) {
        ConcreteBST<Integer, String> bst1 = new ConcreteBST<>();

        // Test put and size
        bst1.put(5, "five");
        System.out.println("Size after adding one element: " + bst1.size());

        // Test get
        System.out.println("Value of key 5: " + bst1.get(5));

        // Test contains
        System.out.println("Contains key 5: " + bst1.contains(5));

        // Test exception handling
        try {
            bst1.put(null, "nullKey");
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        // build a tree
        bst1.put(3, "three");
        bst1.put(7, "seven");
        bst1.put(6, "six");
        bst1.put(1, "one");
        bst1.put(2, "two");
        bst1.put(4, "four");
        bst1.put(9, "nine");
        // Tree:
        //          5
        //        /   \
        //       3     7
        //      / \   / \
        //     1   4 6   9
        //      \
        //       2

        // size: 8
        System.out.println("Size after adding 7 more elements: " + bst1.size());
        // Test levelOrder traversal
        System.out.println("Levelorder traversal: " + bst1.levelOrder());

        // Test update
        bst1.put(2, "two-new");
        System.out.println("New Value of key 2: " + bst1.get(2));

        // Test DeleteMax
        bst1.deleteMax();
        System.out.println("Size after deleting max: " + bst1.size());
        // Do levelOrder traversal to verify
        System.out.println("Levelorder traversal after deletemax: " + bst1.levelOrder());

        // Test DeleteMin
        bst1.deleteMin();
        System.out.println("Size after deleting min: " + bst1.size());
        // Do levelOrder traversal to verify
        System.out.println("Levelorder traversal after deletemin: " + bst1.levelOrder());

        // Tree:
        //          5
        //        /   \
        //       3     7
        //      / \   /
        //     2   4 6

        // Test delete
        bst1.delete(3);
        // Levelorder traversal: 5 4 7 2 6
        System.out.println("Levelorder traversal after delete 3: " + bst1.levelOrder());

        // delete root
        bst1.delete(5);
        // Levelorder traversal: 6 4 7 2
        System.out.println("Levelorder traversal after delete 5: " + bst1.levelOrder());

        System.out.println("Size after deleting one element: " + bst1.size());


        // Test different params
        ConcreteBST<String, Integer> bst2 = new ConcreteBST<>();
        bst2.put("five", 5);
        bst2.put("six", 6);
        System.out.println("Size after adding one element: " + bst2.size());

        // Test different ctor
        ConcreteBST<Integer, String> bst3 = new ConcreteBST<>(1, "one");
        System.out.println("Size after adding one element: " + bst3.size());

    }
}
