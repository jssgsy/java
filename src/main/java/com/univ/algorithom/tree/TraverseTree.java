package com.univ.algorithom.tree;

import java.util.Stack;

/**
 * 树遍历，非递归形式<br>
 * 核心：<br>
 * 1. 引入一个栈；
 *
 * @author univ
 * 2022/4/24 11:05 上午
 */
public class TraverseTree<Value extends Comparable<Value>> {

    /**
     * 先序：根、左、右
     * @param treeNode
     */
    void preOrder(TreeNode<Value> treeNode) {
        TreeNode<Value> cur = treeNode;
        /**
         * 核心：引入一个栈，用来保存遍历过程中遇到的节点
         */
        Stack<TreeNode<Value>> stack = new Stack<>();
        // 两个条件组合一起是整理后的，cur为null，表明此时到达末尾了，需要从栈中取出元素(即回溯到父节点)
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                System.out.println(cur.value);
                stack.push(cur);
                cur = cur.left; // 处理左节点
            } else {
                cur = stack.pop();
                cur = cur.right; // 处理右节点
            }
        }
    }

    /**
     * 中序：左、根、右
     * @param treeNode
     */
    void inOrder(TreeNode<Value> treeNode) {
        TreeNode<Value> cur = treeNode;
        Stack<TreeNode<Value>> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left; // 处理左节点
            } else {
                cur = stack.pop();
                System.out.println(cur.value);  // 打印当前节点
                cur = cur.right;    // 处理右节点
            }
        }
    }

    /**
     * 后序：左、右、根
     * @param treeNode
     */
    void postOrder(TreeNode<Value> treeNode) {
        TreeNode<Value> cur = treeNode;
        Stack<TreeNode<Value>> stack = new Stack<>();
        // 智慧之光：不需要每个节点加一个是否被访问，因为本质是，在判断某个节点是否要真实出栈时是要其右节点是否被访问过，
        TreeNode<Value> lastPrinted = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                /**
                 * 暂时不出栈，因为访问之前需要先访问右节点
                 */
                cur = stack.peek();
                if (cur.right == null || cur.right == lastPrinted) {
                    // 此时要出栈了
                    cur = stack.pop();
                    System.out.println(cur.value);
                    // 哪个节点被打印了，就赋值lastPrinted为哪个节点，也是唯一处需要赋值的
                    lastPrinted = cur;
                    cur = null; // 让下一步继续出栈
                } else {
                    cur = cur.right;
                }
            }
        }
    }


}
