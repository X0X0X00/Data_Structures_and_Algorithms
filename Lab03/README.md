## My findings
- 图a为例，三种时间复杂度O(nlogn)的排序方法明显要比O(n^2)的排序快得多
- 对于排序好的数组，插入排序的复杂度可以到达O(n)，但快速排序的具体复杂度和pivot选择有关，选择、冒泡排序的复杂度为O(n^2)
- 图c中，逆序最差情况，快速排序的时间复杂度为O(n^2)，而归并排序的时间复杂度为O(nlogn)
- 在处理几乎排序好的数组时，插入排序效率很高，在比较的过程中需要移动的元素很少


## Tips
- InsertionSortInterleaved(numbers, numbersSize, startIndex, gap)