package com.univ.algorithom.algorithms4.dynamic_connectivity;

/**
 * Univ
 * 16/11/29 09:45
 */

/**
 * 动态连通性问题
 *
 * 这里使用不带权重的quick-union 方法
 *
 * 1. 根结点:人为设置为p = ids[p],即根结点的值就是其索引值;
 * 2. 根结点就是连通分量的标识;
 *
 */
public class QuickUnion {

    private int[] ids;//树形表示的连通分量的标识
    private int count;//连通分量的标识

    /**
     * 这里设定根结点的父结点的值为其自己
     * @param n
     */
    public QuickUnion(int n){
        count = n;
        ids = new int[n];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    /**
     * 返回元素 p 所在的组(树),即其根结点
     * @param p
     * @return
     */
    private int group(int p){
        while(p != ids[p]) {//不是根结点
            p = ids[p];
        }
        return p;
    }

    /**
     * 两个分量是否连通
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        return group(p) == group(q);
    }

    /**
     * 连接两个分量,将 p 所在的组合并到 q 所在的组上
     * @param p
     * @param q
     */
    public void union(int p, int q){
        if (connected(p, q)) {
            return ;
        }
        int pGroup = group(p);
        int qGroup = group(q);
        ids[pGroup] = qGroup;
        count--;
    }

    /**
     * 连通分量的个数
     * @return
     */
    public int size(){
         return count;
    }

}
