// 泛型队列类
public class URQueue<T> {
    private URArrayList<T> list;

    // 构造函数
    public URQueue() {
        list = new URArrayList<T>();
    }

    // 入队操作，将元素添加到队列末尾
    public void enqueue(T item) {
        // 添加到列表的末尾
        list.add(item);
    }

    // 出队操作，从队列头部移除元素并返回该元素
    public T dequeue() {
        // 从列表的开头移除元素
        return list.remove(0);
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 返回队列中元素的数量
    public int size() {
        return list.size();
    }

    // 返回队列的字符串表示形式
    public String toString() {
        return list.toString();
    }

}
