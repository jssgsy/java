package com.univ.patterndesign.decorator;

import org.junit.Test;

/**
 * 装饰器模式
 *
 * @author univ
 * @datetime 2018/11/6 9:13 AM
 * @description
 */
public class DecoratorTest {

    @Test
    public void test() {
        Component c1 = new ConcreteComponent1();
        Component c2 = new ConcreteComponent2();
        Decorator d1 = new ConcreteDecorator1();
        Decorator d2 = new ConcreteDecorator2();


        // 用d1对c1进行装饰,注意，装饰完后，d1已经是一个包裹着c1的Component(始终记住装饰器就是一种component)
        d1.setComponent(c1);
        d1.fn();

        // 用d1对c2进行装饰，注意，此种模式下一次只能装饰一个对象，没有链式装饰
       /* d1.setComponent(c2);
        d1.fn();*/

        // 重要，setComponent的参数类型为Component，而不论是具体的component还是decorator均是Component类型，链式装饰
        d2.setComponent(d1);
        d2.fn();
    }
}


/*
1. 这里演示有多个装饰器，且有多个对象需要被装的通用情形，可视具体情况进行“退化”，如不用Component接口，不用Decorator接口；
2. 重点：装饰器与被装饰的对象有共同的类型，所以装饰器(也是一种Component接口)也可以被装饰，这使得链式装饰成为可能；
 */


interface Component {
    void fn();
}

// 具体的要被装饰的类
class ConcreteComponent1 implements Component {
    @Override
    public void fn() {
        System.out.println("ConcreteComponent1::fn()");
    }
}

// 具体的要被装饰的类
class ConcreteComponent2 implements Component {
    @Override
    public void fn() {
        System.out.println("ConcreteComponent2::fn()");
    }
}

/*
1. 装饰器必然要有一个Component的引用，因为是要给其动态添加功能，所以Decorator不能是接口
2. 抽象Decorator的作用：不是必须，不过如果有多个装饰器，则每个装饰器都需要一个被装饰的作用，因此不如抽离共同部分出来，这就是抽象装饰器的由来
 */
abstract class Decorator implements Component {
    // 继承给具体的装饰器类使用
    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }
}

// 具体的装饰器
class ConcreteDecorator1 extends Decorator {

    @Override
    public void fn() {
        before();
        component.fn();
        after();
    }

    // 这里的before与after方法是装饰方法
    private void before() {
        System.out.println("ConcreteDecorator1 before...");
    }

    private void after() {
        System.out.println("ConcreteDecorator1 after...");
    }
}

// 具体的装饰器
class ConcreteDecorator2 extends Decorator {

    @Override
    public void fn() {
        System.out.println("ConcreteDecorator2 装饰之前");
        component.fn();
        System.out.println("ConcreteDecorator2 装饰之后");
    }
}