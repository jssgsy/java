package com.univ.patterndesign.composite;

import org.junit.Test;

/**
 * Univ
 * 16/9/30 17:03
 */
public class JUnitTest {

    @Test
    public void test1(){
        Component root = new Composite("root");//根节点

        Component leaf1 = new Leaf("leaf1");//叶子节点
        Component leaf2 = new Leaf("leaf2");//叶子节点
        root.add(leaf1);
        root.add(leaf2);

        Component composite = new Composite("composite1");//非叶子节点
        Component leaf11 = new Leaf("leaf11");//叶子节点
        Component leaf12 = new Leaf("leaf12");//叶子节点
        composite.add(leaf11);
        composite.add(leaf12);
        root.add(composite);

        root.show();
        System.out.println("-----------删除非叶子节点----------");
        root.remove(composite);
        root.show();

    }

}
