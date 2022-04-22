package com.univ.patterndesign.visitor.basic;

import org.junit.Test;

/**
 * @author univ
 * 2022/4/22 12:49 下午
 */
public class VisitorTest {

    @Test
    public void test() {
        ConcreteVisitor1 visitor1 = new ConcreteVisitor1();
        ConcreteVisitor2 visitor2 = new ConcreteVisitor2();

        // 是访问者的入口，此模式的范式是：访问时一起访问所有的被访问者
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.handler(visitor1);
        objectStructure.handler(visitor2);

        System.out.println("------------------");
        // 当然也是可以只访问某一个被访问者，此时就没有ObjectStructure存在的意义了
        ConcreteElement1 element1 = new ConcreteElement1();
        element1.accept(visitor1);

    }
}
