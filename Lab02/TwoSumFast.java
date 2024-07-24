/******************************************************************************
 *  Compilation:  javac TwoSumFast.java
 *  Execution:    java TwoSumFast input.txt
 *  Dependencies: In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with n log n running time. Read in n integers
 *  and counts the number of pairs that sum to exactly 0.
 *
 *  Limitations
 *  -----------
 *     - we ignore integer overflow
 *
 *
 *  % java TwoSumFast 2Kints.txt
 *  2
 *
 *  % java TwoSumFast 1Kints.txt
 *  1
 *
 *  % java TwoSumFast 2Kints.txt
 *  2
 *
 *  % java TwoSumFast 4Kints.txt
 *  3
 *
 *  % java TwoSumFast 8Kints.txt
 *  19
 *
 *  % java TwoSumFast 16Kints.txt
 *  66
 *
 *  % java TwoSumFast 32Kints.txt
 *  273
 *
 ******************************************************************************/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Arrays;

public class TwoSumFast {

	// print distinct pairs (i, j) such that a[i] + a[j] = 0
	public static void printAll(int[] a) {
		int n = a.length;
		Arrays.sort(a);
		if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
		for (int i = 0; i < n; i++) {
			int j = Arrays.binarySearch(a, -a[i]);
			if (j > i) StdOut.println(a[i] + " " + a[j]);
		}
	} 

	// return number of distinct pairs (i, j) such that a[i] + a[j] = 0
	public static int count(int[] a) {
		int n = a.length; // O(1)
		Arrays.sort(a); // O(N log(N))
		if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers"); // O(N)
		int count = 0; // O(1)
		//Loop: O(N)
		for (int i = 0; i < n; i++) {
			int j = Arrays.binarySearch(a, -a[i]); // O(log(N))
			if (j > i) count++;
		}
		// total: O(N log(N))
		return count;
	} 

	// returns true if the sorted array a[] contains any duplicated integers
	private static boolean containsDuplicates(int[] a) {
		for (int i = 1; i < a.length; i++)
			if (a[i] == a[i-1]) return true;
		return false;
	}

	/**
	 * 
	 * counts the number of pairs sum to exactly zero; prints out the time to perform
	 * the computation along with other information.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args)  { 
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Stopwatch timer = new Stopwatch();
        int count = count(a);
        double time = timer.elapsedTime();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		  //TODO: Replace with your own netid
        String netID = "zzh133";
		  StdOut.printf("%7d %7.1f   %s  %s  %s\n", count, time, timeStamp, netID, args[0]);
    } 

} 



