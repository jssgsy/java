package com.univ.thirdutils.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.junit.Test;

import java.util.List;

/**
 * @author univ
 * 2022/2/17 5:04 下午
 */
public class FastJsonTest {

    @Test
    public void test() {

        // JSONObject.parseObject();
    }

    /**
     * 转换成泛型
     * 借助TypeReference
     * 底层是 ParameterizedType
     */
    @Test
    public void genericParse() {
        String json = "[{\"name\":\"zhangsan\", \"address\":\"hangzhou\"}]";
        // 指定要转换成的类型为：List<Ge1>
        List<Ge1> g1s = JSONObject.parseObject(json, new TypeReference<List<Ge1>>() {});
        System.out.println("ge1：" + g1s);

        // 指定要转换成的类型为：List<Ge2>
        List<Ge2> g2s = JSONObject.parseObject(json, new TypeReference<List<Ge2>>() {});
        System.out.println("g2s：" + g2s);

        // 当然，就这种情形而言不借助TypeReference也是可以完成的
        JSONObject.parseArray(json, Ge1.class);
        JSONObject.parseArray(json, Ge2.class);
    }
}

@Data
class Ge1 {
    private String name;
    private String address;
}

@Data
class Ge2 {
    private String name;
    private String address;
}