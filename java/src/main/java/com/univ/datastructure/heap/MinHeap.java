package com.univ.datastructure.heap;

/**
 * Univ
 * 16/10/26 20:26
 */

/**
 * 最小堆
 * 使用数组实现,为了便于计算,数组的第一个位置空置
 */
public class MinHeap {

    private int[] elementData;  //存放堆元素的数组

    private int capacity;   //数组最大的容量

    private static int DEFAULT_CAPACITY = 11;   //数组容量默认为11

    private int tail;   //指向堆尾的指针(索引)

    public MinHeap() {
        this.capacity = DEFAULT_CAPACITY;
        elementData = new int[capacity];
    }

    public MinHeap(int capacity) {
        this.capacity = capacity;
        elementData = new int[capacity];
    }

    /**
     * 通过给定的数组来构造最小堆,不改变输入的数组a
     *
     * @param a
     */
    public MinHeap(int[] a) {
        elementData = new int[a.length + 1];//多出来的1是第一个空置的位置
        System.arraycopy(a, 0, elementData, 1, a.length);
        capacity = elementData.length;
        tail = a.length;
        int i = indexOfParent(tail);//此即为从下到上,从右到左第一个非叶子节点的索引
        while (i >= 1) {
            siftDown(i);
            i--;
        }
    }

    /**
     * 从堆尾插入元素,并使之符合最小堆的结构
     *
     * @param val
     * @return 插入成功返回true,否则抛出异常
     */
    public boolean offer(int val) {
        if (isFull()) {
            throw new UnsupportedOperationException("最小堆已满,元素" + val + "不允许插入。");
        }
        elementData[tail + 1] = val;
        tail++;
        siftUp(tail);
        return true;
    }

    /**
     * 删除堆顶元素,并使之符合最小堆的结构
     *
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("最小堆为空。");
        }
        int val = elementData[1];
        swap(1, tail);
        tail--;
        siftDown(1);
        return val;
    }

    /**
     * 返回(但不删除)堆顶元素
     *
     * @return
     */
    public int peek() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("最小堆为空。");
        }
        return elementData[1];
    }

    public boolean isEmpty() {
        return tail == 0;
    }

    public boolean isFull() {
        return tail + 1 == capacity;
    }

    /**
     * 上浮使之重新调整成符合最小堆的结构
     *
     * @param i 破坏最小堆结构节点的索引,从此节点开始上浮调整
     */
    private void siftUp(int i) {
        int p = indexOfParent(i);//i节点的父节点(索引)
        while (p > 0 && elementData[i] < elementData[p]) {
            swap(i, p);
            i = p;
            p = indexOfParent(p);
        }
    }

    /**
     * 下浮使之重新调整成符合最小堆的结构
     *
     * @param i 破坏最小堆结构节点的索引,从此节点开始下浮调整
     */
    private void siftDown(int i) {
        int min = minOfChildren(i);//左右子树中值较小节点的索引
        while (!isLeaf(i) && elementData[i] > elementData[min]) {//此时才需要下浮
            swap(i, min);
            i = min;
            min = minOfChildren(i);
        }
    }

    /**
     * @param i
     * @return 索引为i的节点的父节点的索引, 如果没有父节点, 则返回1
     */
    private int indexOfParent(int i) {
        //这里处理为非法输入则都返回堆顶节点的索引
        if (i < 2) {
            return 1;
        }
        return i / 2;
    }

    /**
     * 对于没有左子节点的情况,这里是设计成返回-1还是抛出异常都是一种思路。
     * @param i
     * @return 左子节点的索引, 如果没有左子节点, 则返回为-1(调用此方法时需要判断是否为负数来检查是否有左子节点)
     */
    private int indexOfLeftChild(int i) {
        if (!hasLeftChild(i)) {
            return -1;
        }
        return 2 * i;
    }

    /**
     * 对于没有又子节点的情况,这里是设计成返回-1还是抛出异常都是一种思路。
     * @param i
     * @return 右子节点的索引, 如果没有右子节点, 则返回为-1(调用此方法时需要判断是否为负数来检查是否有右子节点)
     */
    private int indexOfRightChild(int i) {
        if (!hasRightChild(i)) {
            return -1;
        }
        return 2 * i + 1;
    }

    /**
     * 交换数组中索引为i和j的值
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        int temp = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = temp;
    }

    /**
     * 返回索引为i的节点的左右节点值较小的节点的索引。
     * 如果没有子节点,则返回其自身的索引。如果只有左节点,则返回左节点的索引,否则返回左右节点值较小的节点的索引。
     *
     * @param i
     * @return
     */
    private int minOfChildren(int i) {
        if (isLeaf(i)) {
            return i;
        }
        int left = 2 * i;
        if (hasRightChild(i)) {
            int right = 2 * i + 1;
            return elementData[left] <= elementData[right] ? left : right;
        } else {
            return left;
        }
    }

    /**
     * 判断索引为i的节点是否有父节点
     *
     * @param i
     * @return
     */
    public boolean hasParent(int i) {
        return i <= 1;
    }

    /**
     * 判断索引为i的节点是否有左子节点
     *
     * @param i
     * @return 如果有左子节点则返回true, 否则返回false
     */
    private boolean hasLeftChild(int i) {
        return 2 * i <= tail;
    }

    /**
     * 判断索引为i的节点是否有右子节点
     *
     * @param i
     * @return 如果有右子节点则返回true, 否则返回false
     */
    private boolean hasRightChild(int i) {
        return 2 * i + 1 <= tail;
    }

    /**
     * 判断索引为i的节点是否为叶子节点
     *
     * @param i
     * @return 为叶子节点则返回true, 否则返回false
     */
    private boolean isLeaf(int i) {
        return i > tail / 2;
    }

    /**
     * 树中节点的个数
     *
     * @return
     */
    public int size() {
        return tail;
    }

    /**
     * 返回最小堆(完全二叉树)的深度。树的深度为log2(n+1)并向上取整。不过用java代码不太好计算,因此使用下面的代码。
     *
     * @return
     */
    public int height() {
        if (isEmpty()) {
            return 0;
        }

        int node = 1, h = 1;
        while (hasLeftChild(node)) {
            node = indexOfLeftChild(node);
            h++;
        }
        return h;
    }

    /**
     * 以完全二叉树的形式打印出整个最小堆。这里也算一个小算法,值得仔细琢磨。
     */
    public void showAsTree() {
        int i = 1;//指向头节点(堆顶)
        int heigh = height();
        for (int h = 1; h <= heigh; h++) {
            int num = (int) Math.pow(2, h - 1);
            for (int j = 1; j <= num && i <= tail; j++) {
                System.out.print(elementData[i++] + "    ");
            }
            System.out.println("");
        }
    }

    /**
     * 将此最小堆排序
     *
     * @return 从大到小排好序的数组
     */
    public int[] heapSort() {
        int i = tail;
        while (i > 1) {
            swap(1, i); //队尾和堆头元素互换
            tail--; //注意这句很重要,因为下面的siftDown只能在除掉末尾元素剩下的堆中使用
            siftDown(1);
            i--;
        }
        tail = elementData.length - 1;
        return toArray();
    }

    /**
     * 将此最小堆转换成数组
     *
     * @return
     */
    private int[] toArray() {
        if (isEmpty()) {
            return new int[0];
        }
        int[] temp = new int[elementData.length - 1];
        System.arraycopy(elementData, 1, temp, 0, elementData.length - 1);
        return temp;
    }

}
