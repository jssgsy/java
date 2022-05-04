package com.univ.algorithom.tree;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 《剑指offer》面试题39：二叉树的深度<br>
 * 输入一棵二叉树的根结节，求该树的深度。
 * 从根节点到叶子节点依次经过的节点形成树的一条路径，最长路径的长度为树的深度。
 *
 * 本题本身较简单，看点在其题的扩展{@link #isBalanceTree_v2(TreeNode, Map)}
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
        TreeNode<Integer> n8 = new TreeNode<Integer>(8, null, null);
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



    @Test
    public void testIsBalanceTree() {
        TreeNode<Integer> n1 = new TreeNode<Integer>(1, null, null);
        TreeNode<Integer> n2 = new TreeNode<Integer>(2, null, null);
        TreeNode<Integer> n3 = new TreeNode<Integer>(3, null, null);
        TreeNode<Integer> n4 = new TreeNode<Integer>(4, null, null);
        TreeNode<Integer> n5 = new TreeNode<Integer>(5, null, null);
        TreeNode<Integer> n6 = new TreeNode<Integer>(6, null, null);
        TreeNode<Integer> n7 = new TreeNode<Integer>(7, null, null);
        TreeNode<Integer> n8 = new TreeNode<Integer>(8, null, null);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n3.right = n6;
        // n7.right = n8;
        System.out.println(isBalanceTree(n1));
    }
    /**
     * 是否为一棵平衡树：即任意节点的左、右子树的深度差不超过1
     * 由{@link #depthOfTree(TreeNode)}可知，既然已经知道每个节点的深度了，那就比较简单了
     *
     * 缺点：
     *  会导致节点重复遍历。因为在求父节点深度的同时已经将所有子节点的深度都算出来，当求子节点深度时又重复了此过程。
     *  即越是在下方的节点，重复求的次数越多，更准确的说是：假如某节点有N个父节点，则此节点将重复计算N次。这也可由其打印的结果可知
     *
     * 改良版：{@link #testIsBalanceTree_v2()}
     * @param node
     * @return
     */
    public boolean isBalanceTree(TreeNode<Integer> node) {
        if (node == null) {
            return true;
        }
        int depthOfLeftTree = depthOfTree(node.left);
        int depthOfRightTree = depthOfTree(node.right);
        if (Math.abs(depthOfLeftTree - depthOfRightTree) <= 1) {
            return isBalanceTree(node.left) && isBalanceTree(node.right);
        } else {
            return false;
        }
    }

    @Test
    public void testIsBalanceTree_v2() {
        TreeNode<Integer> n1 = new TreeNode<Integer>(1, null, null);
        TreeNode<Integer> n2 = new TreeNode<Integer>(2, null, null);
        TreeNode<Integer> n3 = new TreeNode<Integer>(3, null, null);
        TreeNode<Integer> n4 = new TreeNode<Integer>(4, null, null);
        TreeNode<Integer> n5 = new TreeNode<Integer>(5, null, null);
        TreeNode<Integer> n6 = new TreeNode<Integer>(6, null, null);
        TreeNode<Integer> n7 = new TreeNode<Integer>(7, null, null);
        TreeNode<Integer> n8 = new TreeNode<Integer>(8, null, null);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n5.left = n7;
        n3.right = n6;
        // n7.right = n8;
        Map<TreeNode<Integer>, Integer> depthMap = new HashMap<>();
        System.out.println(isBalanceTree_v2(n1, depthMap));
    }

    /**
     * 是{@link #isBalanceTree(TreeNode)}的改良版。
     *
     * 只遍历一次完成要求。
     * 思路(借助了后序遍历的特性，精妙)：
     *  后序遍历(左、右、根)特性：也不能算是从下往上遍历，不过有这么个意思在，因为是先左后右再根。
     *  亦即：遍历到根时左、右子树已经遍历(处理)完了，类似于是从下往上遍历
     *
     * 因此想到，记录左、右子树的深度。即入参depthMap的含义。
     *
     * @param node
     * @param depthMap
     * @return
     */
    public boolean isBalanceTree_v2(TreeNode<Integer> node, Map<TreeNode<Integer>, Integer> depthMap) {
        if (node == null) {
            depthMap.put(node, 0);
            return true;
        }
        System.out.println("被遍历的节点为：" + node.value);
        if (isBalanceTree_v2(node.left, depthMap) && isBalanceTree_v2(node.right, depthMap)) {
            Integer depthOfLeftTree = depthMap.get(node.left);
            Integer depthOfRightTree = depthMap.get(node.right);
            System.out.println("节点【" + node.value + "】的左子树深度为：" + depthOfLeftTree);
            System.out.println("节点【" + node.value + "】的右子树深度为：" + depthOfRightTree);
            if (Math.abs(depthOfLeftTree - depthOfRightTree) <= 1) {
                depthMap.put(node, 1 + Math.max(depthOfLeftTree, depthOfRightTree));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
