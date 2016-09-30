package com.univ.patterndesign.composite;

/**
 * Univ
 * 16/9/30 16:55
 */

/**
 * 组合模式:
 *  将对象组合成树形结构以表示“部分整体”的层次结构。组合模式使得用户对单个对象和使用具有一致性。
 *  它使树型结构的问题中，模糊了简单元素和复杂元素的概念
 */

/**
 * 节点的抽象表示,表示一个节点。
 */
public abstract class Component {

    private String name;

    public Component(){}

    public Component(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void show();

    //这里还可以新增判断是否为叶子节点和非叶子节点的方法,这里为了突出组合模式的重点就略过。
/*    public abstract boolean isLeaf();
    public abstract boolean isComposite();*/


}
