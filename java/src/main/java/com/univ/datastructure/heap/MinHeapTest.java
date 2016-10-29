package com.univ.datastructure.heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * Univ
 * 16/10/26 21:30
 */
public class MinHeapTest {

    @Test
    public void test(){
        MinHeap minHeap = new MinHeap();
        minHeap.offer(53);
        minHeap.offer(17);
        minHeap.offer(78);
        minHeap.offer(9);
        minHeap.offer(45);
        minHeap.offer(65);
        minHeap.offer(87);
        minHeap.offer(23);
        System.out.println("最小堆的大小为: " + minHeap.size());
        System.out.println("最小堆的高度为: " + minHeap.height());
        minHeap.showAsTree();

        minHeap.poll();
        minHeap.poll();
        minHeap.offer(3);
        System.out.println("最小堆的大小为: " + minHeap.size());
        System.out.println("最小堆的高度为: " + minHeap.height());
        minHeap.showAsTree();
    }

    /**
     * 最小堆的构造
     */
    @Test
    public void constructHeapTest(){
        int[] a = {23, 12, 2, 3, 5,34,1,87, 23,98,7,12,43,0};
        MinHeap minHeap = new MinHeap(a);
        minHeap.showAsTree();
    }

    /**
     * 堆排序的测试
     */
    @Test
    public void test3(){
        int[] a = {23, 12, 2, 3, 5,34,1,87, 23,98,7,12,43,0};
        MinHeap minHeap = new MinHeap(a);
        minHeap.showAsTree();
        int[] result = minHeap.heapSort();
        System.out.println(Arrays.toString(result));
        minHeap.showAsTree();
    }
}
