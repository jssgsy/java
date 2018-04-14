package com.univ.algorithom.algorithms4.dynamic_connectivity;

/**
 * Univ
 * 16/11/28 18:35
 */

/**
 * 动态连接性问题
 *
 * 这里使用的方法是quick-find 方法
 *
 * 1. 这里假设输入元素的值均在【0,ids.length-1】中;
 *
 */
public class QuickFind {

    private int[] ids;//连通分量的标识
    private int count;//连通分量的个数

    //从控制台接收输入的个数
    public QuickFind(int n){
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        count = n;
    }

    public boolean connected(int p, int q){
        return group(p) == group(q);
    }

    /**
     * 返回元素 p 所在的分量
     * @param p
     * @return
     */
    private int group(int p) {
        return ids[p];
    }

    /**
     * 将 p 所在的组合并到q 所在的组
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pGroup = group(p);//必须先记录下来,因为group(p)时刻在发生变化。重点!
        int qGroup = group(q);
        if (pGroup != qGroup) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == pGroup){
                    ids[i] = qGroup;
                }
            }
            count--;
        }
    }
}
