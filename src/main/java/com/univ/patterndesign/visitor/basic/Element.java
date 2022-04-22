package com.univ.patterndesign.visitor.basic;

/**
 * 被访问者的抽象定义
 *
 * 重点：被访问者的个数是固定的、不变的，因为visitor需要事先针对每个Element定义好方法。
 * @see Visitor
 *
 * @author univ
 * 2022/4/22 12:36 下午
 */
public interface Element {

    /**
     * 定义一个接收访问者访问的方法<br>
     *
     * 注：
     * 1. 这里访问者是抽象的；
     * 2. 这样理解此方法：此被访问者被访问时需要做的事情
     * @param visitor
     */
    void accept(Visitor visitor);
}
