package com.univ.clone;

/*
1. 任意数组类型都需要深拷贝；
2. 任意数组类型都可以直接调用clone方法
 */
public class ArrCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        B b = new B("sample_name");
        A a = new A(28, b);
        System.out.println(a.arr[0]);

        A a1 = (A) a.clone();
        System.out.println(a1.arr[0]);

        // 修改原数组中的内容
        a.arr[0] = 100;

        System.out.println(a.arr[0]);
        System.out.println(a1.arr[0]);
    }
}

class B implements Cloneable{
    private String name;

    public B(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class A implements Cloneable {
    private int age;
    private B b;
    /**
     * 任意数组类型都可以直接调用clone方法
     */
    public int[] arr = {1, 2, 3};

    public A(int age, B b) {
        this.age = age;
        this.b = b;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        A a = (A) super.clone();
        a.b = (B) b.clone();
        /*
        数组类型必须要深拷贝，且任意数组类型都可直接调用clone方法
         */
        a.arr = arr.clone();
        return a;
    }
}

