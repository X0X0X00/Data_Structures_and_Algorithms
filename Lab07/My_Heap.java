import java.util.NoSuchElementException;


// 声明一个泛型堆类，它实现了 UR_Heap 接口。T 是一个可比较的类型。
public class My_Heap<T extends Comparable<T>> implements UR_Heap<T> {
    private T[] heap; // 堆存储的数组
    private int CurrentSize; // 当前堆中元素的数量
    private static final int DEFAULT_CAPACITY = 10; // 默认的堆容量


    // 默认构造函数，初始化一个具有默认容量的空堆
    public My_Heap() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        CurrentSize = 0;
    }


    // 带有特定容量参数的构造函数，创建一个特定容量的空堆
    public My_Heap(int capacity) {
        heap = (T[]) new Comparable[capacity];
        CurrentSize = 0;
    }


    // 构造函数，将一个数组转换成堆
    public My_Heap(T[] items) {
        CurrentSize = items.length;
        heap = (T[]) new Comparable[CurrentSize];

        System.arraycopy(items, 0, heap, 0, items.length); // 复制元素

        // 将数组转换为堆
        for (int i = CurrentSize / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }


    // 将给定索引处的元素下沉到正确位置以维护堆的性质
    private void heapify(int index) {
        int smallest = index; // Initialize smallest as root
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        // 检查左子节点是否小于当前节点
        if (left < CurrentSize && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }

        // 检查右子节点是否小于当前最小节点
        if (right < CurrentSize && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }

        // 如果找到了更小的子节点，与当前节点交换并递归处理
        if (smallest != index) {
            // Swap
            T temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;

            heapify(smallest);
        }
    }


    // 向堆中插入一个新元素
    @Override
    public void insert(T item) {
        if (CurrentSize == heap.length) {
            expandHeap(); // 如果堆已满，扩大其大小
        }
        heap[CurrentSize] = item;
        bubbleUp(CurrentSize); // 将新元素上浮到合适的位置
        CurrentSize++;
    }


    // 检查堆是否为空
    @Override
    public boolean isEmpty() {
        return CurrentSize == 0;
    }


    // 返回堆的大小
    @Override
    public int size() {
        return CurrentSize;
    }


    // 删除并返回堆中的最小元素
    @Override
    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T minItem = heap[0];
        heap[0] = heap[--CurrentSize];
        heap[CurrentSize] = null;
        bubbleDown(0); // 将新的根元素下沉到合适的位置
        return minItem;
    }


    // 扩大堆的大小
    private void expandHeap() {
        T[] newHeap = (T[]) new Comparable[heap.length * 2]; // 翻倍
        System.arraycopy(heap, 0, newHeap, 0, heap.length); // 复制
        heap = newHeap; // 更新
    }


    // 将给定索引处的元素上浮到正确位置
    private void bubbleUp(int index) {
        T item = heap[index];
        while (index > 0 && item.compareTo(heap[(index - 1) / 2]) < 0) {
            heap[index] = heap[(index - 1) / 2];
            index = (index - 1) / 2;
        }
        heap[index] = item;
    }


    // 将给定索引处的元素下沉到正确位置
    private void bubbleDown(int index) {
        int child;
        T tmp = heap[index];
        while (index * 2 + 1 < CurrentSize) {
            child = 2 * index + 1; // 左子节点索引
            // 检查右子节点是否存在且比左子节点小
            if (child < CurrentSize - 1 && heap[child + 1].compareTo(heap[child]) < 0) {
                child++; // 使用右子节点
            }
            // 如果子节点小于当前节点，进行交换
            if (heap[child].compareTo(tmp) < 0) {
                heap[index] = heap[child];
            } else {
                break;
            }
            index = child;
        }
        heap[index] = tmp;
    }


    // 打印堆中的内容
    public void printHeap() {
        for (int i = 0; i < CurrentSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }


}
