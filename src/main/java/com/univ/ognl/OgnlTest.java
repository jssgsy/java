package com.univ.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by Univ
 * 16/5/16 21:49
 */

/**
 * 表达式有两种形式：
 *  1. 直接字符串形式；
 *  2. 通过Ognl.parseExpression("...")包裹;
 */
public class OgnlTest {

    @Test
    public void testGetValueInRoot() throws OgnlException {
        User user = new User();
        user.setName("univ name");
        String name = (String) Ognl.getValue(Ognl.parseExpression("name1"), user);
        System.out.println("表达式使用Ognl.parseExpression: " + name);
        System.out.println("表达式直接使用字符串: " + Ognl.getValue("name", user));
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
        user.setName("zhangsan");

        OgnlContext ognlContext = Ognl.createDefaultContext(user);
        ognlContext.put("name", "这里上下文环境中的属性值");

        String name = (String) Ognl.getValue("name", ognlContext, user);
        System.out.println("取name属性的值： " + name);

        String name2 = (String) Ognl.getValue("#name", ognlContext, user);
        System.out.println("取#name属性的值： " + name2);

        // 从根中取数据时，如果属性不存在，则直接抛异常NoSuchPropertyException
        /*String address = (String) Ognl.getValue("address", ognlContext, user);
        System.out.println("取address属性的值： " + address);*/

        // 从上下文取数据时，如果属性不存在不会抛异常而是直接返回null
        String address2 = (String) Ognl.getValue("#address", ognlContext, user);
        System.out.println("取#address2属性的值： " + address2);
    }

    @Test
    public void testCollection() throws OgnlException {
        // 重点：Root一定不要为null
        OgnlContext ognlContext = Ognl.createDefaultContext(new Object());

        // 数组
        int[] arr = {1, 3, 5, 7, 10};
        ognlContext.put("arr", arr);
        // list
        ognlContext.put("list", Arrays.asList("aaa", "bbb", "ccc", "ddd"));

        // map
        Map<String, String> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", "30");
        map.put("address", "where");
        ognlContext.put("map", map);

        // 数组访问
        int[] arrResult = (int[]) Ognl.getValue("#arr", ognlContext, ognlContext.getRoot());
        System.out.println("#arr: " +  Arrays.toString(arrResult));

        int i = (int) Ognl.getValue("#arr[0]", ognlContext, ognlContext.getRoot());
        System.out.println("#arr[0]: " + i);

        int j = (int) Ognl.getValue("#arr[0] + #arr[2]", ognlContext, ognlContext.getRoot());
        System.out.println("#arr[0] + #arr[2]: " + j);

        // 集合访问
        @SuppressWarnings("unchecked")
        List<String> listResult = (List<String>) Ognl.getValue("#list", ognlContext, ognlContext.getRoot());
        System.out.println("#list: " + listResult);
        System.out.println("#list[1]: " + Ognl.getValue("#list[1]", ognlContext, ognlContext.getRoot()));

        // map访问
        @SuppressWarnings("unchecked")
        Map<String, String> mapResult = (Map<String, String>) Ognl.getValue("#map", ognlContext, ognlContext.getRoot());
        System.out.println("#map: " + mapResult);
        System.out.println("#map.name: " + Ognl.getValue("#map.name", ognlContext, ognlContext.getRoot()));
        // 不要写成#map[address]，即别忘了添加引号
        System.out.println("#map[address]: " + Ognl.getValue("#map['address']", ognlContext, ognlContext.getRoot()));
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