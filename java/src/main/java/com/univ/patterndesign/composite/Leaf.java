package com.univ.patterndesign.composite;

/**
 * Univ
 * 16/9/30 16:57
 */

/**
 * 叶子节点
 */
public class Leaf extends Component{
    public Leaf(){}

    public Leaf(String name){
        super(name);
    }

    /**
     * 叶子节点不能新增其他节点
     * @param component
     */
    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("不允许新增,此节点是叶子节点: " + getName());
    }

    /**
     * 叶子节点不能删除其他节点(根本就没有子节点)。节点是由父节点删除的
     * @param component
     */
    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("不允许删除,此节点是叶子节点: " + getName());

    }

    @Override
    public void show() {
        System.out.println(getName());
    }

}
