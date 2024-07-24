


import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // 创建一个空的堆实例
        My_Heap<Integer> heap = new My_Heap<>();

        // 插入一些元素
        int[] elementsToAdd = {1, 3, 2, 5, 9, 8, 8, 9, 10};
        for (int element : elementsToAdd) {
            System.out.println("Inserting element: " + element);
            heap.insert(element);
        }

        // 打印堆内容
        System.out.println("Heap after inserting elements:");
        heap.printHeap();

        // 删除最小元素并打印
        System.out.println("Deleting minimum element: " + heap.deleteMin());
        System.out.println("Heap after deleting an element:");
        heap.printHeap();

        // 使用随机数组创建堆
        Random random = new Random();
        Integer[] randomArray = new Integer[10];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(100); // 随机生成0到99之间的整数
        }

        // 打印原始数组
        System.out.println("Random array:");
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 通过随机数组创建新的堆
        My_Heap<Integer> heapFromArray = new My_Heap<>(randomArray);

        // 打印通过数组创建的堆
        System.out.println("Heap created from an array:");
        heapFromArray.printHeap();
    }
}
