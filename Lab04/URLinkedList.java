
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class URLinkedList <E> implements URList<E>,Iterable<E> {


    private URNode<E> head; // Head of the list
    private URNode<E> tail; // Tail of the list
    private int size; // Size of the list


    // Constructor
    public URLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }


    // 确认输入是否合法
    public void checkIndex(int index){ // index 下标
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index " + index + "out of bounds"); // 手动报错
        }
    }


    // 插入内容
    // Appends the specified element to the end of this list
    @Override
    public boolean add(E e) {
        if (size == 0){ // 先判断有没有head 没有head 就得新赋值一个新的head
            head = new URNode<E>(e,null,null);
            tail = head;
        }
        else{ // 有元素的时候
            URNode<E> newNode = new URNode<E>(e,tail,null);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }


    // 插入内容到指定位置
    // Inserts the specified element at the specified position in this list
    @Override
    public void add(int index, E element) {
        if(size == 0) {
            // if the list is empty
            this.add(element);
            return;
        }
        // check index
        checkIndex(index);
        // Inserts the specified element at the specified position in this list
        URNode<E> node = head;
        for(int i = 0; i < size; i++) {
            if (i == index) {
                URNode<E> newNode = new URNode<E>(element, node.prev(), node);
                // the first node in the list
                if(node.prev() != null) {
                    node.prev().setNext(newNode);
                }
                else {
                    head = newNode;
                }
                node.setPrev(newNode);
                size++;
                return;
            }
            node = node.next();
        }
    }


    // 加入一个collection
    // Appends all of the elements in the specified collection to the end of this list,
    // in the order that they are returned by the specified collection's iterator
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c){
            this.add(element);
        }
        return true;
    }


    // 加入一个collection 在指定位置
    // Inserts all of the elements in the specified collection into this list
    // at the specified position
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        // check index
        checkIndex(index);
        int currentIndex = index;
        for (E element : c){
            this.add(currentIndex, element);
            currentIndex ++;
        }
        return true;
    }


    // Removes all of the elements from this list
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    // 查找一个数
    // Returns true if this list contains the specified element.
    @Override
    public boolean contains(Object o) {
        URNode<E> node = head;
        for (int i = 0; i< size ; i++){
            if(node.element().equals(o)){
                return true;
            }
            node = node.next();
        }
        return false;
    }


    // 查找很多内容
    // Returns true if this list contains all of the elements of the specified collection
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o: c){
            if(!this.contains(o)){
                return false;
            }
        }
        return true;
    }


    // 获取
    // Returns the element at the specified position in this list.
    @Override
    public E get(int index) {
        checkIndex(index);
        URNode<E> node = head;
        for (int i = 0; i< size ; i++){
            if(i == index){
                return node.element();
            }
            node = node.next();
        }
        // if not found
        return null;
    }


    // 返回下标值
    // Returns the index of the first occurrence of the specified element in this list,
    // or -1 if this list does not contain the element.
    @Override
    public int indexOf(Object o) {
        URNode<E> node = head;
        for (int i = 0; i< size ; i++){
            if(node.element().equals(o)){
                return i;
            }
            node = node.next();
        }
        // if not found
        return -1;
    }


    // 判断是不是空的
    // Returns true if this list contains no elements.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // 迭代器
    // Returns an iterator over the elements in this list in proper sequence.
    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }


    private class LinkedListIterator implements Iterator<E>{

        private URNode<E> node;

        // 构造函数
        public LinkedListIterator(){
            node = head;
        }

        public boolean hasNext(){
            return node != null;
        }

        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            E temp = node.element();
            node = node.next();
            return temp;
        }
    }


    // 移除 通过index
    // Removes the element at the specified position in this list
    @Override
    public E remove(int index) {
        checkIndex(index);
        URNode<E> node = head;
        for(int i =0; i < size; i++) {
            if (i == index) {
                // head
                if (node.prev() != null) { // 如果node不是第一位
                    node.prev().setNext(node.next());
                } else { // 如果node是第一位
                    head = node.next();
                }

                // tail
                if (node.next() != null) {
                    node.next().setPrev(node.prev());
                } else {
                    // node 是最后一位
                    tail = node.prev();
                }
                size--;
                return node.element();
            }
            node = node.next();
        }
        return null;
    }


    // 移除 通过 object
    // Removes the first occurrence of the specified element from this list,
    // if it is present
    @Override
    public boolean remove(Object o) {
        URNode<E> node = head;
        for(int i =0; i < size; i++) {
            if(node.element().equals(o)){

                // head
                if (node.prev() != null){
                    node.prev().setNext(node.next());
                }
                else {
                    head = node.next();
                }

                // tail
                if (node.next() != null){
                    node.next().setPrev(node.prev());
                }
                else {
                    tail = node.prev();
                }
                size --;
                return true;
            }
            node = node.next();
        }
            return false;
    }


    // 全部移除
    // Removes from this list all of its elements that are contained
    //  in the specified collection
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = true;
        for (Object o: c){
            if (!this.remove(o)){
                result = false;
            }
            else {
                result = true;
            }
        }
        return result;
    }


    // set
    // Replaces the element at the specified position in this list
    // with the specified element
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        URNode<E> node = head;
        for(int i =0; i < size; i++) {
            if (i == index){
                node.setElement(element);
                return node.element();
            }
            node = node.next();
        }
        return null;
    }


    // Returns the number of elements in this list.
    @Override
    public int size() {
        return size;
    }


    // Returns a view of the portion of this list
    // between the specified fromIndex, inclusive, and toIndex, exclusive.
    // 返回一个数组
    @Override
    public URList<E> subList(int fromIndex, int toIndex) {

        checkIndex(fromIndex);
        checkIndex(toIndex);

        URLinkedList<E> list = new URLinkedList<E>();
        URNode<E> node = head;

        for(int i =0; i < size; i++) {
            if (i >= fromIndex && i < toIndex){
                list.add(node.element());
            }
            node = node.next();
        }
        return list;
    }


    // Returns an array containing all of the elements in this list
    //  in proper sequence (from first to the last element).
    @Override
    public Object[] toArray() {
        Object [] node = new Object [size];
        URNode<E> temp = head;
        for(int i = 0; i < size; i++) {
            node[i] = temp.element();
            temp = temp.next();
        }
        return node;
    }


    // Compares the specified object with this list for equality.
    // Returns true if both contain the same elements. Ignore capacity
    @Override
    public boolean equals(Object o){
        URLinkedList<E> o_list = (URLinkedList<E>) o;
        for(int i = 0; i < size; i++) {
            if (this.get(i) != o_list.get(i)){
                return false;
            }
        }
        return true;
    }


    // Method Specific for only URLinkedList class:


    // 1. Inserts the specified element at the beginning of this list.
    public void addFirst(E e){

        this.add(0,e);
    }


    // 2. Appends the specified element to the end of this list.
    public void addLast(E e){

        this.add(e);
    }


    // 3. Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
    public E peekFirst(){
        if(size == 0){
            return null;
        }
        return head.element();
    }


    // 4. Retrieves, but does not remove, the last element of this list, or returns null if  this list is empty.
    public E peekLast(){
        if(size == 0){
            return null;
        }
        return tail.element();
    }


    // 5. Retrieves and removes the first element of this list, or returns null if this list is empty.
    public E pollFirst(){
        if(size == 0){
            return null;
        }
        E temp = head.element();
        head = head.next();
        size --;
        return temp;
    }


    // 6. Retrieves and removes the last element of this list, or returns null if this list is empty.
    public E pollLast(){
        if(size == 0){
            return null;
        }
        E temp = tail.element();
        tail = tail.prev();
        size --;
        return temp;
    }
}
