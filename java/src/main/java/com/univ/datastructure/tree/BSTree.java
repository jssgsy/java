package com.univ.datastructure.tree;

/**
 * Univ
 * 16/11/17 18:08
 */

/**
 * 二叉搜索树
 *
 * 1. contains方法与binarySearch方法需要统一。
 */
public class BSTree {

    private BSTNode root;//二叉搜索树的根节点

    public BSTree() {
    }

    /**
     * 是否包含某个元素
     * @param target
     * @return
     */
    public boolean contains(int target){
        BSTNode temp = root;
        while (temp != null) {
            if (temp.val < target) {
                temp = temp.right;
            } else if (temp.val > target) {
                temp = temp.left;
            }else {
                return true;
            }
        }
        return false;
    }
    /**
     * 二叉搜索
     * @param target
     * @return  比较的最后一个元素(不论是否找到)
     *
     * 这里的重点在于没有找到target时的返回值,返回的是最后一个比较的元素。即搜索停止的地方。
     * 变量parent的唯一目的就是记录当搜索不到时最后一个比较的元素.
     * 注意这里用private修饰,不用暴露给外界。
     */
    private BSTNode binarySearch(int target){
        BSTNode temp = root;
        BSTNode parent = null;
        while (temp != null) {
            if (temp.val < target) {
                parent = temp;
                temp = temp.right;
            } else if (temp.val > target) {
                parent = temp;
                temp = temp.left;
            }else {
                return temp;
            }
        }
        return parent;
    }

    /**
     * 插入指定元素
     * @param val
     * @return
     *
     * 1. 如何确保插入的元素没有存在?
     *  position.val == val说明存在;
     *  position.val != val说明不存在,
     *      如果position.val < val说明欲插入的位置在position的右节点处;
     *      如果position.val > val说明欲插入的位置在position的左节点处;
     */
    public boolean insert(int val){
        if (root == null) {
            root = new BSTNode(val);
        }else {
            BSTNode position = binarySearch(val);
            if (position.val < val) {//说明欲插入的位置在position的右节点处
                BSTNode node = new BSTNode(val);
                position.right = node;
                node.parent = position;
            }else if(position.val > val){//说明欲插入的位置在position的左节点处
                BSTNode node = new BSTNode(val);
                position.left = node;
                node.parent = position;
            }else {//说明欲插入的元素在树中已经存在,不允许插入
                throw new IllegalArgumentException("参数val: " + val + " 已存在,不能插入。");
            }
        }
        return true;
    }

    /**
     * 删除指定元素
     * @param val
     * @return
     */
    public boolean remove(int val){
        if (!contains(val)) {
            throw new IllegalArgumentException("参数val: " + val + " 不存在,无法删除。");
        }
        BSTNode node = binarySearch(val);
        if (node.isLeaf()) {//删除的是叶子节点
            if (node == root) {//删除的是根叶子节点
                root = null;
            }else{
                if (node.val < node.parent.val) {//删除的是左叶子节点
                    node.parent.left = null;
                } else {//说明删除的是右叶子节点
                    node.parent.right = null;
                }
                node.parent = null;//let to gc
            }
        } else if (node.onlyHasLeftChild()) {//删除的节点只有左节点(右节点为空)
            if (node == root) {//删除的是只有左节点的根节点
                root = node.left;
                root.parent = node.left = null;//let to gc
            }else{
                if (node.val < node.parent.val) {//删除的是左叶子节点
                    node.parent.left = node.left;
                } else {//删除的是右叶子节点
                    node.parent.right = node.left;
                }
                node.left.parent = node.parent;
                node.left = node.parent = null;//let to gc
            }
        } else if (node.onlyHasRightChild()) {//删除的节点只有右节点(左节点为空)
            if (node == null) {//删除的是只有右节点的根节点
                root = node.right;
                root.parent = node.right = null;//let to gc
            } else {
                if (node.val < node.parent.val) {//删除的是左叶子节点
                    node.parent.left = node.right;
                } else {//删除的是右叶子节点
                    node.parent.right = node.right;
                }
                node.right.parent = node.parent;
                node.right = node.parent = null;//let to gc
            }
        } else {//说明删除的节点既有左节点又有右节点
            BSTNode minNode = getMin(node.right);//从右子树中获取最小值的节点
            int tempVal = minNode.val;//记录待删除节点的值
            remove(minNode.val);
            node.val = tempVal;//和上一句的顺序很重要
        }
        return true;
    }

    /**
     * 获取以node为根节点的二叉搜索树中的最小节点的值
     * @param node
     * @return
     *
     * 这里采取的是类似中序遍历的方法,不停往左子树走(中序遍历下,最左边的节点就是值最小的节点)。
     * 这里可以考虑直接返回节点值的类型(这里是int)
     */
    private BSTNode getMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * 中序打印整个二叉搜索树
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 中序遍历根节点为node的二叉搜索树
     * @param node
     */
    private void inOrder(BSTNode node) {
        if (node == null) {
            return ;
        }
        inOrder(node.left);
        System.out.print(node.val + "  ");
        inOrder(node.right);
    }

    /**
     * 前序打印整个二叉搜索树
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 前序遍历根节点为node的二叉搜索树
     * @param node
     */
    private void preOrder(BSTNode node) {
        if (node == null) {
            return ;
        }
        System.out.print(node.val + "  ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 后序打印整个二叉搜索树
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 后序遍历根节点为node的二叉搜索树
     * @param node
     */
    private void postOrder(BSTNode node) {
        if (node == null) {
            return ;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + "  ");
    }

    /**
     * 判断二叉搜索树是否为空
     * @return
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * 二叉搜索树的节点
     */
    private static class BSTNode{
        private BSTNode parent;
        private BSTNode left;
        private BSTNode right;

        private int val;

        public BSTNode() {
        }

        public BSTNode(int val) {
            this.val = val;
        }

        /**
         * 是否为叶子节点
         * @return
         */
        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean onlyHasLeftChild() {
            return left != null && right == null;
        }

        public boolean onlyHasRightChild() {
            return left == null && right != null;
        }



    }
}


