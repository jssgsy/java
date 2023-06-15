package com.univ.jdk8.stream;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * @author univ
 * @date 2019/2/22 4:43 PM
 * @description Collectors工具类测试
 */
public class CollectorsTest {

    private final List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");

    private final List<Entity> objList = Arrays.asList(
            new Entity(10, "zhangsan"),
            new Entity(30, "wangwu"),
            new Entity(20, "lisi"),
            new Entity(50, "hello"),
            new Entity(40, "world"),
            new Entity(50, "sam"),
            new Entity(20, "bob")
    );

    /**
     * Collectors.toList()
     */
    @Test
    public void testToList() {
        // 将stream中的元素组装成list
        List<String> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * Collectors.toMap: 将stream中的元素以某种规则组装成map返回
     * 注意：放入map中的key必须唯一，否则报Duplicate key xxx的异常
     */
    @Test
    public void testToMap() {
        Map<Integer, Entity> collect = objList.stream().collect(Collectors.toMap(Entity::getAge, Function.identity()));
        System.out.println(collect);
    }

    /**
     * 转成map时，如果key有重复的，默认情况会抛异常，可以使用第三个参数来选择第一个还是第二个
     */
    @Test
    public void testToMap2() {
        // 当碰到重复的key时选择第一个key
        Map<Integer, Entity> collect = objList.stream().collect(Collectors.toMap(t -> t.getAge(), Function.identity(), (t1, t2) -> t2));
        System.out.println(collect);
    }

    /**
     * 转成map时，默认是用HashMap来承接，因为是HashMap固然就不能保证顺序，
     * 所以如果想将list围成map时保持list中元素的顺序，可以指定用什么类型的Map来承接，这里即LinkedHashMap
     */
    @Test
    public void testToMap3() {
        Map<Integer, Entity> collect = objList.stream().collect(Collectors.toMap(t -> t.getAge(), Function.identity(), (t1, t2) -> t2, LinkedHashMap::new));
        // 注意观察结果key的顺序，是和objList中的顺序保持一致的
        System.out.println(collect);
    }

    /**
     * Collectors.groupingBy: 将stream中的元素以某种规则分组
     * 注意，此时返回的是一个map(因为是分组内容)
     */
    @Test
    public void testGroup() {
        Map<Integer, List<Entity>> collect = objList.stream().collect(Collectors.groupingBy(Entity::getAge));
        System.out.println(collect);
    }




}

@Data
@AllArgsConstructor
class Entity {
    private Integer age;
    private String name;
}
