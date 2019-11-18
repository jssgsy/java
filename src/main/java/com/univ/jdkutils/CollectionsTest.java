package com.univ.jdkutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/11/1 4:22 PM
 * @description
 * reverse：集合元素翻转；
 */
public class CollectionsTest {
    
    @Test
    public void reverse() {
        Integer[] arr = new Integer[10];
        Arrays.setAll(arr, i -> i * 3);
        List<Integer> list = Arrays.asList(arr);
        System.out.println(list);// [0, 3, 6, 9, 12, 15, 18, 21, 24, 27]
        Collections.reverse(list);
        System.out.println(list);// [27, 24, 21, 18, 15, 12, 9, 6, 3, 0]
    }
    
    @Test
    public void sort() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 10, 2, 31, 4);

        // 顺序排序
        Collections.sort(list);
        System.out.println(list);

        // 指定排序规则
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);
    }

    /**
     * 方便填充集合元素
     */
    @Test
    public void addAll() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);
        System.out.println(list);
    }
    
    @Test
    public void emptyCollection() {
        Collections.emptyList();
        
        Collections.emptySet();
        
        Collections.emptyMap();
        
        // 空枚举
        Collections.emptyEnumeration();
    }
    
    // 返回一个有且只能有一个元素的集合，此时不能再往其中添加元素
    @Test
    public void singleton() {
        // set
        Collections.singleton(10);

        Collections.singletonList(10);
        
        Collections.singletonMap("name", "zhangsan");
    }
    
    @Test
    public void fill() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);
        System.out.println(list);

        // 将集合的元素全部替换成某个元素
        Collections.fill(list, 10);
        System.out.println(list);
    }

    /**
     * 取集合元素的最大值，最小值
     */
    @Test
    public void math() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);

        Integer max = Collections.max(list);
        Integer min = Collections.min(list);
        System.out.println(max);
        System.out.println(min);
    }

    /**
     * 将集合元素中的某个元素统一替换成另一个元素
     */
    @Test
    public void replaceAll() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 1, 4);

        // 将集合中所有的1替换成10
        Collections.replaceAll(list, 1, 10);
        System.out.println(list);
    }

    /**
     * 返回一个按自然顺序降序的比较器
     * 还是有作用的，比如对Integer或者String集合排序时默认是自然顺序升序，如果要倒序，则需要自己实现一下(虽然很简单)，这个方法相当于帮自己省去了一步
     */
    @Test
    public void reverseOrder() {

        Comparator<Integer> objectComparator = Collections.reverseOrder();

        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4);

        // 自然顺序降序
        Collections.sort(list, objectComparator);
        // 相当于如下代码
        /*Collections.sort(list, (first, second) -> {
            if (first < second) {
                 return 1;
            } else if (first == second) {
                return 0;
            } else
                return -1;
        });*/

        System.out.println(list);
    }

    // 随机打乱集合元素顺序
    @Test
    public void shuffle() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8);

        Collections.shuffle(list);
        System.out.println(list);
    }
}
