package com.univ.enumeration;

/**
 * Univ
 * 16/9/1 16:01
 */

import org.junit.Test;

import java.util.EnumSet;
import java.util.Iterator;

/**
 * 演示EnumSet(EnumSet是一个抽象类)的使用方法。
 *
 * 1.首先,EnumSet是一个集合类型,知道这点很重要,只是这个集合中只能存放枚举类型的对象,且只能是同一种枚举类型的对象;
 * 2.EnumSet中的元素具有唯一性; 不能往EnumSet中存放null值;
 * 3.遍历(既然是集合,当然可以使用迭代器iterator);
 *      Iterator returned by EnumSet traverse the elements in their natural order,
 *      i.e. the order on which enum constants are declared, or the order returned by ordinal() method.
 * 4.常用操作:
 *      1.创建一个EnumSet实例(通过静态方法);
 *      2.判断是否包含某个元素;
 * 5.EnumSet是非线程安全的,要想得到线程安全的EnumSet,使用Collections.synchronizedSet包装一下:
 *      Set<MyEnum> s = Collections.synchronizedSet(EnumSet.noneOf(MyEnum.class));
 */
public class EnumSetTest {

    @Test
    public void test1(){

        EnumSet<Size> range = EnumSet.range(Size.S, Size.XXL);
        // [S, X, XL, XXL]
        System.out.println(range);

        /**
         * 1.创建一个空EnumSet,然后通过add方法添加元素;
         */
        EnumSet<Size> s1 = EnumSet.noneOf(Size.class);
        // 初始大小：0
        System.out.println("初始大小：" + s1.size());
        s1.add(Size.S);
        s1.add(Size.Z);
        // 添加元素后大小：2
        System.out.println("添加元素后大小：" + s1.size());
        s1.add(Size.XL);
        s1.add(Size.XL);//只会存在一份Size.XL
        s1.add(Size.XXL);
        // [Z, S, XL, XXL]
        showEnumSet(s1);
        System.out.println();

        /**
         * 2.利用allOf()方法将枚举类型Size的所有实例均添加到EnumSet中;
         */
        EnumSet<Size> s2 = EnumSet.allOf(Size.class);
        // [Z, S, X, XL, XXL]
        System.out.println(s2);

        /**
         * 3.利用of()方法将枚举类型Size的部分实例添加到EnumSet中;
         */
        EnumSet<Size> s3 = EnumSet.of(Size.XL, Size.S, Size.X);
        // [S, X, XL]
        showEnumSet(s3);
        System.out.println();

        /**
         * 判断集合中是否包含某个元素
         */
        boolean flag = s3.contains(Size.XXL);

        // false
        System.out.println(flag);
    }

    private void showEnumSet(EnumSet<Size> ss) {
        /**
         * 1.利用迭代器遍历
         * 遍历的顺序是ss中的元素在Size中定义的顺序。
         */
        Iterator<Size> iterator = ss.iterator();
        while (iterator.hasNext()) {
            Size size = iterator.next();
            System.out.println(size);
        }

        /**
         * 2.利用forEach遍历
         */
        /*for (Size s:ss) {
            System.out.println(s);
        }*/
    }
}

enum Size{
    Z,S,X,XL,XXL
}




