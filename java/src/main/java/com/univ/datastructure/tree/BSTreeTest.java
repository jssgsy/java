package com.univ.datastructure.tree;

import org.junit.Test;

/**
 * Univ
 * 16/11/17 18:45
 */
public class BSTreeTest {

    @Test
    public void testInsert(){
        BSTree bsTree = new BSTree();
        bsTree.insert(8);
        bsTree.insert(12);
        bsTree.insert(4);
        bsTree.insert(316);
        bsTree.insert(3);
        bsTree.insert(6);

        bsTree.preOrder();
        System.out.println();

        bsTree.inOrder();
        System.out.println();

        bsTree.postOrder();
        System.out.println();
    }


    @Test
    public void testContains(){
        BSTree bsTree = new BSTree();
        bsTree.insert(8);
        bsTree.insert(12);
        bsTree.insert(4);
        bsTree.insert(316);
        bsTree.insert(3);
        bsTree.insert(6);

        System.out.println(bsTree.contains(12));
        System.out.println(bsTree.contains(120));
    }

    @Test
    public void testRemove(){
        BSTree bsTree = new BSTree();
        bsTree.insert(8);
        bsTree.insert(12);
        bsTree.insert(4);
        bsTree.insert(316);
        bsTree.insert(3);
        bsTree.insert(6);

        System.out.println("初始二叉搜索树为: ");
        bsTree.inOrder();
        System.out.println();

        System.out.println("删除316后的二叉搜索树为: ");
        bsTree.remove(316);
        bsTree.inOrder();
        System.out.println();

        System.out.println("删除12后的二叉搜索树为: ");
        bsTree.insert(316);
        bsTree.remove(12);
        bsTree.inOrder();
        System.out.println();

        System.out.println("删除4后的二叉搜索树为: ");
        bsTree.insert(12);
        bsTree.remove(4);
        bsTree.inOrder();
        System.out.println();
    }

    @Test
    public void testRemove2(){
        BSTree bsTree = new BSTree();
        bsTree.insert(8);
        bsTree.insert(4);
        System.out.println(bsTree.isEmpty());
        bsTree.inOrder();

        bsTree.remove(8);
        System.out.println(bsTree.isEmpty());
        bsTree.inOrder();

    }

}
