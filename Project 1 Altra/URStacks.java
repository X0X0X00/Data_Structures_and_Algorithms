
//  泛型堆栈类定义
public class URStacks<T> {
    // 声明一个私有的列表成员变量
    private URArrayList<T> URList;

    // 构造函数，创建一个新的堆栈对象时初始化列表
    public URStacks() {
        URList = new URArrayList<T>();
    }

    // isEmpty 方法，用于检查堆栈是否为空
    public boolean isEmpty() {
        return URList.isEmpty();
    }

    // size 方法，用于获取堆栈中元素的数量
    public int size() {
        return URList.size();
    }

    // push 方法，用于将元素推入堆栈
    public void push(T item) {
        // 将元素添加到列表的末尾
        URList.add(item);
    }

    // pop 方法，用于从堆栈中弹出元素
    public T pop() {
        // 从列表的末尾移除元素并返回它
        return URList.remove(URList.size() - 1);
    }

    // peek 方法，用于获取堆栈顶部的元素，但不移除它
    public T peek() {
        return URList.get(URList.size() - 1);
    }

    // toString 方法，返回堆栈的字符串表示
    public String toString() {
        return URList.toString();
    }


}
