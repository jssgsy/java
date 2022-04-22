package com.univ.patterndesign.visitor.basic;

/**
 * @author univ
 * 2022/4/22 12:40 下午
 */
public class ConcreteElement2 implements Element {

    @Override
    public void accept(Visitor visitor) {
        visitor.visitElement2(this);
    }
}
