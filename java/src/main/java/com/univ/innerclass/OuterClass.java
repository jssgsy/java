package com.univ.innerclass;

/**
 * Univ
 * 16/8/23 13:58
 */
public class OuterClass{

    private String name = "outerClassName";

    private static String address = "OuterClass_address";//注意是static修饰

    /**
     * 1. 内部类(静态或者非静态内部类)的修饰符的作用和属性的修饰符的作用是一样的。
     *      这里InnerClass的修饰符是默认的包修饰符,说明不能在此包以外的地方创建(访问)InnerClass对象。
     *
     * 2. 非静态内部类可以直接访问外部类的属性字段(不论其修饰符是什么),
     *      这也是非静态内部类与静态内部类最大的区别:非静态内部类对象持有外部类对象的引用;
     */
    class InnerClass{
        public String getName() {
            return name;
        }

        /**
         * 非静态内部类可以修改外部类的属性.
         * 注意这里的参数名为str而不是name(参考下面的setName2方法)
         */
        public void setName(String str){
            name = str;
        }

        /**
         * 注意内部类访问外部类属性的方式:OuterClass.this.name
         */
        public void setName2(String name){
            OuterClass.this.name = name;
        }
    }

    /**
     * 在外部类中提供一个方法返回一个内部类的实例,
     * 这比直接使用oc.new InnerClass()更直观。
     * @return 内部类对象
     */
    public InnerClass createInnerClass(){
        return new InnerClass();
    }


    static class InnerStaticClass{
        private int age = 20;

        /**
         * 静态内部类不能访问外部类的非static属性与非static方法
         */
        /*public String getName() {
            return name;
        }*/

        /**
         * 静态内部类虽然不能访问外部类的非static属性与方法,但可以访问和修改外部类的static属性与static方法
         * @return
         */
        public String getAddress(){
            return address;
        }
        public void setAddress(String address){
            OuterClass.address = address;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    /**
     * 在外部类中提供一个方法返回一个静态内部类的实例,慎用,因为静态内部类对象并不依赖外部类实例而存在。
     * 应该直接使用new OuterClass.InnerStaticClass()创建静态内部类对象。
     * @return  静态内部类对象
     */
    public InnerStaticClass createInnerStaticClass(){
        return new InnerStaticClass();
    }

}
