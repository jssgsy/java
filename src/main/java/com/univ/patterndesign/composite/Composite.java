package com.univ.patterndesign.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Univ
 * 16/9/30 17:01
 */

/**
 * 非叶子节点
 */
public class Composite extends Component {

    // 这里需要有一个List<Component>，是一个子结点的容器；
    private List<Component> list = new ArrayList<>();

    public Composite(){}

    public Composite(String name){
        super(name);
    }

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    /**
     * 重点：非叶子结点(Composite)是框架的概念，其不承接具体的业务逻辑
     * 因此非叶子结点调用一般只是委托给所有子结点(遍历即可)的相应方法
     */
    @Override
    public void show() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).show();
        }
    }

}
