package com.univ.algorithom.algorithms4.dynamic_connectivity;

import java.util.Scanner;

/**
 * Univ
 * 16/11/28 18:50
 */
public class QuickFindTest {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QuickFind quickFind = new QuickFind(n);
        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (quickFind.connected(p,q)) {
                System.out.println(p + " 与 " + q + "已经连通");
                continue;
            }
            quickFind.union(p,q);
        }
    }

}
