package com.univ.algorithom.tree;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 《剑指offer》面试题25<br>
 * 打印出从根节点出发到叶子节点上所有节点和为某个值的所有路径。<br>
 *
 * 大思路：在先序遍历中做一些操作。<br>
 *
 * @author univ
 * 2022/5/2 4:32 下午
 */
public class PathONumInTree {

    @Test
    public void test() {
        TreeNode<Integer> head = new TreeNode<>(10, null, null);
        TreeNode<Integer> node1 = new TreeNode<>(5, null, null);
        TreeNode<Integer> node2 = new TreeNode<>(12, null, null);
        TreeNode<Integer> node3 = new TreeNode<>(4, null, null);
        TreeNode<Integer> node4 = new TreeNode<>(7, null, null);
        TreeNode<Integer> node5 = new TreeNode<>(1, null, null);
        head.left = node1;
        head.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node5;
        for (Integer num : Arrays.asList(20, 22, 18)) {
            sum = 0;
            stack.clear();
            // preOrder_v1(head, num);
            // preOrder_v2(head, num);
            preOrder_v3(head, num);
            System.out.println("======");
        }
    }

    // 全局变量用来缓存当前路径上的节点值之和
    int sum = 0;

    /**
     * 首先来个简单的：找到一次就打印一次
     * 之后来收集路径
     *
     * @param treeNode
     * @param num
     */
    void preOrder_v1(TreeNode<Integer> treeNode, int num) {
        if (treeNode == null) { // 说明到达叶子节点
            if (sum == num) {   // 注：会打印两次：左、右null节点各打一次
                System.out.println("到达一次，但不知道路径");
            }
            return;
        }
        sum += treeNode.value;
        preOrder_v1(treeNode.left, num);
        if (null != treeNode.left) {
            sum -= treeNode.left.value;  // 重点：回溯时需要减去底层的值
        }
        preOrder_v1(treeNode.right, num);
        if (null != treeNode.right) {
            sum -= treeNode.right.value;    // 重点：回溯时需要减去底层的值
        }
    }

    /**
     * 借助一个栈来保存满足要求的从根节点到叶子结点的路径
     */
    private Stack<Integer> stack = new Stack<>();

    /**
     * 由{@link #preOrder_v1(TreeNode, int)}可知已经能知道是否有路径之和为指定的num了。现在要做的就是将此路径收集起来
     *
     * 思路：涉及到回溯，因此需要借助一个栈来保存路径
     *
     * @param treeNode
     * @param num
     */
    void preOrder_v2(TreeNode<Integer> treeNode, int num) {
        if (treeNode == null) { // 说明到达叶子节点
            if (sum == num) {   // 说明此路径满足要求了
                System.out.println(JSONObject.toJSONString(stack));
            }
            return;
        }
        // 碰到一个节点，此节点就入栈
        stack.push(treeNode.value);
        sum += treeNode.value;
        preOrder_v2(treeNode.left, num);
        if (null != treeNode.left) {
            sum -= treeNode.left.value;
            stack.pop();
        }
        preOrder_v2(treeNode.right, num);
        if (null != treeNode.right) {
            sum -= treeNode.right.value;    // 回溯时需要减去底层的值
            stack.pop();
        }
    }

    /**
     * 最终版<br>
     * 由{@link #preOrder_v2(TreeNode, int)}可知，满足条件的每条路径都是打印两次。优化<br>
     *
     * 打印两次的原因是：叶子节点的左、右子树均为空，会命中两次。因此优化方向为直接判定是否为叶子节点，而不是当为叶子节点时还继续向下递归<br>
     *
     * 注：此时逻辑稍有调整：先入栈，然后判断是否为叶子节点
     * @param treeNode
     * @param num
     */
    void preOrder_v3(TreeNode<Integer> treeNode, int num) {
        if (null == treeNode) {
            return;
        }
        // 碰到一个节点，此节点就入栈
        stack.push(treeNode.value);
        sum += treeNode.value;

        if (treeNode.left == null && treeNode.right == null) { // 说明到达叶子节点
            if (sum == num) {   // 说明此路径满足要求了
                System.out.println(JSONObject.toJSONString(stack));
            }
            return;
        }

        preOrder_v3(treeNode.left, num);
        if (null != treeNode.left) {
            sum -= treeNode.left.value;
            stack.pop();
        }
        preOrder_v3(treeNode.right, num);
        if (null != treeNode.right) {
            sum -= treeNode.right.value;    // 回溯时需要减去底层的值
            stack.pop();
        }
    }

}
