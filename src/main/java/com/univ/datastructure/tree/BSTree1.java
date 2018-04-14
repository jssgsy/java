package com.univ.datastructure.tree;

/**
 * Univ
 * 16/11/18 18:59
 */

/**
 * BSTree.java的升级版
 * 1.BSTNode没有parent指针;
 * 2.BSTNode带key与value
 */
public class BSTree1 {

    private BSTNode root;

    /**
     * @return 此棵二叉搜索树的节点个数。
     *
     * 其实不定义此方法也可以在代码中很简单的求出树的节点个数,
     * 但这样定义有一个好处,比如在求某棵树的子树的节点个数时不用再去判断其子树是否为null。
     */
    public int size(){
        return size(root);
    }

    private int size(BSTNode root) {
        if (root == null) {
            return 0;
        } else {
            return root.N;
        }
    }

    /**
     * 获取指定key对应的value
     *
     * @param key
     * @return 二叉搜索树的查找既可以使用递归也可以使用while循环(如BSTree.java中的contains方法),
     * 考虑到树的结构本身是递归的性质,因此使用递归来处理可能更好一些。
     * 重要的是,使用递归处理时,应该在get(key)方法内部调用get(node,key),即体现出是在某颗树中查找
     */
    public int get(int key) {
        return get(root, key);
    }

    /**
     * 在node为根节点的二叉搜索树中找出key对应的value
     *
     * @param node
     * @param key
     * @return 如果key不存在, 则返回-1.(这需要在后期修改成泛型,此时可以返回null,这也是包装类型的一个优点:可以为null值)
     */
    private int get(BSTNode node, int key) {
        if (node == null) {//空树
            return -1;
        }
        if (key < node.key) {
            return get(node.left, key);
        } else if (key > node.key) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * 在二叉搜索树中插入键值对key-value,如果key已经存在,则用value替换旧值
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        root = put(root, key, value);
    }

    /**
     * 在node为根节点的二叉搜索树中插入键值对,如果key已经存在,则用value替换旧值
     *
     * @param node
     * @param key
     * @param value
     * @return 返回此棵树(即此棵树的根节点)
     *
     * 这里的返回值类型设置成BSTNode而不是void是一个大大的技巧。值得细细揣摩。
     */
    private BSTNode put(BSTNode node, int key, int value) {
        if (node == null) {//如果是空树,则直接新建一个节点
            //root = new BSTNode(key, value, 1);//这是错误的,root是整棵树的根节点,不能在操作子树时改变
            return new BSTNode(key, value, 1);
        }
        if (key < node.key) {
            node.left = put(node.left, key, value);
        } else if (key > node.key) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;//更新此节点为根节点的二叉搜索树的节点个数
        return node;
    }

    /**
     * 二叉搜索树的最小key
     * @return
     */
    public int min(){
        return min(root);
    }

    private int min(BSTNode node) {
        if (node == null) {
            return -1;
        }
        if (node.left != null) {
            return min(node.left);
        } else {
            return node.key;
        }
    }

    /**
     * 二叉搜索树的最大key值
     * @return
     */
    public int max(){
        return max(root);
    }

    private int max(BSTNode node) {
        if (node == null) {
            return -1;
        }
        if (node.right != null) {
            return max(node.right);
        } else {
            return node.key;
        }
    }


    private static class BSTNode {
        private BSTNode left;
        private BSTNode right;

        private int key;//节点的key
        private int value;//节点的value

        private int N;//以此节点为根节点的二叉搜索树的节点个数

        public BSTNode(int key, int value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
}
