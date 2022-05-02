package com.univ.algorithom.tree;

import org.junit.Test;

/**
 * 一棵树是否有指定的子树<br>
 *
 * 递归的应用
 * @author univ
 * 2022/4/30 4:24 下午
 */
public class HasSubTree {

    @Test
    public void test() {
        TreeNode<Integer> treeHead1 = new TreeNode<>(9, null, null);
        TreeNode<Integer> node2 = new TreeNode<>(8, null, null);
        TreeNode<Integer> node3 = new TreeNode<>(7, null, null);
        TreeNode<Integer> node4 = new TreeNode<>(4, null, null);
        TreeNode<Integer> node5 = new TreeNode<>(3, null, null);
        TreeNode<Integer> node6 = new TreeNode<>(2, null, null);
        TreeNode<Integer> node7 = new TreeNode<>(1, null, null);
        node5.right = node7;
        node4.left= node6;
        node3.left = node4;
        node3.right = node5;
        treeHead1.left = node2;
        treeHead1.right = node3;

        System.out.println(hasSubTree(treeHead1, node4));
        System.out.println(hasSubTree(treeHead1, new TreeNode<>(9, null, null)));
    }

    /**
     * 树A是否包含树B(只要求B是A的一部分)
     * @param treeA
     * @param treeB
     * @param <Value>
     * @return
     */
    public <Value extends Comparable<Value>> boolean hasSubTree(TreeNode<Value> treeA, TreeNode<Value> treeB) {
        if (treeB == null) {
            return true;
        }
        TreeNode<Value> sameNode = find(treeA, treeB.value);
        if (null == sameNode) {
            return false;
        }
        // 这里很重要：将树A是否包含树B转化成树B是否是树A的一部分，这样可以通过遍历树B的每个元素看是否都在树A中的对应位置
        if (isSubTreeWithSameRoot(treeB, sameNode)) {
            return true;
        }
        if (hasSubTree(treeA.left, treeB)) {
            return true;
        }
        return hasSubTree(treeA.right, treeB);
    }

    /**
     * 在树中找到某个元素，并返回命中的节点，未命中返回null
     * @param tree
     * @param obj
     * @param <Value>
     * @return
     */
    private <Value extends Comparable<Value>> TreeNode<Value> find(TreeNode<Value> tree, Value obj) {
        if (tree == null) {
            return null;
        }
        if (tree.value.compareTo(obj) == 0) {
            return tree;
        }
        TreeNode<Value> resultNode = find(tree.left, obj);
        if (resultNode != null) {
            return resultNode;
        }
        return find(tree.right, obj);
    }

    /**
     * 判定树A是否是树B的子树
     * @param treeA
     * @param treeB
     * @param <Value>
     * @return
     */
    private <Value extends Comparable<Value>> boolean isSubTreeWithSameRoot(TreeNode<Value> treeA, TreeNode<Value> treeB) {
        if (treeA == null && treeB == null) {
            return true;
        }
        if (treeA == null) {
            return true;
        }
        if (treeB == null) {
            return false;
        }
        if (treeA.value.compareTo(treeB.value) != 0) {
            return false;
        }
        return isSubTreeWithSameRoot(treeA.left, treeB.left) && isSubTreeWithSameRoot(treeA.right, treeB.right);
    }
}
