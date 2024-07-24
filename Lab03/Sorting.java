/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Sorting {

    /**
     *
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort
     * 3 = Insertion Sort
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */

    // BubbleSort 气泡排序 Time Complexity O(n^2)
    public static void BubbleSort(int[] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length - i - 1; j++){
                if(a[j] > a[j + 1]){
                    // exchange a[j] and a[j+1]
                    swap(a, j, j + 1);
                }
            }
        }
    }


    // SelectionSort 选择排序
    public static void SelectionSort(int[] a){
        // 选择排序
        for(int i = 0; i < a.length; i++){
            int min = i; // 记录最小的元素
            for(int j = i + 1; j < a.length; j++){
                // find the min value
                if(a[j] < a[min]){
                    min = j;
                }
            }
            // exchange a[i] and a[min]
            swap(a, i, min);
        }
    }


    // InsertionSort
    public static void InsertionSort(int[] a){
        // 插入排序，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
        // 每次插入都是将当前元素(i)插入到前面已排序数组的合适位置，利用不断交换的方式
        for(int i = 1; i < a.length; i++){
            int j = i;
            while(j > 0 && a[j] < a[j - 1]){
                // exchange a[j] and a[j-1]
                swap(a, j, j - 1);
                j--;
            }
        }
    }


    // MergeSort Time Complexity O(n log(n)))
    public static void MergeSort(int[] array) {
        // 当数组中只有一个元素时返回，递归出口
        if (array.length <= 1) {
            return;
        }

        // device into two subgroup
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // 将原数组分割成左右两个子数组
        // left group: [0,mid)
        System.arraycopy(array, 0, left, 0, left.length);
        // right group:[mid,array.length)
        System.arraycopy(array, mid, right, 0, right.length);

        //sort for two group
        MergeSort(left);
        MergeSort(right);

        //combine two groups
        merge(left, right, array);
    }


    public static void merge(int[] left, int[] right, int[] result) {

        int i = 0; // left group
        int j = 0; // right group
        int k = 0; // combined group

        // 合并左右子数组, 使合并后的数组有序
        // 需要保证左右子数组都没有遍历完
        while (i < left.length && j < right.length) {
            // 将两个数组中较小的元素放到合并后的数组中
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // 将剩余的元素复制到合并后的数组中
        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }


    // QuickSort  Time Complexity from O(n log(n)) to O(n^2)
    public static void QuickSort(int[] array, int low, int high) {
        // low 和 high 代表需要排序的数组的起始和结束下标
        if (low < high) {
            // 将数组分区，找到分区点
            int pivotIndex = partition(array, low, high);
            // 对左子数组进行快速排序 [low, pivotIndex - 1]
            QuickSort(array, low, pivotIndex - 1);
            // 对右子数组进行快速排序 [pivotIndex + 1, high]
            QuickSort(array, pivotIndex + 1, high);
        }
    }


    public static int partition(int[] array, int low, int high) {
        // partition作用: 将数组分区，并返回分区点的下标
        // pivot左侧的元素都小于pivot，右侧的元素都大于pivot
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        // 归位pivot，此时i的左侧都小于pivot，右侧都大于pivot
        swap(array, i + 1, high);
        return i + 1;
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    // Output message
    public static void outputArray(int[] a, String path)  {
        // Output the array to a file
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            for (int i = 0; i < a.length; i++) {
                out.write(String.valueOf(a[i]));
                out.newLine();
            }
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    // main function
    public static void main(String[] args)  {
        In in = new In(args[0]);

        // Storing file input in an array
        int[] a = in.readAllInts();

        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);

        // c contains all integers stored in reverse order 
        // (you can have your own O(n) solution to get c from b
        int[] c = new int[b.length];
        for(int i = 0; i < b.length; i++){
            c[i] = b[b.length - i - 1];
        }
        // d contains almost sorted array 
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to achieve this.
        int[] d = Arrays.copyOf(b, b.length);
        int swaps = (int)(0.1 * d.length);
        for(int i = 0; i < swaps; i++){
            int index1 = StdRandom.uniform(d.length);
            int index2 = StdRandom.uniform(d.length);
            // exchange d[index1] and d[index2]
            swap(d, index1, index2);
        }


        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
        // For runtime and printing, you can use the same code from Lab 4 as follows:
        String[] arrays = {"a", "b", "c", "d"};
        int[][] arraysUsed = {a, b, c, d};
        String[] algorithms = {"Arrays.sort()", "BubbleSort", "SelectionSort", "InsertionSort", "Mergesort", "Quicksort"};
        int algorithm = Integer.parseInt(args[1]);
        for(int i = 0; i < 4; i++){
            String arrayUsed = arrays[i];
            int[] array = arraysUsed[i];
            Stopwatch timer = new Stopwatch();
            if(algorithm == 0) Arrays.sort(array);
            else if(algorithm == 1) BubbleSort(array);
            else if(algorithm == 2) SelectionSort(array);
            else if(algorithm == 3) InsertionSort(array);
            else if(algorithm == 4) MergeSort(array);
            else if(algorithm == 5) QuickSort(array, 0, array.length - 1);

            double time = timer.elapsedTimeMillis();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            //TODO: Replace with your own netid
            String netID = "zzh133";

            //TODO: Replace with the algorithm used
            String algorithmUsed = algorithms[algorithm];
            //TODO: Replace with the  array used
            // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
            //save the result to txt: a.txt b.txt
            String fileName = arrayUsed + ".txt";
            outputArray(array, fileName);

            
            // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
        }

    }
}


