package com.univ.datastructure.queue;

/**
 * Univ
 * 16/10/24 18:21
 */

/**
 * 循环队列,内部使用数组表示
 */
public class CircleQueue {

    private Object[] elementData;
    private int f;  //队头所在的位置
    private int r;  //下一个入队元素的索引(实际对尾元素的前一个索引)

    private int capacity;  //数组elementData的大小
    private static int DEFAULT_CAPACITY = 10;   //elementData默认为10

    public CircleQueue(){
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    public CircleQueue(int capacity){
        this.capacity = capacity;
        elementData = new Object[capacity];
    }

    /**
     * 判断循环队列是否为空。
     * r == f,表示下一个入队元素的索引是队头,说明此时队列内还没有元素
     * @return
     */
    public boolean isEmpty(){
        return r == f;
    }

    /**
     * 判断循环队列是否被占满。
     * 如果对尾再往前一步就碰到了队头,说明此时队列已经没有多余位置了
     */
    public boolean isFull(){
        return (r + 1) % capacity == f;
    }

    /**
     * 入队
     * 入队是队尾的操作,队头指针不用改动
     * @param obj
     */
    public boolean offer(Object obj){
        if (!isFull()) {
            elementData[r] = obj;
            r = (r + 1) % capacity;
            return true;
        }else {
            throw new UnsupportedOperationException("队列已满,元素" + obj + "不能入队。");
        }
    }

    /**
     * 出队
     * 出队是队头的操作,队尾指针不用改动
     * @return  队头元素的值
     */
    public Object poll(){
        if (!isEmpty()) {
            Object val = elementData[f];
            f = (f + 1) % capacity;
            return val;
        }else {
            throw new UnsupportedOperationException("队列为空,不能出队。");
        }
    }

    /**
     * 获取队头元素,不出队
     * @return
     */
    public Object peek(){
        if (!isEmpty()) {
            return elementData[f];
        }else {
            throw new UnsupportedOperationException("队列为空。");
        }
    }

    /**
     * 队列中元素的个数
     * @return
     */
    public int size(){
        return (r - f + capacity) % capacity;
    }

    /**
     * 这里需要注意遍历循环列表的方法
     * 这里不能改变队头指针f与队尾指针r。
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size());
        sb.append("[");
        int i = f;//不能改成队头指针f的值,因此先记录下f
        while (i != r) {//遍历的核心代码
            sb.append(elementData[i]).append(",");
            i = (i + 1) % capacity;
        }
        if (!isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

}
