package com.univ.algorithom.tree;

/**
 * 二叉搜索树的工具类
 * 二叉搜索树：左 < 父 < 根
 * @author univ
 * 2022/5/3 10:33 下午
 */
public class BSTUtil {

    /**
     * 求出二叉搜索树的最大节点(最右边节点)
     * @param node
     * @return
     */
    public static TreeNode<Integer> maxNodeOfTree(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        } else {
            return maxNodeOfTree(node.right);
        }
    }

    /**
     * 求出二叉搜索树的最小节点(最左边节点)
     * @param node
     * @return
     */
    public static  TreeNode<Integer> minNodeOfTree(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        } else {
            return minNodeOfTree(node.left);
        }
    }
}
