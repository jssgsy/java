package com.univ.algorithom.sort;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * created by Univ
 * 16/5/18 15:46
 */
@RunWith(Parameterized.class)
public class BinaryInsertTest {

    //这里的预期值是固定的,不用在利用data方法注入
    private static int[] expected = {1, 4, 6, 10,19, 30, 30};
    private int[] arr;

    public BinaryInsertTest(int[] arr) {
        this.arr = arr;
    }

    //这里的方法仅仅是要求返回一个Collection而已
    @Parameterized.Parameters
    public static Collection data(){
        return Arrays.asList(new int[]{30,1,6,4,30,19,10},
                new int[]{10,19,30,6,30,4,1});
    }

    @Test
    public void test() {
        binaryInsert(arr, 0, arr.length - 1);
        assertArrayEquals(expected,arr);
    }

    public void binaryInsert(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                int index = findPosToInsert(a,lo,i-1,a[i]);//index:元素应该被正确放置的位置
                System.arraycopy(a,index,a,index+1,i-index);
                a[index] = temp;
            }
        }
    }

    /**
     * @param a
     * @param lo
     * @param hi
     * @param target
     * @return target在数组a中应该被放置的位置
     */
    private int findPosToInsert(int[] a, int lo, int hi, int target) {
        if (lo > hi) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == target) {
            return mid;
        } else if (a[mid] > target) {
            hi = mid - 1;
            if (hi >= lo && target > a[hi]) {
                return mid;
            }
        } else {
            lo = mid + 1;
            if (target < a[lo]) {
                return lo;
            }
        }

        return findPosToInsert(a, lo, hi, target);
    }

}
