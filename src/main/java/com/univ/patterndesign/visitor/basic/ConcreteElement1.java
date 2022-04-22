package com.univ.patterndesign.visitor.basic;

/**
 * @author univ
 * 2022/4/22 12:38 下午
 */
public class ConcreteElement1 implements Element {

    /**
     * 所谓双重分派：
     * 第一次分派：将visitor当参数传递到这里的accept方法；
     * 第二次分派：将this当参数又传递给visitor；
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitElement1(this);
    }
}
