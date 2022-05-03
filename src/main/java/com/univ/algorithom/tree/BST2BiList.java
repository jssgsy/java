package com.univ.algorithom.tree;

import org.junit.Test;

/**
 * 二叉搜索树按照顺序转成双向链表<br>
 * 《剑指offer》面试题27：二叉搜索树与双向链表
 *
 * 不允许新建节点，即需要通过变更树节点的链接使树原地转变成一个双向链表
 *
 * @author univ
 * 2022/5/3 10:28 上午
 */
public class BST2BiList {

    @Test
    public void testTreeToBiList() {
        TreeNode<Integer> node0 = new TreeNode<>(20, null, null);
        TreeNode<Integer> node1 = new TreeNode<>(10, null, null);
        TreeNode<Integer> node2 = new TreeNode<>(6, null, null);
        TreeNode<Integer> node3 = new TreeNode<>(14, null, null);
        TreeNode<Integer> node4 = new TreeNode<>(4, null, null);
        TreeNode<Integer> node5 = new TreeNode<>(8, null, null);
        TreeNode<Integer> node6 = new TreeNode<>(12, null, null);
        TreeNode<Integer> node7 = new TreeNode<>(16, null, null);
        node0.left = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        TreeNode<Integer> node = treeToBiList_v1(node0, null);
        node.printBiList();
    }

    /**
     * 以node为根节点的二叉搜索树转成双向链表后的头节点；<br>
     * 还有很多待优化点，特别是每次都额外调用了{@link BSTUtil#maxNodeOfTree(TreeNode)}与{@link BSTUtil#minNodeOfTree(TreeNode)} (TreeNode)}方法
     *
     * 思路：
     * 1. 一看就是递归的应用，且为中序遍历；
     *  补充：中序遍历的特点就是将二叉搜索树按顺序打印出；
     * 2. 大原则：处理当前节点时，
     *  2.1 处理好左子树后，返回左子树中的最大节点maxNode(最右子节点)；
     *  2.2 处理好右子树后，返回右子树中的最小节点minNode(最左子节点)；
     *  2.3
     *  暂存正在处理节点(树)的最大和最小节点，因为要给上层节点用；
     *
     * 重点：边界情况较多，很难一次性写对。
     *
     * @param node 当前处理的树节点
     * @param parent 当前处理的树节点的父节点
     * @return
     */
    TreeNode<Integer> treeToBiList_v1(TreeNode<Integer> node, TreeNode<Integer> parent) {
        if (null == node && null != parent) {   // 当前正处理null节点
            return parent;
        }
        if (null == node) { // 输入列表是空列表
            return null;
        }
        if (!NormalTreeUtil.isLeaf(node)) {    // 非叶子节点，即要么有左子节点、要么有右子节点、要么左右子节点都有
            TreeNode<Integer> n1 = treeToBiList_v1(node.left, node);
            TreeNode<Integer> n2 = treeToBiList_v1(node.right, node);
            TreeNode<Integer> minNode = BSTUtil.minNodeOfTree(node.left);// 不管三七二十一暴力求出最小节点，有可能为空
            TreeNode<Integer> maxNode = BSTUtil.maxNodeOfTree(node.right);// 不管三七二十一暴力求出最小节点，有可能为空
            if (n1 != node) {   // 说明当前节点有左子节点
                n1.right = node;
                node.left = n1;  // 将当前节点的左节点置为左子树中的最大节点
            }
            if (n2 != node) {      // 说明当前节点有左子节点
                n2.left = node;
                node.right = n2;    // 将当前节点的左节点置为右子树中的最小节点
            }

            if (null == parent) { // 容易漏。说明处理到根节点了, 此时所有节点均已转换
                return minNode == null ? node : minNode;    // 为空则表示没有左子节点，那最小节点就是当前节点
            } else {
                // maxNode为空，表明当前节点没有右子节点，则当前节点就是最大节点；
                // minNode为空，表明当前节点没有左子节点，则当前节点就是最小节点；
                return node == parent.left ? (maxNode == null ? node : maxNode) : (minNode == null ? node : minNode);
            }
        } else {
            return node;
        }
    }

}
