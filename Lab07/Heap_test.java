// Zhenhao Zhang zzh133@u.rochester.edu



public class Heap_test {
	public static void main(String[] args) {
		// default ctor
		UR_MyHeap<Integer> hp1 = new UR_MyHeap<Integer>();
		hp1.insert(9);
		hp1.insert(4);
		hp1.insert(5);
		hp1.insert(3);
		hp1.insert(2);
		hp1.insert(8);
		hp1.insert(7);
		hp1.insert(6);
		hp1.insert(0);
		hp1.insert(1);

		hp1.printHeap();
		while (!hp1.isEmpty()) {
			System.out.print(hp1.deleteMin() + " ");
		}
		System.out.println();
		System.out.println(hp1.isEmpty());
		// test the heapify method
		Integer[] array = {9, 4, 5, 3, 2, 8, 7, 6, 0, 1};
		UR_MyHeap<Integer> hp2 = new UR_MyHeap<Integer>(array);
		hp2.printHeap();

		// ctor with size
		UR_MyHeap<Integer> hp3 = new UR_MyHeap<Integer>(20);
		hp3.insert(9);
		hp3.insert(4);
		hp3.insert(5);
		hp3.insert(3);
		hp3.insert(2);
		hp3.insert(8);
		hp3.insert(7);
		hp3.insert(6);
		hp3.insert(0);
		hp3.insert(1);
		hp3.insert(10);
		hp3.insert(11);
		hp3.insert(12);
		hp3.insert(13);
		hp3.insert(14);
		hp3.printHeap();

	}
}
