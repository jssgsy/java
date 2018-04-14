package com.univ.datastructure.list;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by Univ on 16/6/10.
 */
public class SingleListTest {


    @Test
    public void reverse() throws Exception {
        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        list.add(7);
        SingleList newList = list.reverse();
        assertArrayEquals(new int[]{7,6,23,35,3},list.toArray());//注意原链表并没有发生任何变化
        assertArrayEquals(new int[]{3,35,23,6,7},newList.toArray());
    }

    @Test
    public void reverse2() throws Exception {

        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        list.add(7);
        list.reverse2();
        assertArrayEquals(new int[]{3,35,23,6,7},list.toArray());//注意原链表本身反转了
    }

    @Test
    public void toArray() throws Exception {
        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        list.add(7);
        assertArrayEquals(new int[]{7,6,23,35,3},list.toArray());
    }

    @Test
    public void add() throws Exception {
        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        assertArrayEquals(new int[]{6,23,35,3},list.toArray());

    }

    @Test
    public void remove() throws Exception {
        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        list.remove(2);
        assertArrayEquals(new int[]{6,23,3},list.toArray());

    }

    @Test
    public void get() throws Exception {

        SingleList list = new SingleList();
        list.add(3);
        list.add(35);
        list.add(23);
        list.add(6);
        assertEquals(3,list.get(3));
        assertEquals(35,list.get(2));
        assertEquals(23,list.get(1));
        assertEquals(6,list.get(0));
    }

    @Test
    public void isEmpty() throws Exception {
        SingleList list = new SingleList();
        assertEquals(true,list.isEmpty());
        list.add(3);
        assertEquals(false,list.isEmpty());
    }
}