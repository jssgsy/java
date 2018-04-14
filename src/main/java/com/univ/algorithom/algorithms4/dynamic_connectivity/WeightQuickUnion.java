package com.univ.algorithom.algorithms4.dynamic_connectivity;

/**
 * Univ
 * 16/11/29 10:31
 */

/**
 * 动态连接性问题
 *
 * 这里使用加权 quick-union 方法
 *
 *
 * 1. 和 QuickQunion 的区别在于,这里需要记录每个连通分量中结点的个数。
 *  而使用数组表示的树不是很好统计每个连通分量的结点过数(需要找到根结点,然后遍历整个数组),
 *  因此这里额外开辟一个数组用来存放每个连通分量的个数
 */
public class WeightQuickUnion {

    private int[] ids;//树形表示的连通分量的标识
    private int[] sz;//每个连通分量中结点的size
    private int count;//连通分量的标识

    /**
     * 这里设定根结点的父结点的值为其自己
     * @param n
     */
    public WeightQuickUnion(int n){
        count = n;
        ids = new int[n];
        sz = new int[n];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
            sz[i] = 1;//初始时,每个连通分量的结点过数均为1
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

        //将小树连接到大树上
        if (sz[pGroup] <= sz[qGroup]) {
            ids[pGroup] = qGroup;
            sz[qGroup] += sz[pGroup];
        } else {
            ids[qGroup] = pGroup;
            sz[pGroup] += sz[qGroup];
        }

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
