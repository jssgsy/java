package com.univ.algorithom.tree;

/**
 * 普通二叉树的工具类。
 * 即不要求是二叉搜索树，更不要求是其它树。
 *
 * @author univ
 * 2022/5/3 10:35 下午
 */
public class NormalTreeUtil {

    /**
     * 判断某个节点是否为叶子节点
     * @param treeNode
     * @return
     */
    public static boolean isLeaf(TreeNode<Integer> treeNode) {
        return treeNode.left == null && treeNode.right == null;
    }
}
