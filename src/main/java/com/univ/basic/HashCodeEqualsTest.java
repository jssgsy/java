package com.univ.basic;

import org.junit.Test;

import java.util.*;

/**
 * hashCode与equals方法
 * 如果某个类实现了equals方法，则必须同时实现hashCode方法，因为hashCode有如下规范：
 *      即如果两个对象的equals方法结果相等，则此两个对象的hashCode结果也必须相等，如下：
 *      If two objects are equal according to the equals(Object) method,
 *      then calling the hashCode method on each of the two objects must produce the same integer result
 */
public class HashCodeEqualsTest {

    /**
     * 比较两个String对象是否相等，应该使用Objects.equals(str1, str2)方法，不要使用==
     */
    @Test
    public void fn () {
        String str1 = new String("hello");
        String str2 = new String("hello");
        System.out.println(Objects.equals(str1, str2)); // true
        System.out.println(str1 == str2);   // false
    }

    /**
     * 如果某个类实现了equals方法，则必须同时实现hashCode方法
     */
    @Test
    public void fn2() {
        A a1 = new A("hello");
        A a2 = new A("hello");
        A a3 = new A("hello");
        A a4 = new A("hello");
        Map<A, Integer> map = new HashMap<>();
        map.put(a1, 20);
        map.put(a2, 20);
        map.put(a3, 20);
        map.put(a4, 20);
        Set set = map.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.print(entry.getKey());
            System.out.print(" : " + entry.getValue());
            System.out.println();
        }
        // 输出：如果A只实现equals方法，则map中会有重复的对象，如果同时实现了hashCode方法，则map中只会有一个对象，这也正是所期望的
    }
}

class A {
    private String str;

    public A(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    /**
     * 此时如果不实现hashCode方法，会发生虽然a1与a2确实equals相等，但hashCode竟然不等，带来两个后果：
     * 1. 违反了hashCode方法的规约；
     * 2. hashMap中会出现“相同”的元素；
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return Objects.equals(str, a.str);
    }

    @Override
    public String toString() {
        return "A{" +
                "str='" + str + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
