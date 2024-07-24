// Zhenhao Zhang zzh133@u.rochester.edu



public interface UR_Heap<T extends Comparable<T>> {
	public void insert(T item);
	public boolean isEmpty();
	public int size();
	public T deleteMin();
}