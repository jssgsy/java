package com.univ.patterndesign.factory;

import org.junit.Test;

/**
 * Univ
 * 16/9/17 15:15
 */

/**
 * 抽象工厂模式
 *      产品族的概念不太好解释,多看几遍代码的结构便能理解。
 */
public class AbstractFactoryTest {

    @Test
    public void test1(){
        //生成宝马系列
        CarFactory carFactory = new BMFactory();
        Engine engine = carFactory.createEngine();
        Seat seat = carFactory.createSeat();
        engine.showWeight();//BM Engine
        seat.showMaterial();//BM Seat

        //生成大众系列
        CarFactory carFactory1 = new DZFactory();
        Engine engine1 = carFactory1.createEngine();
        Seat seat1 = carFactory1.createSeat();
        engine1.showWeight();//DZ Engine
        seat1.showMaterial();//DZ Seat

    }
}


/**
 * 抽象产品接口:引擎
 */
interface Engine{

    void showWeight();
}

/**
 * 抽象产品接口:座位
 */
interface Seat{

    void showMaterial();
}

/**
 * 具体产品:宝马车的引擎
 */
class BMEngine implements Engine{

    @Override
    public void showWeight() {
        System.out.println("BM Engine");
    }
}

/**
 * 具体产品:宝马车的座位
 */
class BMSeat implements Seat{

    @Override
    public void showMaterial() {
        System.out.println("BM Seat");
    }
}

/**
 * 具体产品:大众车的引擎
 */
class DZEngine implements Engine{

    @Override
    public void showWeight() {
        System.out.println("DZ Engine");
    }
}

/**
 * 具体产品:大众车的座位
 */
class DZSeat implements Seat{

    @Override
    public void showMaterial() {
        System.out.println("DZ Seat");
    }
}

/**
 * 抽象工厂:生成的是一个完整的车,而车是由引擎和座位(这里引擎和座位就是一个产品族)组成的。
 *      所以这里应该即提供生成引擎的方法,也应该提供生成座位的方法
 */
interface CarFactory{

    /**
     * 创建整个产品族(car)中的一部分:引擎
     * @return
     */
    Engine createEngine();

    /**
     * 创建整个产品族(car)中的一部分:座位
     * @return
     */
    Seat createSeat();
}

/**
 * 具体工厂:生成宝马车产品族的工厂
 */
class BMFactory implements CarFactory{

    @Override
    public Engine createEngine() {
        return new BMEngine();
    }

    @Override
    public Seat createSeat() {
        return new BMSeat();
    }
}

/**
 * 具体工厂:生成大众车产品族的工厂
 */
class DZFactory implements CarFactory{

    @Override
    public Engine createEngine() {
        return new DZEngine();
    }

    @Override
    public Seat createSeat() {
        return new DZSeat();
    }
}