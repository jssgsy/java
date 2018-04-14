package com.univ.ognl;

import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * created by Univ
 * 16/5/16 21:49
 */

/**
 * 1. 获取值时(不能从root对象还是context中),表达式都需要通过Ognl.parseExpression("...")获得;
 */
public class OgnlTest {

    @Test
    public void testGetValueInRoot() throws OgnlException {
        User user = new User();
        user.setName("univ name");
        String name = (String) Ognl.getValue(Ognl.parseExpression("name"), user);
        System.out.println("[univ]:------ " + name);
    }

    @Test
    public void testSetValueInRoot() throws OgnlException {
        User user = new User();
        Ognl.setValue("name",user,"aaaa");
        System.out.println("[univ]:------ " + user.getName());
    }

    /**
     * 注意,从context中获取值时,必须有root对象,哪怕如下面一样仅仅只是个空root对象;
     * 不能给context赋值;
     */
    @Test
    public void testGetValueInContext() throws OgnlException {
        User user = new User();
        Map<String,String> map = new HashMap<String,String>();
        map.put("aaa", "bbb");

        //找不到值则返回null,并不是抛出异常
        String name = (String) Ognl.getValue(Ognl.parseExpression("#xxx"), map,user);
        System.out.println("[univ]:------ " + name);

        String aaa = (String) Ognl.getValue(Ognl.parseExpression("#aaa"), map,user);
        System.out.println("[univ]:------ " + aaa);

    }




}

class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}