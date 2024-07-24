/*

Zhenhao Zhang
zzh133@u.rochester.edu
32277234

 */

// Generic Queue class
public class MyQueue<T> {

	private MyList<T> list;

	public MyQueue() {

		list = new MyList<T>();

	}


	public void enqueue(T item) {

		// add to the end of the list
		list.add(item);

	}
	public T dequeue() {

		// remove from the front of the list
		return list.remove(0);

	}

	public boolean isEmpty() {

		return list.isEmpty();

	}

	public int size() {

		return list.size();

	}

	public String toString() {

		return list.toString();

	}

	// Test
	public static void main(String[] args) {
		MyQueue<String> queue = new MyQueue<String>();
		queue.enqueue("a");
		queue.enqueue("b");
		queue.enqueue("c");
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
	}
}