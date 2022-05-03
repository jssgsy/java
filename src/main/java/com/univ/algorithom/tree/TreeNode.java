package com.univ.algorithom.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 树节点的表现形式
 * @author univ
 * 2022/4/24 11:06 上午
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<Value extends Comparable<Value>> {
    Value value;
    TreeNode<Value> left;
    TreeNode<Value> right;

    /**
     * 打印出转换后的双向列表形式。<br>
     *
     * {@link BST2BiList#treeToBiList_v1(TreeNode, TreeNode)}专用
     */
    public void printBiList() {
        TreeNode<Value> tmp = this;
        while (tmp != null) {
            System.out.print("当前结果值为：" + tmp.value);

            if (tmp.left != null) {
                System.out.print("，前置节点值为：" + tmp.left.value);
            } else {
                System.out.print("，前置节点值为null");
            }

            if (tmp.right != null) {
                System.out.println("，后置节点值为：" + tmp.right.value);
            } else {
                System.out.println("，后置节点值为null");
            }
            tmp = tmp.right;
        }
    }
}
