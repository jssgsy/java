package com.univ.generic.advance;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author univ
 * date 2023/7/19
 */
public class GenericArrayTestTwo<T> {

    Object[] arr;

    public GenericArrayTestTwo(Object[] arr) {
        this.arr = arr;
    }

    public GenericArrayTestTwo(int arrLen) {
        this.arr = new Object[arrLen];
    }

    public void set(int index, T t) {
        arr[index] = t;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        // 此时显然要强转
        return (T) arr[index];
    }

    /**
     * 运行时依然可能报错，参见下面的f1()与f2()
     *
     * 注：实际场景下，一般不会将底层数组暴露出去，对象逸出
     */
    @SuppressWarnings("unchecked")
    public T[] getArr() {
        // 此时显然要强转
        return (T[]) arr;
    }

    public static void main(String[] args) {
        // ok
        f1();

        // not ok
        // f2();
    }

    public static void f1() {
        Integer[] intArr = new Integer[3];
        // 重点：这里指定了底层数组存放的实际元素为Integer
        GenericArrayTestTwo<Integer> arrayTestTwo = new GenericArrayTestTwo<>(intArr);
        arrayTestTwo.set(0, 10);
        Integer integer = arrayTestTwo.get(0);
        System.out.println(integer);

        // 此时调用会ok，因为底层数组中存放的就是Integer
        Integer[] arr1 = arrayTestTwo.getArr();
        System.out.println(Arrays.toString(arr1));
    }

    @Test
    public static void f2() {
        // 重点：此时底层数组存放的实际元素为Object
        GenericArrayTestTwo<Integer> arrayTestTwo = new GenericArrayTestTwo<>(3);
        arrayTestTwo.set(0, 10);
        Integer integer = arrayTestTwo.get(0);
        System.out.println(integer);
        // 此时调用会报错，因为底层数组中存放的就是Object，没法转成其它类型
        Integer[] arr1 = arrayTestTwo.getArr();
        System.out.println(Arrays.toString(arr1));
    }

}
