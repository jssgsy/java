package com.univ.enumeration;

import org.junit.Test;

/**
 * Univ
 * 16/8/26 16:34
 */

/**
 * 演示枚举类型较高级的用法
 *
 * 1. 所有的枚举都继承自java.lang.Enum类。由于Java不支持多继承，所以枚举类不能再继承其他类。当然枚举类是可以实现接口的。
 * 2. 还有EnumMap与EnumSet,可以看一看。
 */
public class AdvanceEnumTest {

    @Test
    public void test(){

        Season spring = Season.SPRING;
        System.out.println("Season.SPRING.toString()为: " + spring.toString());
        /**
         * 可以修改此枚举的值
         */
        spring.setName("spring");
        spring.setQuarter("第一季度");
        System.out.println("Season.SPRING.toString()为: " + spring.toString());

    }
}

/**
 * 可以为enum类添加属性与方法。
 * 1. 自定义属性或者方法时,必须将属性或者方法放到定义的enum实例后;
 */
enum Season{
    SPRING, //创建一个Season对象,调用无参构造函数
    SUMMER("sumer","第二季度"), //创建一个Season对象,调用有参构造函数
    FALL("fall","第三季度"),    //如果不覆写toString()方法,则此对象的toString()返回仍为"FALL"而不是fall
    WINTER("winter","第四季度");    //如果要自定义方法,则这里分号不可少

    //为枚举类添加属性
    private String name;
    private String quarter;//属于哪个季度

    Season(){}

    /**
     * 枚举类型的构造函数只能是private修饰(可省略),因为不能在外部创建enum实例
     */
    Season(String name, String quarter){
        this.name = name;
        this.quarter = quarter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    /**
     * 一般为枚举类添加了属性的时候可考虑重写toString方法,使得输出更友好。
     */
    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", quarter='" + quarter + '\'' +
                '}';
    }
}

/**
 * 无法从enum继承子类，如果需要扩展enum中的元素，在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组。
 * 达到将枚举元素进行分组的目的。
 */
interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
