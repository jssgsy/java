package com.univ.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
         * 可以修改此枚举的值，当然，前提是没有将此字段设为final且有提供setter方法
         */
        spring.setName("spring");
        spring.setQuarter("第一季度");
        System.out.println("Season.SPRING.toString()为: " + spring.toString());

    }

    /**
     * 实现接口的枚举
     */
    @Test
    public void interfaceEnum() {
        System.out.println(Food.Coffee.BLACK_COFFEE);
        System.out.println(Food.Coffee.BLACK_COFFEE.fn("abc"));
        System.out.println(Food.Coffee.DECAF_COFFEE.fn("abc"));
    }

    /**
     * 结合策略模式用的枚举
     */
    @Test
    public void strategyEnum () {
        // 没有覆写直接抛异常
//        StragtegyEnum.TYPE_ONE.execute("abc");
        System.out.println(StragtegyEnum.TYPE_TWO.execute("hello"));
        System.out.println(StragtegyEnum.TYPE_THREE.execute("ha ha"));
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
 * 可达到将枚举元素进行分组的目的。
 */
interface Food {
    // 枚举就是一个类，实现接口必然要实现接口中的方法
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO;

        /**
         * 注，此时此方法是枚举维度的，而不是枚举实例维度，若要枚举实例维度，参见{@link StragtegyEnum}
         */
        @Override
        public String fn(String str) {
            return str + "_coffee";
        }
    }

    enum Dessert implements Food {
        FRUIT, CAKE, GELATO;

        @Override
        public String fn(String str) {
            return str + "_dessert";
        }
    }

    String fn(String str);
}


/**
 * 枚举实现多态-每个【枚举实例】有自己个性化的行为。
 * 使用场景：类似于策略模板，只是和enum结合了而已。
 * 有多个枚举，同时需要根据不同枚举计算出一个不同的值，如文件夹路径。
 * 当然可以弄一个类似util的类来处理，
 * 但枚举、根据枚举计算值这两者显然是紧相关的，因此应该放一起处理，也便于集中管理；不用引入没必要的类
 *
 * 补充：注意与上述枚举实现接口的区别：
 * * 枚举实现接口：实现的方法是【枚举】维度，而不是【枚举实例】维度，显然这不是用来实现接口的用处，因此不实现接口也可实现；
 * * 策略枚举：行为是【枚举实例】维度；
 */
@Getter
@AllArgsConstructor
enum StragtegyEnum {
    // 此实例调用execute方法直接就抛异常UnsupportedOperationException
    TYPE_ONE(1, "模拟类型1"),

    // 本质其实是个匿名子类
    TYPE_TWO(2, "模拟类型2") {
        @Override
        public <T> String execute(T id) {
            if (id instanceof String) {
                return (String) id;
            }
            return "type_two";
        }
    },
    TYPE_THREE(3, "模拟类型3") {
        @Override
        public <T> String execute(T id) {
            if (id instanceof Integer) {
                return String.valueOf(id);
            }
            return "type_three";
        }
    },

    ;

    // 和泛型结合使用也是ok的
    public <T> String execute(T id) {
        throw new UnsupportedOperationException("子类必须覆写");
    }
    private final Integer type;
    private final String desc;
}

