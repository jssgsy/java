package com.univ.algorithom.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * created by Univ
 * 16/5/18 14:47
 */
@RunWith(Parameterized.class)
public class BinarySearchTest {

    private int target;
    private int index;
    private static  int[] arr = {1, 2, 5, 7, 10, 34, 38};

    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new Object[][]{
            {1,0},
            {2,1},
            {5,2},
            {9,-1},
            {38,arr.length-1}
        });
    }

    //这里方法的函数一定要和data方法中的顺序对应
    public BinarySearchTest(int target, int index) {
        this.target = target;
        this.index = index;
    }

    @Test
    public void test() {
        int indexResult = binarySearch(arr, 0, arr.length - 1, target);
        assertEquals(indexResult, index);
    }


    //找到则返回其在数组中的索引,否则返回-1
    public int binarySearch(int[] a, int lo, int hi, int target) {
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;
        if (a[mid] == target) {
            return mid;
        } else if (a[mid] > target) {
            hi = mid - 1;
        } else {
            lo = mid + 1;
        }
        return binarySearch(a, lo, hi, target);
    }

}
