/*
Zhenhao Zhang  zzh133@u.rochester.edu 32277234 Lab Section 24
 */

public class CSC172_Lab1_Part1 {

    // Question 1
//    public static void printArray(Object[] Array) {  // print Array Method object是所以类的复类,不能知道是什么类
//        for(Object element : Array){
//            System.out.print(element + " ");
//        }
//        System.out.println( );
//    }



    //Question 2 Overload Method
//    public static void printArray(Integer[] Array) {
//        for (Integer element : Array){
//            System.out.print(element + " ");
//        }
//        System.out.println( );
//    }
//    public static void printArray(Double[] Array) { // Double 大写的是类，可以被继承
//        for (double element : Array){ // double 小写的是基础数据类型，声明变量(类型名称)
//            System.out.print(element + " ");
//        }
//        System.out.println( );
//    }
//    public static void printArray(Character[] Array) {
//        for (char element : Array){
//            System.out.print(element + " ");
//        }
//        System.out.println( );
//    }
//    public static void printArray(String[] Array) {
//        for (String element : Array){
//            System.out.print(element + " ");
//        }
//        System.out.println( );
//    }



    // Question 3  Generic programming technique  泛型，方便+类可以拿出来用
    public static < E > void printArray( E [] inputArray ) // E 就是可以代表[]的类别 eg.(Double，double，Integer)
    {
        // 输出数组元素
        for ( E element : inputArray ){ // E 可以拿出来用
            System.out.print(element + " " );
        }
        System.out.println();
    }



    //Question 4
//    public static Comparable getMax(Comparable [] anArray){ // comparable 是一个抽象借口, 需要重写一下compareTo的方法
//        Comparable max; // 定义了一个max，变量类型是Comparable
//        if( anArray.length == 0){ // 如果传错了内容
//            System.out.print("Input null");
//        }
//        max = anArray[0];
//        for(int i = 1; i < anArray.length ; i++){
//            if(max.compareTo(anArray[i]) < 0){ // compareTo: >0 前面那个大； <0 后面括号里面的大； =0 两个数相等
//                continue;
//            }
//            else{
//                max = anArray[i];
//            }
//        }
//        return max;
//    }



    //Question 5 Generic techniques
    public static < E extends Comparable <E> > E getMax(E[] anArray){ // E 继承了Comparable & 重写compareTo这个方法
        E max = anArray[0];
        if( anArray.length == 0){ // 如果传错了内容
            System.out.print("Input null");
        }
        for (int i = 1; i < anArray.length; i++) {
            if (anArray[i].compareTo(max) > 0) {
                max = anArray[i];
            }
        }
        return max;
    }



    // main function
    public static void main(String args[]) {
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArry = {1, 2, 3, 4, 5};
        Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
        String[] strArray = {"once", "upon", "a", "time"};

        // 输出 Output
        printArray(intArry);
        printArray(doubArry);
        printArray(charArray);
        printArray(strArray);

        System.out.println("max Integer is: " + getMax(intArry));
        System.out.println("max Double is: " + getMax(doubArry));
        System.out.println("max Character is: " + getMax(charArray));
        System.out.println("max String is: " + getMax(strArray));
    }
}



