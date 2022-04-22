package com.univ.patterndesign.visitor.basic;

/**
 * @author univ
 * 2022/4/22 12:42 下午
 */
public class ConcreteVisitor1 implements Visitor {

    @Override
    public void visitElement1(ConcreteElement1 element1) {
        System.out.println("ConcreteVisitor1访问ConcreteElement1了");
    }

    @Override
    public void visitElement2(ConcreteElement2 element2) {
        System.out.println("ConcreteVisitor1访问ConcreteElement2了");
    }
}
