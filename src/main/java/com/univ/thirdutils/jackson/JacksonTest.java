package com.univ.thirdutils.jackson;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author univ
 * @datetime 2018/12/8 8:20 AM
 * @description
 * 参考网络资料：https://www.cnblogs.com/winner-0715/p/6109225.html
 *
 * 使用方法：
 * 1. 引入依赖；
 * 2. 核心类：ObjectMapper
 * 2. 对象转json字符串：objectMapper.writeValueAsString();
 * 3. json字符串转对象：objectMapper.readValue();
 */
public class JacksonTest {

    @Test
    public void basic() throws IOException {
        // 对象转json
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(new User());
        System.out.println(str);    // {"name":"univ","age":28}


        // json字符串转对象
        String jsonStr = "{\"name\":\"univ\",\"age\":28}";
        // 第二个参数说明将jsonStr转换成User类
        User user = objectMapper.readValue(jsonStr, User.class);
        System.out.println(user);
        // {"age":28,"birthday":"2018-12-08 08:39:30","nameV2":"univ"}
        // 注意：结果中没有id字段，User类中name字段变成了nameV2，birthday是yyyy-MM-dd HH:mm:ss的格式

    }

    /**
     * jsckson的常用注解
     * @JsonIgnore
     * @JsonProperty
     * @JsonFormat
     */
    @Test
    public void annotationJackson() throws JsonProcessingException {
        User1 user1 = new User1();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(user1);
        System.out.println(jsonStr);
    }
}

class User {
    private String name = "univ";
    private Integer age = 28;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class User1 {

    @JsonIgnore // 序列化时忽略此字段
    private Long id = 10l;

    @JsonProperty("nameV2") // 将name属性映射成nameV2
    private String name = "univ";

    private Integer age = 28;

    // 注意不要忘了timezone = "GMT+8"
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
