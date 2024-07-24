// Zhenhao Zhang zzh133@u.rochester.edu


public class UR_MyHeap<T extends Comparable<T>> implements UR_Heap<T>{
	private static final int DEFAULT_CAPACITY = 10;

	// use array to store the heap
	private T[] heap;
	// the number of elements in the heap
	private int size;

	// default ctor
	public UR_MyHeap() {
		this(DEFAULT_CAPACITY);
	}
	// ctor with initial capacity
	public UR_MyHeap(int capacity) {
		heap = (T[]) new Comparable[capacity];
		size = 0;
	}

//	Implement a third constructor to your heap class that can take an array of comparable types and turn them into a heap by re-arranging the elements.
	public UR_MyHeap(T[] array) {
		heap = (T[]) new Comparable[array.length];
		size = 0;
		// copy the array
		for (int i = 0; i < array.length; i++) {
			heap[i] = array[i];
			size++;
		}
		heapify();
	}
	//	Write a heapify() method that performs the operation by swapping array elements.
	private void heapify() {
		// start from the last non-leaf node
		int index = size / 2 - 1;
		while (index >= 0) {
			bubbleDown(index);
			index--;
		}
	}

	public void bubbleUp(int index) {
		int parentIndex = (index - 1) / 2;

		while (index > 0 && heap[parentIndex].compareTo(heap[index]) > 0) {
			// Swap elements if parent is greater
			T temp = heap[parentIndex];
			heap[parentIndex] = heap[index];
			heap[index] = temp;
			// move up
			index = parentIndex;
			// update parent index
			parentIndex = (index - 1) / 2;
		}
	}

	@Override
	public void insert(T item) {
		// handle overflow
		if (size == heap.length) {
			throw new RuntimeException("Heap overflow");
		}
		// insert the new item at the end of the heap
		heap[size] = item;
		// bubble up the new item
		bubbleUp(size);
		// increase the size
		size++;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public void bubbleDown(int index) {
		int leftChildIndex = 2 * index + 1;
		int rightChildIndex = 2 * index + 2;
		int minIndex = index;

		// find the min index
		if (leftChildIndex < size && heap[leftChildIndex].compareTo(heap[minIndex]) < 0) {
			minIndex = leftChildIndex;
		}
		if (rightChildIndex < size && heap[rightChildIndex].compareTo(heap[minIndex]) < 0) {
			minIndex = rightChildIndex;
		}

		// swap if necessary
		if (minIndex != index) {
			T temp = heap[index];
			heap[index] = heap[minIndex];
			heap[minIndex] = temp;
			// bubble down the min item
			bubbleDown(minIndex);
		}
	}

	@Override
	public T deleteMin() {
		// handle underflow
		if (isEmpty()) {
			throw new RuntimeException("Heap underflow");
		}
		// get the min item
		T min = heap[0];
		// move the last item to the root
		heap[0] = heap[size - 1];
		// decrease the size
		size--;
		// bubble down the root
		bubbleDown(0);
		// return the min item
		return min;
	}

	public void printHeap() {
		// print the heap into the console, do not print the null value
		// print the heap level by level
		int level = 0;
		int levelSize = 1;
		int printed = 0;
		while (printed < size) {
			for (int i = 0; i < levelSize && printed < size; i++) {
				System.out.print(heap[levelSize + i - 1] + " ");
				printed++;
			}
			System.out.println();
			level++;
			levelSize = (int) Math.pow(2, level);
		}
	}

}
