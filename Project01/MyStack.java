/*

Zhenhao Zhang
zzh133@u.rochester.edu
32277234

 */



// Generic Queue class
public class MyStack<T> {

	private MyList<T> list;

	public MyStack() {

		list = new MyList<T>();

	}
	public void push(T item) {

		// add to the end of the list
		list.add(item);

	}
	public T pop() {

		// remove from the front of the list
		return list.remove(list.size() - 1);

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

	public T peek() {

		return list.get(list.size() - 1);

	}


	// Test
	public static void main(String[] args) {

		MyStack<String> stack = new MyStack<String>();
		stack.push("a");
		stack.push("b");
		stack.push("c");
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);

	}


}
