package com.univ.generic.advance;

import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Test;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author univ
 * @date 2019/2/28 7:19 PM
 * @description 泛型的深入理解
 *
 * 对PECS(extends:produce, super:consumer)的理解：
 * 入参：用super
 * 出参：用extends，因为是出参，所以外部必定需要某个类型来接收，因此只能是extends，否则接不住
 * 举例：Predicate是入参，用? super T
 *      Function的第一个参数是入参，用? super T
 *      Function的第二个参数是出参，用? extends T
 *      Supplier是出参，用? extends T
 *
 * 很多类(如Optional)类中的频繁使用? super T或者? extends U，不是必须，但使得程序更灵活，健壮
 *
 */
public class AdvanceGenericTest {

    /**
     * 理解Optional类filter方法的签名：Optional<T> filter(Predicate<? super T> predicate)
     * 注意，这里的入参类型是 ? super T
     * 顺便理解入参用? super T
     */
    @Test
    public void test1() {
        Son son = new Son().setId(20);
        // 以这种方式使用的时候，因为Optional中的value是Son类型，因此这里的t必定是Son类型，所以这里t直接用T类型即可
        Optional.of(son).filter(t -> t.getId() > 30).orElse(new Son());

        // 接上，明明定义成T即可，为何还要定义成? super T 呢？这是因为还可以接收一个外部Predicate类型的变量，
        // 此时外部Predicate的类型不一定非是Son，见下例

        // 注意，此时t只是静态类型是Father，实际类型其实还是Son，因为其是Optional中的Son
        Predicate<Father> predicate = t -> t.getId() > 3;
        // Predicate<Father> predicate = t -> t.getId2() > 3; // 此时抛异常，因为Son没有id2字段
        Son son1 = Optional.of(son).filter(predicate).orElse(new Son());
        System.out.println(son1);
    }

    /**
     * 理解出参用? extends U：既然是出参，则外部必定需要某个类型来接收此出参，所以必须用extends
     * map方法的签名：public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
     *  其中定义了新的类型U，第二个？指定了返回类型必须为U的子类
     */
    @Test
    public void test2() {
        Son son = new Son().setId(20);
        Integer integer = Optional.of(son).map(Son::getId).orElse(34);
        System.out.println(integer);


        Father father = new Father();
        // 更因为定义成了? extends U，所以这里map才可以返回U(Father)的子对象son
        Son s1 = Optional.of(father).map(t -> son).orElse(new Son());
    }
}

@Data
@Accessors(chain = true)
class Father {
    private Integer id;

    private Integer id2;
}

@Data
@Accessors(chain = true)
class Son extends Father {

    private Integer id;

    private String name = "son_name";
}