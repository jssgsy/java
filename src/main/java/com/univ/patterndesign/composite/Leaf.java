package com.univ.patterndesign.composite;

/**
 * Univ
 * 16/9/30 16:57
 */

/**
 * 叶子节点
 *
 * update: 这里应该移除add,remove等不支持操作的方法,应该将其在父类中默认实现为抛出UnsupportedOperationException异常,
 * 并在需要支持的子类中覆写,这才是良好的设计(集合框架中就有这样的身影,如AbstractCollection中的add方法)。
 */
public class Leaf extends Component{
    public Leaf(){}

    public Leaf(String name){
        super(name);
    }

    /**
     * 叶子节点不能新增其他节点
     * @param component
     *
     * update:此方法应该上移至父类并默认实现为抛出UnsupportedOperationException异常
     */
    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("不允许新增,此节点是叶子节点: " + getName());
    }

    /**
     * 叶子节点不能删除其他节点(根本就没有子节点)。节点是由父节点删除的
     * @param component
     *
     * update:此方法应该上移至父类并默认实现为抛出UnsupportedOperationException异常
     */
    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("不允许删除,此节点是叶子节点: " + getName());

    }

    /**
     * 重点：由叶子结点实现最终的业务逻辑
     */
    @Override
    public void show() {
        System.out.println(getName());
    }

}
