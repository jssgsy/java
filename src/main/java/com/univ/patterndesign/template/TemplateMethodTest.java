package com.univ.patterndesign.template;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/6 11:06 AM
 * @description 模板方法模式
 */
public class TemplateMethodTest {

    @Test
    public void test() {
        AbstractClass a = new A();
        a.process();

        AbstractClass a1 = new B();
        a1.process();
    }
}

abstract class AbstractClass {

    // 固定的步骤
    public void step1() {
        System.out.println("AbstractClass step1");
    }

    // 固定的步骤
    public void step2() {
        System.out.println("AbstractClass step2");
    }

    // 由子类实现的步骤
    public abstract void step3();

    // 由子类实现的步骤
    public abstract void step4();

    // 固定的步骤
    public void stepn() {
        System.out.println("AbstractClass stepn");
    }

    /**
     * 这是一个算法的骨架(流程)
     * 一般定义成final，避免子类重写
     */
    public final void process() {
        step1();
        step2();
        // 将具体子类的实现嵌入到整个流程中
        step3();

        // 可以通过勾子来决定是否要嵌入某些步骤
        if (useStep4()) {
            step4();
        }

        stepn();
    }

    /**
     * 勾子方法，子类可覆写
     * @return
     */
    protected boolean useStep4() {
        return true;
    }
}

class A extends AbstractClass{

    @Override
    public void step3() {
        System.out.println("子类A：step3");
    }

    @Override
    public void step4() {
        System.out.println("子类A：step4");
    }

    @Override
    protected boolean useStep4() {
        // 业务逻辑处理，决定是否开启
        return false;
    }
}

class B extends AbstractClass{

    @Override
    public void step3() {
        System.out.println("子类B：step3");
    }

    @Override
    public void step4() {
        System.out.println("子类B：step4");
    }

    @Override
    protected boolean useStep4() {
        // 业务逻辑处理，决定是否开启
        return true;
    }
}


