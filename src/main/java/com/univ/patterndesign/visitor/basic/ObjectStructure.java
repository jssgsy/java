package com.univ.patterndesign.visitor.basic;

import java.util.Arrays;
import java.util.List;

/**
 * 很容易，其实就是一个容器，这里包含了的元素，且将访问所有元素(被访问者)的逻辑放到了这里，无它
 * @author univ
 * 2022/4/22 12:45 下午
 */
public class ObjectStructure {

    /**
     * 保存所有的被访问者
     *
     * 实际使用中灵活应用
     */
    private List<Element> elementList = Arrays.asList(new ConcreteElement1(), new ConcreteElement2());

    /**
     * 相当于是对访问者的入口
     *
     * 重点：访问者访问时是要一起访问所有被访问者的
     * @param visitor
     */
    public void handler(Visitor visitor) {
        for (Element element : elementList) {
            element.accept(visitor);
        }
    }
}
