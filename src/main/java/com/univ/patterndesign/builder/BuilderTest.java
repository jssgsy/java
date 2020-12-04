package com.univ.patterndesign.builder;

import org.junit.Test;

import lombok.Data;

/**
 * @author univ
 * @datetime 2018/11/6 4:11 PM
 * @description 建造者模式
 */
public class BuilderTest {
    @Test
    public void test() {
        // 客户端需要知道指导员的存在
        Director director = new Director();

        // 客户也需要知道自己想要什么样的产品(一个建造建造一种类型的产品)
        CarBuilder carBuilder = new MichelinCarBuilder();

        // 客户端不需要知道具体的构建过程。由指导者指导建造者进行建造即可
        Car car = director.guide(carBuilder);
        System.out.println(car);
    }

}

/**
 * 要建造的对象，通常比较复杂，是由其它好几个对象组合而成
 */
@Data
class Car {
    // 使用接口而不是具体的类，这样就能生产出不同品牌的轮胎与引擎
    private Wheel wheel;
    private Engineer engineer;
}
interface Wheel {
    Wheel makeWheel();
}
// 米其林
@Data
class MichelinWheel implements Wheel {
    private String name;

    public MichelinWheel(String name) {
        this.name = name;
    }

    @Override
    public Wheel makeWheel() {
        return this ;
    }
}
// 马牌
@Data
class HorseWheel implements Wheel {
    private String name;

    public HorseWheel(String name) {
        this.name = name;
    }

    @Override
    public Wheel makeWheel() {
        return this;
    }
}

interface Engineer {
    Engineer makeEngineer();
}
@Data
class MichelinEngineer implements Engineer {
    private String name;

    public MichelinEngineer(String name) {
        this.name = name;
    }

    @Override
    public Engineer makeEngineer() {
        return this;
    }
}
@Data
class HorseEngineer implements Engineer {

    private String name;

    public HorseEngineer(String name) {
        this.name = name;
    }

    @Override
    public Engineer makeEngineer() {
        return this;
    }
}

/**
 * 职责：定义组成产品的各个部分的创建
 */
abstract class CarBuilder {
    // 要最终建造的产品
    protected Car car = new Car();

    /**
     * 因为构造的是对象的一部分，因此这里直接返回void即可，外界不会用到这个小对象，按照实际情况使用即可
     */
    public abstract void buildWheel();
    public abstract void buildEngineer();

    // 获取最后建造完毕的产品
    public abstract Car getResult();
}
// 会构建米其林车的工人
class MichelinCarBuilder extends CarBuilder {

    @Override
    public void buildWheel() {
        car.setWheel(new MichelinWheel("米其林轮胎"));
    }

    @Override
    public void buildEngineer() {
        car.setEngineer(new MichelinEngineer("米其林发动机"));
    }

    @Override
    public Car getResult() {
        return car;
    }
}
// 会构建马牌车的工人
class HorseCarBuilder extends CarBuilder {
    @Override
    public void buildWheel() {
        car.setWheel(new MichelinWheel("米其林轮胎"));
    }

    @Override
    public void buildEngineer() {
        car.setEngineer(new MichelinEngineer("米其林发动机"));
    }

    @Override
    public Car getResult() {
        return car;
    }
}

// 指导者，用来指导Builder建造整个产品的流程、顺序
class Director {

    /**
     * 也可以将builder作为Director的成员变量，不过这样的这么话就需要先设置builder，然后才能调用guide方法，不如将builder作为入参来得方便
     * 感觉这里甚至可以将方法设为static，这样Director的含义更纯粹
     * @param carBuilder
     * @return
     *
     * 补充：注意这里是如何体现【使得同样的构建过程可以创建不同的表示】的，这里构建过程是稳定的(封装在这一个方法中，先构建谁、后构建谁都已经确定了)，
     * 但因为不同部分都是接口表示，所以最终的产品是由具体的Builder决定的。
     */
    public Car guide(CarBuilder carBuilder) {
        /**
         * director把建造的流程告诉builder，即以什么样的顺序来构造(前提就是要构造的对象的过程是较为稳定的)
         * 引擎和发动机缺一不可(实际情况中，还可能有顺序关系)
         */
        carBuilder.buildEngineer();
        carBuilder.buildWheel();
        return carBuilder.getResult();
    }
}
