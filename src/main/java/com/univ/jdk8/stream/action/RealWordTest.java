package com.univ.jdk8.stream.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * 实际工作中碰到的
 *
 * @author univ date 2023/6/14
 */
public class RealWordTest {

    /**
     * map按照key排序。
     * 直接使用TreeMap即可。
     *
     * 重点：TreeMap构造函数的Comparator的类型就是key！
     */
    @Test
    public void mapOrderByKey() {
        Map<String, Long> map = new HashMap<>();
        map.put("aaa", 12L);
        map.put("bbb", 9L);
        map.put("ccc", 40L);
        map.put("ddd", 14L);
        map.put("eee", 100L);
        map.put("fff", 8L);
        System.out.println("before sort: " + map);
        // 重点：TreeMap构造函数的Comparator的类型就是key，查看一下其定义，这也是为何能直接使用TreeMap的原因所在
        TreeMap<String, Long> treeMap = new TreeMap<>(String::compareTo);
        treeMap.putAll(map);
        System.out.println("after sort: " + treeMap);
    }

    /**
     * map按照value排序
     *
     * 大致思路：
     *  map及其流是没有提供sort方法的，本身map就是无序的；
     *  map的entrySet是一个Set，set是能排序的，因此从entrySet身上着手，这是重点
     *  LinkedHashMap是有顺序的概念的，因此必然要先原来的map转成这种类型；
     */
    @Test
    public void mapOrderByValue() {
        Map<String, Long> map = new HashMap<>();
        map.put("aaa", 12L);
        map.put("bbb", 9L);
        map.put("ccc", 40L);
        map.put("ddd", 14L);
        map.put("eee", 100L);
        map.put("fff", 8L);

        System.out.println("before sort: " + map);
        LinkedHashMap<String, Long> linkedHashMap = map.entrySet().stream()
            // 正序
            // 重点是；Entry本身提供了一些Comparator，如这里的byValue
//            .sorted(Entry.comparingByValue())

            // 倒序
            // 注，这里不能使用Entry.comparingByValue().reversed()，类型匹配不上了。原因还需要深究
            // 启示：并不是不能使用reversed()来反转排序规则就做不了倒序，兜底还有原生的Comparator可写；
            .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (t1, t2) -> t1, LinkedHashMap::new));
        System.out.println("after sort: " + linkedHashMap);
    }
}
