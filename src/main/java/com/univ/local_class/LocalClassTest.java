package com.univ.local_class;

import org.junit.Test;

import lombok.Data;

/**
 * @author univ
 * @date 2019/6/16 3:40 PM
 * @description 本地类：即定义在方法中的类
 */

/**
 * 小结：
 * 1. local类不能用修饰符，其作用域就是定义此类的外部方法；
 * 2. local类可以访问外部类的成员，且可以访问外部方法的局部变量(包含形参与局部变量，包含基本类型与引用类型)，但要求是final类型的；
 *      jdk1.8及以上不用显示定义成final，由编译器代劳，内部类不能改变局部变量的值(因为实际上是final的)
 * 3. final的基本类型，就是不能改变其值，则final的引用类型则是指不能再赋值，但可以改变引用的对象的内容；
 */
@Data
public class LocalClassTest {
    private String name = "univ";

    public void fn() {
        /**
         * 1. local类不能用修饰符，其作用域就是定义此类的方法fn
         */
        @Data
        class Local {
            /**
             * 1. local类可以外部类的成员
             * 2. 方法的修饰符依然有效，要为public,protected,private
             */
            public void sayHello() {
                System.out.println("hello, " + name);
            }
        }
        Local local = new Local();
        local.sayHello();
    }

    @Test
    public void test() {
        LocalClassTest localClassTest = new LocalClassTest();
        localClassTest.fn();
    }
    // ------------------------------------------------------------

    /**
     * 1. jdk1.7及以下，内部类要访问外部方法的局部变量，此变量必需定义为final；
     * 2. jdk1.8及以上，内部类可以访问外部方法的非局部变量了（编译器会默认将之作为final），前提是内部类不会改变局部变量的值；
     * @param city
     */
    public void fn1(/*final*/ String city) {
        @Data
        class Local1 {
            public void sayHello() {
                // local类可以外部方法形参，在jdk1.8及以上，city可以不用显示定义成final
                System.out.println("hello, " + city);

                // jdk1.8中，如果内部类不能作修改外部方法局部变量city的操作，这会违反final
                // city = "abc";
            }
        }
        Local1 local1 = new Local1();
        local1.sayHello();
        // ------------------------------------------------------------

        /*final*/ int i = 10;
        @Data
        class Local11 {
            public void sayHello() {
                // local类可以外部方法形参，在jdk1.8及以上，i可以不用显示定义成final
                System.out.println("hello, " + i);

                // jdk1.8中，如果内部类不能作修改外部方法局部变量i的操作，这会违反final
                // i = 2;
            }
        }
        Local11 local11 = new Local11();
        local11.sayHello();
    }

    @Test
    public void test1() {
        LocalClassTest localClassTest = new LocalClassTest();
        localClassTest.fn1("beijin");
    }
    // ------------------------------------------------------------

    /**
     * 引用类型(final)
     * @param demo
     */
    public void fn2(Demo demo) {

        @Data
        class Local2 {
            public void sayHello() {
                // local类可以外部方法的局部变量（引用类型）
                System.out.println("hello, " + demo.getAddress());

                // demo实际上是final的，不能重新赋值；
                // demo = new Demo();

                // 但可以改变内容
                demo.setAddress("xxxx");
            }
        }

        Local2 local2 = new Local2();
        local2.sayHello();
    }
    @Test
    public void test2() {
        LocalClassTest localClassTest = new LocalClassTest();
        localClassTest.fn2(new Demo());
    }
    // ------------------------------------------------------------
}

@Data
class Demo {
    private String address = "wuhan";
}
