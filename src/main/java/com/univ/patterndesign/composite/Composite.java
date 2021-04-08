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

    // 这里需要有一个List<Component>
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

    @Override
    public void show() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).show();
        }
    }

}
