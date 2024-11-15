import java.util.Collection;
import java.util.Iterator;

// URList class ADT. Generalize the element type using Java Generics.
public interface URList<E>  extends Iterable<E>{ // URList class ADT

	// Appends the specified element to the end of this list 
	boolean add(E e);

	// Inserts the specified element at the specified position in this list 
	void add(int index, E element);

	// Appends all of the elements in the specified collection to the end of this list,
	// in the order that they are returned by the specified collection's iterator 
	boolean    addAll(Collection<? extends E> c);

	// Inserts all of the elements in the specified collection into this list 
	// at the specified position
	boolean addAll(int index, Collection<? extends E> c);

	// Removes all of the elements from this list 
	void clear();

	// Returns true if this list contains the specified element.
	boolean    contains(Object o);

	// Returns true if this list contains all of the elements of the specified collection
	boolean    containsAll(Collection<?> c);

	// Compares the specified object with this list for equality. 
	// Returns true if both contain the same elements. Ignore capacity
	boolean    equals(Object o);

	// Returns the element at the specified position in this list.
	E get(int index);

	// Returns the index of the first occurrence of the specified element in this list, 
	// or -1 if this list does not contain the element.
	int indexOf(Object o);

	// Returns true if this list contains no elements.
	boolean    isEmpty();

	// Returns an iterator over the elements in this list in proper sequence.
	Iterator<E> iterator();

	// Removes the element at the specified position in this list 
	E remove(int index);

	// Removes the first occurrence of the specified element from this list,
	// if it is present 
	boolean    remove(Object o);

	// Removes from this list all of its elements that are contained
	//  in the specified collection
	boolean    removeAll(Collection<?> c);

	// Replaces the element at the specified position in this list
	// with the specified element 
	E set(int index, E element);

	// Returns the number of elements in this list.
	int size();

	// Returns a view of the portion of this list 
	// between the specified fromIndex, inclusive, and toIndex, exclusive.
	URList<E> subList(int fromIndex, int toIndex);


	// Returns an array containing all of the elements in this list
	//  in proper sequence (from first to the last element).
	Object[] toArray();


}
