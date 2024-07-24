import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class URArrayList<E> implements URList<E> {


    private E[] array;
    private int size; // stored elements
    private int capacity; // total capacity


    // Constructor
    public URArrayList() {
        capacity = 4;
        size = 0;
        array = (E[]) new Object[capacity];
    }


    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }


    public void grow() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }


    // Appends the specified element to the end of this list
    @Override
    public boolean add(E e) {
        if (size == capacity) {
            grow();
        }
        array[size] = e;
        size++;
        return true;
    }


    // Inserts the specified element at the specified position in this list
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (size == capacity) {
            grow();
        }
        // for loop to shift the elements
        for(int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }


    // Appends all of the elements in the specified collection to the end of this list,
    // in the order that they are returned by the specified collection's iterator
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E e : c) {
            add(e);
        }
        return true;
    }


    // Inserts all of the elements in the specified collection into this list
    // at the specified position
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        int currentIndex = index;
        for(E e : c) {
            add(currentIndex, e);
            currentIndex++;
        }
        return true;
    }


    // Removes all of the elements from this list
    @Override
    public void clear() {
        size = 0;
        capacity = 4; // this is not necessary
        array = (E[]) new Object[capacity]; // reallocate the array memory
    }


    @Override
    public boolean contains(Object o) {
        // Returns true if this list contains the specified element.
        for(int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        // Returns true if this list contains all of the elements of the specified collection
        for(Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public E get(int index) {
        checkIndex(index);
        // Returns the element at the specified position in this list.
        return array[index];
    }


    @Override
    public int indexOf(Object o) {
        // Returns the index of the first occurrence of the specified element in this list,
        // or -1 if this list does not contain the element.
        for(int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        // element not found
        return -1;
    }


    @Override
    public boolean isEmpty() {
        // Returns true if this list contains no elements.
        return size == 0;
    }


    @Override
    public Iterator<E> iterator() {
        // Returns an iterator over the elements in this list in proper sequence.
        return new ArrayIterator();
    }


    private class ArrayIterator implements Iterator<E> {
        private int currentIndex;

        public ArrayIterator() {
            currentIndex = 0;
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex++];
        }
    }


    @Override
    public boolean equals(Object o) {
        // Compares the specified object with this list for equality.
        // Returns true if both contain the same elements. Ignore capacity
        URArrayList<E> o_list = (URArrayList<E>) o;
        // if the length is not equal, return false
        if (this.size != o_list.size) {
            return false;
        }
        for(int i = 0; i < size; i++) {
            if (this.get(i) != o_list.get(i)) {
                return false;
            }
        }
        return true;
    }


    // Removes the element at the specified position in this list
    @Override
    public E remove(int index) {
        checkIndex(index);
        E element = array[index];
        // for loop to shift the elements to the latter
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }


    // Removes the first occurrence of the specified element from this list,
    // if it is present
    @Override
    public boolean remove(Object o) {
        for(int i = 0; i < size; i++) {
            if(array[i].equals(o)){
                // for loop to shift the elements to the latter
                for(int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }


    // Removes from this list all of its elements that are contained
    //  in the specified collection
    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object o : c) {
            while(this.contains(o)) {
                this.remove(o);
            }
        }
        return true;
    }


    @Override
    public E set(int index, E element) {
        checkIndex(index);
        // Replaces the element at the specified position in this list
        // with the specified element
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }


    @Override
    public int size() {
        // Returns the number of elements in this list.
        return size;
    }


    // Returns a view of the portion of this list
    // between the specified fromIndex, inclusive, and toIndex, exclusive.
    @Override
    public URList<E> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);
        URList<E> subList = new URArrayList<E>();
        for(int i = fromIndex; i < toIndex; i++) {
            subList.add(array[i]);
        }
        return subList;
    }


    // Returns an array containing all of the elements in this list
    //  in proper sequence (from first to the last element).
    @Override
    public Object[] toArray() {
        // copy the array to a new array
        Object[] newArray = new Object[size];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }


    // Increases the capacity of this ArrayList instance, if necessary,
    // to ensure that it can hold at least the number of elements specified
    // by the minimum capacity argument.
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            capacity = minCapacity;
            // move the elements to the new array
            E[] newArray = (E[]) new Object[capacity];
            for(int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }


    // Returns the current capacity of the list
    int getCapacity() {
        return capacity;
    }
}
