package com.univ.sort;

/**
 * Univ
 * 16/7/28 20:34
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * 二叉折半插入排序算法
 *  二叉折半插入排序是稳定的排序算法
 */
public class BinarySearchSortTest {


    @Test
    public void test(){
        int[] a = {1, 3, 50, 20, 15, 3,-1,100};
        System.out.println("待排序数组为: " + Arrays.toString(a));
        System.out.println("内部排序过程为: ");
        binarySearchSort(a,0,a.length);
        System.out.println(Arrays.toString(a));

    }

    private void binarySearchSort(int[] a, int fromIndex, int toIndex){
        for (int i = 1; i < toIndex; i++) {
            if (a[i] < a[i-1]) {//此时才需要开始二叉搜索
                int low = fromIndex;
                int high = i-1;
                int temp = a[i];
                while (low <= high) {
                    int mid = (low + high)/2;//注意此句需要放在循环体内
                    if (temp < a[mid]) {
                        high = mid - 1;
                    } else { //重点,这里包含了a[i]==a[mid],这是为了保证算法是稳定的
                        low = mid + 1;
                    }
                }//循环结束之后,low就是a[i]应该插入的位置,而不论a[i]是否在a中存在

                //将a[low,i-1]移到到a[low+1,i]中,共需移动(i-1)-low+1=i-low个元素
                System.arraycopy(a,low,a,low+1,i-low);
                a[low] = temp;
                System.out.println(Arrays.toString(a));
            }
        }
    }


}
