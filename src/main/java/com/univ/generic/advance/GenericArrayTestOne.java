package com.univ.generic.advance;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 使用Array.newInstance方法
 * @author univ
 * date 2023/7/19
 */
public class GenericArrayTestOne<T> {

    T[] tArr;

    /**
     * IDE会提示【Unchecked cast: 'java.lang.Object' to 'T[]'】，因此加上 @SuppressWarnings
     */
    @SuppressWarnings("unchecked")
    public GenericArrayTestOne(Class<T> tClass, int arrLength) {
        // newInstance返回的是Object类型，这里转成T[]
        tArr = (T[]) Array.newInstance(tClass, arrLength);
    }

    public void set(int index, T t) {
        tArr[index] = t;
    }

    public T get(int index) {
        return tArr[index];
    }

    /**
     * 注：实际场景下，一般不会将底层数组暴露出去，对象逸出
     */
    public T[] getArr() {
        return tArr;
    }

    public static void main(String[] args) {
        GenericArrayTestOne<String> arrayTestOne = new GenericArrayTestOne<>(String.class, 3);
        // 此行编译失败，应当
        // arrayTestOne.set(0, 123);
        arrayTestOne.set(0, "abc");

        // 不用强转
        String str = arrayTestOne.get(0);
        System.out.println("str: " + str);

        // 不用强转
        String[] arr = arrayTestOne.getArr();
        System.out.println(Arrays.toString(arr));
    }

}
