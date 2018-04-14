package com.univ.algorithom.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * created by Univ
 * 16/5/15 15:38
 */
public class InsertSort {


    @Test
    public void testInsertSort() {
        int[] a = {24, 3, 325, 23, 35, 22, 54, 123};
        insertSort1(a, 0, a.length - 1);
        System.out.print("[univ]:------ b: " + Arrays.toString(a));
    }

    private void insertSort1(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                int j = i - 1;
                do {
                    a[j + 1] = a[j];
                    j--;
                } while (j >= lo && temp < a[j]);
                a[j + 1] = temp;
            }
        }
    }

    private int[] insertSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = 0; j <= i - 1; j++) {
                if (a[j] >= a[i]) {
                    reOrder(a, j, i);
                    break;
                }
            }
        }
        return a;
    }

    private void reOrder(int[] a, int j, int i) {
        int temp = a[i];
        for (int k = i; k >= j + 1; k--) {
            a[k] = a[k - 1];
        }
        a[j] = temp;
    }


}
