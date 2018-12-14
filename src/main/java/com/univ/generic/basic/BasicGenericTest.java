package com.univ.generic.basic;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/14 2:21 PM
 * @description 泛型的基本知识
 */
public class BasicGenericTest {

    @Test
    public void test() {
        /*
        类级别泛型参数的使用
         */
        Author author = new Author();
        // 定义类实例时具化泛型参数
        Book<Author> book = new Book<>();
        book.setAuthor(author);


        /*
        方法级别泛型参数的使用:不用做任何操作，和普通方法调用就行
         */
        // 静态方法
        Demo.getById("abc");

        // 实例方法
        Demo demo = new Demo();
        Integer i = demo.getById2(12);
        System.out.println(i);

    }
}

/**
 * 类级别定义泛型参数
 * @param <T>
 */
class Book<T> {
    private String name;
    private T author;

    public void setAuthor(T author) {
        this.author = author;
    }
}
class Author {
    private String name;
}

class Demo {

    /**
     * 可以在方法级别定义泛型参数，一般用在工具类中
     * @param <T>
     * @return
     */
    public static <T> void getById(T p) {
        System.out.println("啥也不做");
    }

    public <T, V> T getById2(T t1) {
        return t1;
    }
}