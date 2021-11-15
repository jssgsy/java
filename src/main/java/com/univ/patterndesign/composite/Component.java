package com.univ.patterndesign.composite;

/**
 * Univ
 * 16/9/30 16:55
 */

/**
 * 组合模式:
 *  将对象组合成树形结构以表示“部分整体”的层次结构。组合模式使得用户对单个对象和使用具有一致性。
 *  它使树型结构的问题中，模糊了简单元素和复杂元素的概念
 *
 *  这里是【透明】的组合模式：将add、remove等方法定义在了Component而不是Composite中
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

    /**
     * update: 这里不应该将add,remove等方法定义成abstract修饰,因为并不是所有的子类都需要实现,
     * 这里的情况恰恰相反,子类叶子节点不用实现此两个方法,所以正确的做法应该是在这里默认实现add,remove方法,
     * 并抛出UnsupportedOperationException异常,有需要的子类可以覆写此方法。
     * 这才是良好的设计(集合框架中就有这样的身影,如AbstractCollection中的add方法)。
     * @param component
     */

    public abstract void add(Component component);

    public abstract void remove(Component component);

    /**
     * 重点：
     * 真正的业务逻辑代码，非叶子结点仅仅只是委托给其子结点
     * 所以这里的逻辑最终是由叶子结点来实现的。
     */
    public abstract void show();

    //这里还可以新增判断是否为叶子节点和非叶子节点的方法,这里为了突出组合模式的重点就略过。
/*    public abstract boolean isLeaf();
    public abstract boolean isComposite();*/


}
