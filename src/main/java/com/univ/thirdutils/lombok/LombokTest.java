package com.univ.thirdutils.lombok;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/23 3:11 PM
 * @description
 */
public class LombokTest {
    
    @Test
    public void fn() {
        Student stu = new Student();
        stu.setAge(23);
        stu.setName("aaa");
        System.out.println(stu);
    }
}
