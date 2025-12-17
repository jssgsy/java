package com.univ.ognl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.io.IOException;
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

    /**
     * 演示为何从context中取值时仍然要传入root对象：为了复用context对象
     */
    @Test
    public void whyStillNeedPassRoot() throws OgnlException {
        // 共享上下文，可能只包含工具类等
        OgnlContext sharedContext = (OgnlContext) Ognl.createDefaultContext(null); // 或传入一个空对象

        User u1 = new User("Charlie");
        User u2 = new User("David");

        // 第一次调用：使用 u1
        System.out.println(Ognl.getValue("name", sharedContext, u1));;

        // 第二次调用：使用 u2
        System.out.println(Ognl.getValue("name", sharedContext, u2));;
    }

    /**
     * ognl也可以处理List<Object>或List<Map<String, Object>>等类型
     */
    @Test
    public void dealObject() throws IOException, OgnlException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "\n" +
                "\t\t\t[{\"buildMainName\": \"1231\", \"buildMainTime\": 13, \"buildMainUnitFunction\": \"基础电信运营商\", \"buildMainTotalItCapacity\": 23, \"buildMainTotalItCapacityUnit\": \"兆瓦\", \"buildMainIsSpecializedCapability\": \"1\", \"buildMainLargeScaleFacilityProjectAmount\": 334, \"buildMainMediumScaleFacilityProjectAmount\": 12}, \n" +
                "\t\t\t{\"buildMainName\": \"12\", \"buildMainTime\": 2, \"buildMainUnitFunction\": \"互联网及云厂商\", \"buildMainTotalItCapacity\": 2, \"buildMainTotalItCapacityUnit\": \"兆瓦\", \"buildMainIsSpecializedCapability\": \"1\", \"buildMainLargeScaleFacilityProjectAmount\": 2, \"buildMainMediumScaleFacilityProjectAmount\": 2}\n" +
                "\t\t]";
        // 此时listObjects中的对象Object其实是一个Map；
        List<Object> listObjects = objectMapper.readValue(jsonStr, new TypeReference<List<Object>>() {
        });
        Object value = Ognl.getValue("[0].buildMainUnitFunction", listObjects);
        System.out.println(listObjects);
        System.out.println(value);
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}