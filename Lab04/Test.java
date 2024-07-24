import java.util.Arrays;
import java.util.Iterator;


public class Test {

    public static <E> void assertURListEqual(URList<E> list, E[] arr) {
        if (list.size() != arr.length) {
            throw new AssertionError("Expected size " + arr.length + " but got " + list.size());
        }
        for(int i = 0; i < arr.length; i++) {
            if (!list.get(i).equals(arr[i])) {
                throw new AssertionError("Expected element " + arr[i] + " at index " + i + " but got " + list.get(i));
            }
        }
    }

    public static <E> void printList(URList<E> list) {
        System.out.print("[");
        for(int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


    public static void testLinkedList() {
        URLinkedList<Integer> lint1 = new URLinkedList<>();
        if(!lint1.isEmpty()) {
            throw new AssertionError("isEmpty Error");
        }
        // test the add method
        // basic add to tail
        lint1.add(1);
        lint1.add(2);
        lint1.add(3);
        lint1.add(4);
        if(lint1.isEmpty()) {
            throw new AssertionError("isEmpty Error");
        }
        Iterator<Integer> it1 = lint1.iterator();

        assertURListEqual(lint1, new Integer[]{1, 2, 3, 4});

        // add with index argument
        lint1.add(0, 0); // add to the first one
        assertURListEqual(lint1, new Integer[]{0, 1, 2, 3, 4});
        lint1.add(2, 5);
        assertURListEqual(lint1, new Integer[]{0, 1, 5, 2, 3, 4});

        // addAll
        lint1.addAll(Arrays.asList(6, 7, 8));
        assertURListEqual(lint1, new Integer[]{0, 1, 5, 2, 3, 4, 6, 7, 8});

        // addAll with index argument
        lint1.addAll(2, Arrays.asList(9, 10));
        assertURListEqual(lint1, new Integer[]{0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});

        lint1.addAll(0, Arrays.asList(-1, -2));
        assertURListEqual(lint1, new Integer[]{-1, -2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});


        // test remove
        // remove first one
        lint1.remove(0);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});

        // remove last one
        lint1.remove(lint1.size() - 1);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7});

        // remove inner one
        lint1.remove(3);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 10, 5, 2, 3, 4, 6, 7});

        lint1.add(2, 6);
        assertURListEqual(lint1, new Integer[]{-2, 0, 6, 1, 10, 5, 2, 3, 4, 6, 7});

        // test set
        lint1.set(0, -1);
        assertURListEqual(lint1, new Integer[]{-1, 0, 6, 1, 10, 5, 2, 3, 4, 6, 7});

        // test sublist
        URList<Integer> sublist = lint1.subList(2, 5);
        assertURListEqual(sublist, new Integer[]{6, 1, 10});

        // test toArray
        Object[] arr = lint1.toArray();
        // remove object: the specific element
        URLinkedList<Double> lint2 = new URLinkedList<>();
        lint2.add(1.0);
        lint2.add(2.0);
        lint2.add(3.0);
        lint2.add(2.0);
        lint2.add(4.0);
        assertURListEqual(lint2, new Double[]{1.0, 2.0, 3.0, 2.0, 4.0});

        lint2.remove(2.0);
        assertURListEqual(lint2, new Double[]{1.0, 3.0, 2.0, 4.0});

        lint2.remove(1.0);
        assertURListEqual(lint2, new Double[]{3.0, 2.0, 4.0});

        // remove all
        lint2.removeAll(Arrays.asList(2.0, 4.0));
        assertURListEqual(lint2, new Double[]{3.0});

        // test equals
        URLinkedList<Integer> lint3 = new URLinkedList<>();
        lint3.add(1);
        lint3.add(3);
        lint3.add(2);

        URLinkedList<Integer> lint4 = new URLinkedList<>();
        lint4.add(1);
        lint4.add(3);
        lint4.add(2);
        if(!lint3.equals(lint4)) {
            throw new AssertionError("Expected equals but got false");
        }

        // test the first and last ops
        lint4.addFirst(0);
        lint4.addLast(4);
        assertURListEqual(lint4, new Integer[]{0, 1, 3, 2, 4});

        // peek: retrieve but not remove
        if(lint4.peekFirst() != 0) {
            throw new AssertionError("Expected peekFirst 0 but got " + lint4.peekFirst());
        }
        if(lint4.peekLast() != 4) {
            throw new AssertionError("Expected peekLast 4 but got " + lint4.peekLast());
        }
        // poll: remove the first and last element
        if(lint4.pollFirst() != 0) {
            throw new AssertionError("Expected pollFirst 0 but got " + lint4.pollFirst());
        }
        if(lint4.pollLast() != 4) {
            throw new AssertionError("Expected pollLast 4 but got " + lint4.pollLast());
        }
        assertURListEqual(lint4, new Integer[]{1, 3, 2});



    }


    public static void testArrayList() {
        URArrayList<Integer> lint1 = new URArrayList<>();

        if(!lint1.isEmpty()) {
            throw new AssertionError("isEmpty Error");
        }
        // test the add method
        // basic add to tail
        lint1.add(1);
        lint1.add(2);
        lint1.add(3);
        lint1.add(4);
        assertURListEqual(lint1, new Integer[]{1, 2, 3, 4});
        if(lint1.isEmpty()) {
            throw new AssertionError("isEmpty Error");
        }

        // add with index argument
        lint1.add(0, 0); // add to the first one
        assertURListEqual(lint1, new Integer[]{0, 1, 2, 3, 4});
        lint1.add(2, 5);
        assertURListEqual(lint1, new Integer[]{0, 1, 5, 2, 3, 4});

        // addAll
        lint1.addAll(Arrays.asList(6, 7, 8));
        assertURListEqual(lint1, new Integer[]{0, 1, 5, 2, 3, 4, 6, 7, 8});

        // addAll with index argument
        lint1.addAll(2, Arrays.asList(9, 10));
        assertURListEqual(lint1, new Integer[]{0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});

        lint1.addAll(0, Arrays.asList(-1, -2));
        assertURListEqual(lint1, new Integer[]{-1, -2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});


        // test remove
        // remove first one
        lint1.remove(0);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7, 8});

        // remove last one
        lint1.remove(lint1.size() - 1);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 9, 10, 5, 2, 3, 4, 6, 7});

        // remove inner one
        lint1.remove(3);
        assertURListEqual(lint1, new Integer[]{-2, 0, 1, 10, 5, 2, 3, 4, 6, 7});

        lint1.add(2, 6);
        assertURListEqual(lint1, new Integer[]{-2, 0, 6, 1, 10, 5, 2, 3, 4, 6, 7});

        // test set
        lint1.set(0, -1);
        assertURListEqual(lint1, new Integer[]{-1, 0, 6, 1, 10, 5, 2, 3, 4, 6, 7});

        // test sublist
        URList<Integer> sublist = lint1.subList(2, 5);
        assertURListEqual(sublist, new Integer[]{6, 1, 10});

        // test toArray
        Object[] arr = lint1.toArray();
        // remove object: the specific element
        URArrayList<Double> lint2 = new URArrayList<>();
        lint2.add(1.0);
        lint2.add(2.0);
        lint2.add(3.0);
        lint2.add(2.0);
        lint2.add(4.0);
        assertURListEqual(lint2, new Double[]{1.0, 2.0, 3.0, 2.0, 4.0});

        lint2.remove(2.0);
        assertURListEqual(lint2, new Double[]{1.0, 3.0, 2.0, 4.0});

        lint2.remove(1.0);
        assertURListEqual(lint2, new Double[]{3.0, 2.0, 4.0});

        // remove all
        lint2.removeAll(Arrays.asList(2.0, 4.0));
        assertURListEqual(lint2, new Double[]{3.0});

        // test equals
        URArrayList<Integer> lint3 = new URArrayList<>();
        lint3.add(1);
        lint3.add(3);
        lint3.add(2);

        URArrayList<Integer> lint4 = new URArrayList<>();
        lint4.add(1);
        lint4.add(3);
        lint4.add(2);

        // capacity operations
        URArrayList<Integer> lint5 = new URArrayList<>();
        lint5.add(1);
        lint5.ensureCapacity(1000);
        if (lint5.getCapacity() != 1000) {
            throw new AssertionError("Expected capacity 1000 but got " + lint5.getCapacity());
        }

    }

    public static void main(String[] args) {
        testLinkedList();
        testArrayList();
    }
}
