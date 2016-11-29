package com.univ.algorithom.algorithms4.dynamic_connectivity;

import java.util.Scanner;

/**
 * Univ
 * 16/11/29 10:05
 */
public class QuickUnionTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        QuickUnion quickUnion = new QuickUnion(n);
        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            if (quickUnion.connected(p,q)) {
                System.out.println(p + " 与 " + q + " 已经连接。");
                continue;
            }
            quickUnion.union(p,q);
        }
    }

}
