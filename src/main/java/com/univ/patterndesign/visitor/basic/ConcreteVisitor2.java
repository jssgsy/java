package com.univ.patterndesign.visitor.basic;

/**
 * @author univ
 * 2022/4/22 12:43 下午
 */
public class ConcreteVisitor2 implements Visitor {
    @Override
    public void visitElement1(ConcreteElement1 element1) {
        System.out.println("ConcreteVisitor2访问ConcreteElement1了");
    }

    @Override
    public void visitElement2(ConcreteElement2 element2) {
        System.out.println("ConcreteVisitor2访问ConcreteElement2了");
    }
}
