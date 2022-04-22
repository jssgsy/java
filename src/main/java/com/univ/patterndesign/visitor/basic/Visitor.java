package com.univ.patterndesign.visitor.basic;

/**
 * 访问者的抽象定义
 * @author univ
 * 2022/4/22 12:36 下午
 */
public interface Visitor {

    /**
     * 针对element的组成部分-ConcreteElement1的访问逻辑
     *
     * 当此访问者访问ConcreteElement1时做什么
     * @param element1
     */
    void visitElement1(ConcreteElement1 element1);

    /**
     * 针对element的组成部分-ConcreteElement2的访问逻辑
     *
     * 当此访问者访问ConcreteElement2时做什么
     * @param element2
     */
    void visitElement2(ConcreteElement2 element2);

    /**
     * 不能再新增了，因为不可能再有其它的被访问者(Element)了，否则不该使用此模式
     */
}
