package com.univ.thirdutils.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

        // 当然，就这种情形而言不借助TypeReference也是可以完成的，这是因为有parseArray方法
        JSONObject.parseArray(json, Ge1.class);
        JSONObject.parseArray(json, Ge2.class);

        // 但如果是MyObj<Ge1>这样的就必须借助TypeReference了
    }

    @Test
    public void mustUseTypeReference() {
        String json = "{\"data\":{\"name\":\"zhangsan\", \"address\":\"hangzhou\"}}";
        MyObj<Ge1> parsed = JSONObject.parseObject(json, new TypeReference<MyObj<Ge1>>() {});
        System.out.println(parsed);
    }

    // 对各种时间类型的默认处理
    @Test
    public void timeDefault() {
        System.out.println(JSONObject.toJSONString(new TimeObj()));
        // {"date":1692933166417,"localDate":"2023-08-25","localDateTime":"2023-08-25T11:12:46.416"}
    }

    /**
     * date类型的默认字符串形式
     */
    @Test
    public void timeDateStrDefault() {
        System.out.println(JSONObject.toJSONString(new TimeObj(), SerializerFeature.WriteDateUseDateFormat));
        // {"date":"2023-08-25 11:51:45","localDate":"2023-08-25","localDateTime":"2023-08-25T11:51:45.687"}
    }

    @Test
    public void timeDateCustomize() {
        System.out.println(JSONObject.toJSONStringWithDateFormat(new TimeObj(), "yyyy/MM/dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat));
        // {"date":"2023/08/25 13:43:12","localDate":"2023-08-25","localDateTime":"2023/08/25 13:43:12"}
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

@Data
class MyObj<T> {
    T data;
}

/**
 * JSONField注解适用于所有时间类型，如LocalDateTime、LocalDate、Date等
 */
@Data
class TimeObj {
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss", name = "llll")
    private LocalDateTime localDateTime = LocalDateTime.now();

//    @JSONField(format = "yyyy/MM/dd")
    private LocalDate localDate = LocalDate.now();

//    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date date = new Date();
}