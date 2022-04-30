package com.univ.algorithom.basic;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 数组相关的算法
 * @author univ
 * 2022/4/30 9:09 下午
 */
public class ArrayAlgorithm {

    /**
     * 循环打印矩阵元素
     */
    @Test
    public void testPrintMatrixInCycle() {
        int[][] arr1 = {
                {1}, {2}, {3}
        };
        int[][] arr2 = {{1, 2, 3}};
        int[][] arr3 = {
                {1, 10, 100, 1000, 10000},
                {2, 20, 200, 2000, 20000},
                {3, 30, 300, 3000, 30000},
                {4, 40, 400, 4000, 40000},
                {5, 50, 500, 5000, 50000},

        };
        int[][] arr4 = {
                {1, 10, 100, 1000, 10000},
                {2, 20, 200, 2000, 20000},
                {3, 30, 300, 3000, 30000},

        };
        int[][] arr5 = {
                {1, 10},
                {2, 20},
                {3, 30},
                {4, 40},
                {5, 50},

        };
        for (int[][] arr : Arrays.asList(arr1, arr2, arr3, arr4, arr5)) {
            int rows = arr.length;
            int columns = arr[0].length;
            System.out.println("rows: " + rows + ", columns: " + columns);
            System.out.println("原始数组为：" + JSONObject.toJSONString(arr));
            printMatrixInCycle(arr, rows, columns);
            System.out.println("==================");
        }
    }

    /**
     * 从左到右，从上到下循环打印出出矩阵的每个元素
     * 思路：一个循环即是一圈，因此分解成两步
     *  1. 总共需要打印多少圈；
     *  2. 每圈打印时的下标是多少；
     *
     * 关于第一个步骤：经过演算发现不好找出一个具体的值说总共要打印多少圈，因此转换思路为：什么时候不需要再打圈了！
     *
     * 注意点：
     *  a. 上述第二步中，要注意单行或单列矩阵的边界条件，防止重复打印；
     *
     * @param arr
     * @param rows
     * @param columns
     */
    public void printMatrixInCycle(int[][] arr, int rows, int columns) {
        int start = 0;
        do {
            printCycle(arr, start, rows, columns);
            start++;
            rows = rows -2;
            columns = columns - 2;
        } while (rows >= 1 && columns >= 1);    // 注意循环结束条件
    }

    private void printCycle(int[][] arr, int start, int rows, int columns) {
        int first = start;
        for (; first <= start + columns - 1; first++) {
            System.out.print(arr[start][first]);
            System.out.println();
        }

        if (rows - 1 <= start) {    // 重要：说明没有更多行要打印了
            return;
        }
        for (int second = start + 1; second <= start + rows -1; second++) {
            System.out.print(arr[second][start + columns - 1]);
            System.out.println();
        }

        for (int third = start + columns - 2; third >= start; third--) {
            System.out.print(arr[start + rows - 1][third]);
            System.out.println();
        }

        if (columns - 1 <= start) {    // 重要：说明没有更多列要打印了
            return;
        }
        for (int forth = start + rows - 2; forth >= start + 1; forth--) {
            System.out.print(arr[forth][start]);
            System.out.println();
        }

    }
}
