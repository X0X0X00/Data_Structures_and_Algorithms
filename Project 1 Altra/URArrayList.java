import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class URArrayList<E> {

    private E[] array; // 存储元素的数组
    private int size; // 存储的元素数量
    private int capacity; // 总容量


    // 构造函数
    public URArrayList() {
        capacity = 4;
        size = 0;
        array = (E[]) new Object[capacity];
    }


    // 检查索引是否合法
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }


    // 扩展容量
    public void grow() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }


    // 向列表末尾添加指定元素
    public boolean add(E e) {
        if (size == capacity) {
            grow();
        }
        array[size] = e;
        size++;
        return true;
    }


    // 在指定位置插入指定元素
    public void add(int index, E element) {
        checkIndex(index);
        if (size == capacity) {
            grow();
        }
        for(int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }


    // 将指定集合中的所有元素添加到列表末尾
    public boolean addAll(Collection<? extends E> c) {
        for(E e : c) {
            add(e);
        }
        return true;
    }


    // 将指定集合中的所有元素插入到列表的指定位置
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        int currentIndex = index;
        for(E e : c) {
            add(currentIndex, e);
            currentIndex++;
        }
        return true;
    }


    // 清空列表中的所有元素
    public void clear() {
        size = 0;
        capacity = 4;
        array = (E[]) new Object[capacity];
    }


    // 检查列表是否包含指定元素
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return true;
            }
        }
        return false;
    }


    // 检查列表是否包含指定集合中的所有元素
    public boolean containsAll(Collection<?> c) {
        for(Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }


    // 获取指定位置的元素
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }


    // 获取指定元素在列表中第一次出现的索引位置，如果未找到则返回 -1
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }


    // 检查列表是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    // 返回一个迭代器，用于按顺序遍历列表中的元素
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }


    // 内部类，用于实现迭代器
    private class ArrayIterator implements Iterator<E> {
        private int currentIndex;

        public ArrayIterator() {
            currentIndex = 0;
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex++];
        }
    }


    // 比较列表是否与指定对象相等，忽略容量
    public boolean equals(Object o) {
        URArrayList<E> o_list = (URArrayList<E>) o;
        // if the length is not equal, return false
        if (this.size != o_list.size) {
            return false;
        }
        for(int i = 0; i < size; i++) {
            if (this.get(i) != o_list.get(i)) {
                return false;
            }
        }
        return true;
    }


    // 移除列表中指定位置的元素
    public E remove(int index) {
        checkIndex(index);
        E element = array[index];
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return element;
    }


    // 移除列表中第一次出现的指定元素（如果存在）
    public boolean remove(Object o) {
        for(int i = 0; i < size; i++) {
            if(array[i].equals(o)){
                for(int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }


    // 从列表中移除所有包含在指定集合中的元素
    public boolean removeAll(Collection<?> c) {
        for(Object o : c) {
            while(this.contains(o)) {
                this.remove(o);
            }
        }
        return true;
    }


    // 设置指定位置的元素值，并返回原来的元素值
    public E set(int index, E element) {
        checkIndex(index);
        // 用指定元素替换列表中指定位置的元素
        E oldElement = array[index];
        array[index] = element;
        return oldElement;
    }


    // 返回列表中元素的数量
    public int size() {
        return size;
    }



    // 返回一个包含列表中所有元素的数组，按照正确的顺序（从第一个到最后一个元素）
    public Object[] toArray() {
        // 将数组复制到一个新数组中
        Object[] newArray = new Object[size];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }


    // 增加此 ArrayList 实例的容量（如果需要的话），
    // 以确保它可以容纳至少由最小容量参数指定的元素数量。
    public void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            capacity = minCapacity;
            // 将元素移动到新数组中
            E[] newArray = (E[]) new Object[capacity];
            for(int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }


    // 返回列表的当前容量
    int getCapacity() {
        return capacity;
    }


    // 返回列表的字符串表示形式
    public String toString() {
        String str = "[";
        for(int i = 0; i < size; i++) {
            str += array[i];
            if(i != size - 1) {
                str += ", ";
            }
        }
        str += "]";
        return str;
    }
}
