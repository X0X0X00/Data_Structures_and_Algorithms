import java.util.*;

public class URLinkedListTest {

    public static void main(String[] args) {
        URLinkedList<Integer> list = new URLinkedList<>();

        // Testing add(E e) method
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Add Elements: " + Arrays.toString(list.toArray())); // Expected Output: [1, 2, 3]


        // Test size()
        System.out.println("Size: " + list.size()); // Expected: 3

        // Testing add(int index, E element) method
        list.add(1, 4);
        System.out.println("Add Element at Index: " + Arrays.toString(list.toArray())); // Expected Output: [1, 4, 2, 3]

        // Test get(int index)
        System.out.println("Element at index 1: " + list.get(1)); // Expected: X

        // Test iterator()
        System.out.print("List elements: ");
        for (Integer s : list) {
            System.out.print(s + " "); // Expected: A X B C
        }
        System.out.println();

        // Testing addFirst(E e) method
        list.addFirst(0);
        System.out.println("Add First: " + Arrays.toString(list.toArray())); // Expected Output: [0, 1, 4, 2, 3]

        // Testing addLast(E e) method
        list.addLast(5);
        System.out.println("Add Last: " + Arrays.toString(list.toArray())); // Expected Output: [0, 1, 4, 2, 3, 5]

        // Testing peekFirst() method
        System.out.println("Peek First: " + list.peekFirst()); // Expected Output: 0

        // Testing peekLast() method
        System.out.println("Peek Last: " + list.peekLast()); // Expected Output: 5

        // Testing pollFirst() method
        System.out.println("Poll First: " + list.pollFirst()); // Expected Output: 0
        System.out.println("After Poll First: " + Arrays.toString(list.toArray())); // Expected Output: [1, 4, 2, 3, 5]

        // Testing pollLast() method
        System.out.println("Poll Last: " + list.pollLast()); // Expected Output: 5
        System.out.println("After Poll Last: " + Arrays.toString(list.toArray())); // Expected Output: [1, 4, 2, 3]

        // Testing remove(int index) method
        list.remove(1);
        System.out.println("After Removing Index 1: " + Arrays.toString(list.toArray())); // Expected Output: [1, 2, 3]

        // Testing remove(Object o) method
        list.remove((Integer) 2);
        System.out.println("After Removing Object 2: " + Arrays.toString(list.toArray())); // Expected Output: [1, 3]

        // Testing size() method
        System.out.println("Size: " + list.size()); // Expected Output: 2

        // Testing get(int index) method
        System.out.println("Get Index 1: " + list.get(1)); // Expected Output: 3

        // Testing set(int index, E element) method
        list.set(1, 6);
        System.out.println("After Set Index 1 to 6: " + Arrays.toString(list.toArray())); // Expected Output: [1, 6]

        // Testing contains(Object o) method
        System.out.println("Contains 6: " + list.contains(6)); // Expected Output: true
        System.out.println("Contains 2: " + list.contains(2)); // Expected Output: false

        // Testing equals(Object o) method
        URLinkedList<Integer> anotherList = new URLinkedList<>();
        anotherList.add(1);
        anotherList.add(6);
        System.out.println("Equals Another List: " + list.equals(anotherList)); // Expected Output: true

        // Testing clear() method
        list.clear();
        System.out.println("After Clear: " + Arrays.toString(list.toArray())); // Expected Output: []

        // Testing isEmpty() method
        System.out.println("Is Empty: " + list.isEmpty()); // Expected Output: true
    }
}
