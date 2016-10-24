package com.univ.datastructure.queue;

import org.junit.Test;

/**
 * Univ
 * 16/10/24 18:51
 */
public class CircleQueueTest {

    @Test
    public void test1(){
        CircleQueue cq = new CircleQueue();
        System.out.println(cq.size());//0
        System.out.println(cq.isEmpty());//true
        System.out.println(cq.isFull());//false

        for (int i = 1 ; !cq.isFull() ; i++){
            cq.offer(i);
        }
        System.out.println(cq);//[1,2,3,4,5,6,7,8,9]
        System.out.println(cq.size());//9

        cq.poll();
        cq.poll();
        cq.poll();
        cq.poll();
        System.out.println(cq.size());//5
        System.out.println(cq.isEmpty());//false
        System.out.println(cq.isFull());//false
        System.out.println(cq);//[5,6,7,8,9]

        cq.offer(7);
        cq.offer(8);
        cq.offer(11);
        cq.offer(12);
        System.out.println(cq.size());//9
        System.out.println(cq);//[5,6,7,8,9,7,8,11,12]

        System.out.println(cq.isEmpty());//false
        System.out.println(cq.isFull());//true
    }

}
