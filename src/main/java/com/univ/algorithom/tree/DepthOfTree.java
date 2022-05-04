package com.univ.algorithom.tree;

import org.junit.Test;

/**
 * 《剑指offer》面试题39：二叉树的深度<br>
 * 输入一棵二叉树的根结节，求该树的深度。
 * 从根节点到叶子节点依次经过的节点形成树的一条路径，最长路径的长度为树的深度。
 *
 * @author univ
 * 2022/5/4 9:10 下午
 */
public class DepthOfTree {

    @Test
    public void testDepthOfTree() {
        TreeNode<Integer> n1 = new TreeNode<Integer>(1, null, null);
        TreeNode<Integer> n2 = new TreeNode<Integer>(2, null, null);
        TreeNode<Integer> n3 = new TreeNode<Integer>(3, null, null);
        TreeNode<Integer> n4 = new TreeNode<Integer>(4, null, null);
        TreeNode<Integer> n5 = new TreeNode<Integer>(5, null, null);
        TreeNode<Integer> n6 = new TreeNode<Integer>(6, null, null);
        TreeNode<Integer> n7 = new TreeNode<Integer>(7, null, null);
        TreeNode<Integer> n8 = new TreeNode<Integer>(7, null, null);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n3.right = n6;
        n7.right = n8;
        System.out.println(depthOfTree(n1));

    }

    /**
     * 求树的深度。
     * 思路：比较简单，一看就是递归的应用，只要明白：depth(node) = 1 + max(depth(node.left), depth(node.right))即可。
     *
     * @param node
     * @return
     */
    public int depthOfTree(TreeNode<Integer> node) {
        int depth = 0;
        if (node == null) {
            return depth;
        }
        System.out.println("被遍历的节点为：" + node.value);
        depth++;
        return depth + Math.max(depthOfTree(node.left), depthOfTree(node.right));
    }

}
