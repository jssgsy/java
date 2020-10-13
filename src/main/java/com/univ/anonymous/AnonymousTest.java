package com.univ.anonymous;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/10/13 5:15 下午
 * @description 匿名类
 *
 * 注：不仅可以为接口创建匿名实现类，也可以为类创建匿名子类
 */
public class AnonymousTest {

    @Test
    public void testCreateAnonymousClass() {
        // 正常创建类
        AnonymousClass a = new AnonymousClass();
        AnonymousClass a1 = new AnonymousClass(1, 2);
        System.out.println(a.fn());
        System.out.println(a1.fn());

        // 为类创建匿名子类
        // 语法：在new Class()后添加{}即可，然后在{}使用想override的方法即可
        // 为类创建无参构造函数匿名子类
        AnonymousClass a2 = new AnonymousClass() {
            @Override
            public String fn() {
                // return super.fn();
                return "anonymous_class_with_no_arg";
            }
        };
        // 为类创建有参构造函数匿名子类
        AnonymousClass a3 = new AnonymousClass(1, 2) {
            @Override
            public String fn() {
                // return super.fn();
                return "anonymous_class_with_arg";
            }
        };
        System.out.println(a2.fn());
        System.out.println(a3.fn());

        // 为接口创建类名类
        // 语法：// 语法：在new Interface()后添加{}即可，然后在{}使用想override的方法即可
        AnonymousInterface a4 = new AnonymousInterface() {
            @Override
            public String fn(int i, int j) {
                return "anonymous_interface";
            }
        };
        System.out.println(a4.fn(1, 2));
    }

}

class AnonymousClass {
    AnonymousClass(){}
    AnonymousClass(int i, int j){}
    public String fn(){
        return "default";
    }
}

interface AnonymousInterface {
    String fn(int i, int j);
}