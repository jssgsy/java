package com.univ.patterndesign.facade;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/6 10:00 AM
 * @description
 */
public class FacadeTest {
    @Test
    public void test() {
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();
    }
}

// 子系统之一
class SubSystem1 {
    public void method1() {
        System.out.println("SubSystem1::method1");
    }
}

// 子系统之一
class SubSystem2 {
    public void method2() {
        System.out.println("SubSystem2::method2");
    }
}

// 子系统之一
class SubSystem3 {
    public void method3() {
        System.out.println("SubSystem3::method3");
    }
}

// 子系统之一
class SubSystemN {
    public void methodN() {
        System.out.println("SubSystemN::methodN");
    }
}

/*
1. 为子系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使用这一子系统更加容易使用
2. 常见应用场景：service层，有时因为业务复杂，某个业务逻辑需要多少service共同完成，此时可以定义一个对应的facade类供外界使用，此时外界不用知道具体的service，在facade中封装出外界需要的方法，
 */
class Facade {
    private SubSystem1 subSystem1;
    private SubSystem2 subSystem2;
    private SubSystem3 subSystem3;
    private SubSystemN subSystemN;

    public Facade(SubSystem1 subSystem1, SubSystem2 subSystem2, SubSystem3 subSystem3, SubSystemN subSystemN) {
        this.subSystem1 = subSystem1;
        this.subSystem2 = subSystem2;
        this.subSystem3 = subSystem3;
        this.subSystemN = subSystemN;
    }

    public Facade() {
        subSystem1 = new SubSystem1();
        subSystem2 = new SubSystem2();
        subSystem3 = new SubSystem3();
        subSystemN = new SubSystemN();
    }

    // 提供给外界的方法，此方法内部可能要由多个子系统一起协同完成，但对外界调用者而已，接口是简单的，一致的
    public void methodA() {
        subSystem1.method1();
        subSystem2.method2();
    }

    // 提供给外界的方法，此方法内部可能要由多个子系统一起协同完成，但对外界调用者而已，接口是简单的，一致的
    public void methodB() {
        subSystem3.method3();
        subSystemN.methodN();
    }
}

