package com.univ;

/**
 * Univ
 * 16/9/13 11:16
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 用以存放jdk中常用的工具类方法
 */
public class JdkUtil {

    /**
     * System.arraycopy()
     * 参数:
     *      src - the source array.
     *      srcPos - starting position in the source array.
     *      dest - the destination array.
     *      destPos - starting position in the destination data.
     *      length - 想从原数组中复制元素的个数
     *
     * 注意和Arrays.copyOf()方法的区别(见ArrayTest.java):
     *  1. System.arraycopy():新旧数组都是方法的参数;
     *  2. Arrays.copy():新数组是方法的返回值;
     *  3. Arrays.copy()底层调用的是System.arraycopy()方法;
     *
     */
    @Test
    public void test1(){
        int[] a = {1, 2, 3, 4};
        int[] b = {6, 7, 8};
        /**
         * 将b数组中的[0,0+2)区间的元素用a数组中的[1,1+2)元素替换(都是左开右闭区间);
         */
        System.arraycopy(a,1,b,0,2);
        System.out.println(Arrays.toString(b));//2, 3, 8
    }

    /**
     * Arrays.toString()/Arrays.deepToString
     */
    @Test
    public void test2(){
        //int 类型数组排序
        int[] arrInt = {3, 5, 1, 39, 34, -3};
        System.out.println(Arrays.toString(arrInt));

        //double类型数组排序
        double[] arrDouble = {2.4, 1.2, 5, 36.2, 34.24, -3.5};
        System.out.println(Arrays.toString(arrDouble));

        //String类型数组排序
        String[] arrStr = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        System.out.println(Arrays.toString(arrStr));

        //二维数组
        int[][] arr2 = {
                {1,3,5},
                {3,23,7},
                {23,36,14}
        };
        System.out.println(Arrays.deepToString(arr2));
    }

    /**
     * Arrays.equals
     *  基本类型数组的比较判断。
     *  Arrays中提供了基本数据类型数组排序:Arrays.equals(arr1,arr2)
     */
    @Test
    public void test3(){
        //int 类型数组相等比较
        int[] arrInt1 = {3, 5, 1, 39, 34, -3};
        int[] arrInt2 = {3, 39, 34, -3};
        System.out.println(Arrays.equals(arrInt1,arrInt2));


        //String类型数组排序
        String[] arrStr1 = {"abd", "fjeifa", "iu", "aa", "iy", "etf"};
        String[] arrSt2 = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        System.out.println(Arrays.equals(arrStr1,arrSt2));
    }

    /**
     * Arrays.asList()
     *     1. 要转换成list的数组中的元素不能为基本类型;
     *     2. 转成的list大小是固定的,不能执行add,remove等操作;
     *     3. 原数组的修改会影响转换后的list;
     *     4. 上面2和3的原因: Arrays.asList()返回的是Arrays内部的一个类,此类内部维护一个数组a,当调用此方法时,只是将a赋值为传入的参数然后返回a;
     *     5. list可以修改其中元素的内容(Arrays类中相关内部类继承自AbstractList类);
     */
    @Test
    public void test4(){

        Integer[] a = {2, 5, 3, 4};//1.不能为int[]类型
        List<Integer> list = Arrays.asList(a);
        System.out.println(list);//[2, 5, 3, 4]

        //list.add(200);//2.list的大小固定

        a[3] = 100; //3.原数组的修改会影响转换后的list
        System.out.println(list);//[2, 5, 3, 100]

        list.set(2, 300);//5.list可以修改其中元素的内容;
        System.out.println(list);//[2, 5, 300, 100]

    }

    /**
     *  Arrays.sort()
     *      按自然顺序排序
     */
    @Test
    public void test5(){
        Integer[] arr = {1,30,5,2,3,15,23,9};
        //升序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //降序
        Arrays.sort(arr,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : 1;
            }
        });
        System.out.println(Arrays.toString(arr));

        //大小写敏感排序(默认)
        String[] strArr = {"avd", "eia", "Ahi", "Bad"};
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));

        //大小写不敏感排序(String类内部已经定义了比较器CASE_INSENSITIVE_ORDER)
        Arrays.sort(strArr,String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strArr));

    }

    @Test
    public void test6(){
        Integer[] arr = {1,30,5,2,3,15,23,9};
        Integer[] clone = arr.clone();
        arr[2] = 200;
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(clone));
    }


}
