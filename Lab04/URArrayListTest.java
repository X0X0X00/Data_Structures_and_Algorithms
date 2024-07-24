import java.util.*;

public class URArrayListTest {

    public static void main(String[] args) {
        URArrayList<Integer> list = new URArrayList<>();

        // Testing add(E e) method
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Add Elements: " + Arrays.toString(list.toArray()));

        // Testing add(int index, E element) method
        list.add(1, 4);
        System.out.println("Add Element at Index: " + Arrays.toString(list.toArray()));

        // Testing addAll(Collection<? extends E> c) method
        List<Integer> additionalElements = Arrays.asList(7, 8, 9);
        list.addAll(additionalElements);
        System.out.println("Add All Elements: " + Arrays.toString(list.toArray()));

        // Testing addAll(int index, Collection<? extends E> c) method
        list.addAll(2, additionalElements);
        System.out.println("Add All Elements at Index: " + Arrays.toString(list.toArray()));

        // Testing ensureCapacity(int minCapacity) method
        list.ensureCapacity(20);
        System.out.println("Ensure Capacity: " + list.getCapacity());

        // Testing remove(int index) method
        list.remove(1);
        System.out.println("After Removing Index 1: " + Arrays.toString(list.toArray()));

        // Testing remove(Object o) method
        list.remove((Integer) 7);
        System.out.println("After Removing Object 7: " + Arrays.toString(list.toArray()));

        // Testing removeAll(Collection<?> c) method
        list.removeAll(Arrays.asList(8, 9));
        System.out.println("After Removing All [8, 9]: " + Arrays.toString(list.toArray()));

        // Testing size() method
        System.out.println("Size: " + list.size());

        // Testing get(int index) method
        System.out.println("Get Index 1: " + list.get(1));

        // Testing set(int index, E element) method
        list.set(1, 6);
        System.out.println("After Set Index 1 to 6: " + Arrays.toString(list.toArray()));

        // Testing contains(Object o) method
        System.out.println("Contains 6: " + list.contains(6));
        System.out.println("Contains 2: " + list.contains(2));

        // Testing containsAll(Collection<?> c) method
        System.out.println("Contains All [1, 6]: " + list.containsAll(Arrays.asList(1, 6)));

        // Testing equals(Object o) method
        URArrayList<Integer> anotherList = new URArrayList<>();
        anotherList.add(1);
        anotherList.add(6);
        System.out.println("Equals Another List: " + list.equals(anotherList));

        // Testing subList(int fromIndex, int toIndex) method
        URList<Integer> subList = list.subList(0, 1);
        System.out.println("Sub List [0, 1]: " + Arrays.toString(subList.toArray()));

        // Testing iterator() method
        Iterator<Integer> iterator = list.iterator();
        System.out.print("Iterator: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // Testing clear() method
        list.clear();
        System.out.println("After Clear: " + Arrays.toString(list.toArray()));

        // Testing isEmpty() method
        System.out.println("Is Empty: " + list.isEmpty());
    }
}
