package com.univ.thirdutils.jackson;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
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

    private ObjectMapper objectMapper = new ObjectMapper();

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

    /**
     * 将数组形式的json字符串转换成list形式的java类型
     */
    @Test
    public void list() throws IOException {
        User u1 = new User();
        u1.setAge(20);
        u1.setName("aaa");

        User u2 = new User();
        u2.setAge(30);
        u2.setName("bbb");

        User u3 = new User();
        u3.setAge(40);
        u3.setName("ccc");

        List<User> list = Arrays.asList(u1, u2, u3);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(list);
        System.out.println(str);
        // [{"name":"aaa","age":20},{"name":"bbb","age":30},{"name":"ccc","age":40}]

        String jsonStr = "[{\"name\":\"aaa\",\"age\":20},{\"name\":\"bbb\",\"age\":30},{\"name\":\"ccc\",\"age\":40}]";

        // json字符串转成数组
        User[] users = objectMapper.readValue(jsonStr, User[].class);
        System.out.println(Arrays.toString(users));
        // [User{name='aaa', age=20}, User{name='bbb', age=30}, User{name='ccc', age=40}]

        // json字符串转成list
        List list1 = objectMapper.readValue(jsonStr, List.class);
        // Set list1 = objectMapper.readValue(jsonStr, Set.class);
        System.out.println(list1);
        // [{name=aaa, age=20}, {name=bbb, age=30}, {name=ccc, age=40}]

    }

    /**
     * json字符串转成List<T>
     * 转成Set<T>也是类似
     * @throws IOException
     */
    @Test
    public void jsonToList() throws IOException {
        
        String jsonStr = "[{\"name\":\"aaa\",\"age\":20},{\"name\":\"bbb\",\"age\":30},{\"name\":\"ccc\",\"age\":40}]";
        ObjectMapper objectMapper = new ObjectMapper();
        // 转成list
        List list = objectMapper.readValue(jsonStr, List.class);
        System.out.println(list);
        // [{name=aaa, age=20}, {name=bbb, age=30}, {name=ccc, age=40}]

        // 转成List<User>
        JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
        List<User> list1 = objectMapper.readValue(jsonStr, listType);
        System.out.println(list1);
        // [User{name='aaa', age=20}, User{name='bbb', age=30}, User{name='ccc', age=40}]
        // 注意list1与list的差别
    }

    /**
     * json字符串围成Map<K,V>
     * @throws IOException
     */
    @Test
    public void jsonToMap() throws IOException {
        String mapStrJson = "{\"third\":{\"name\":\"ccc\",\"age\":40},\"first\":{\"name\":\"aaa\",\"age\":20},\"second\":{\"name\":\"bbb\",\"age\":30}}";
        // 转成map
        JavaType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, User.class);
        Map<String, User> map = objectMapper.readValue(mapStrJson, mapType);
        System.out.println(map);
        // {third=User{name='ccc', age=40}, first=User{name='aaa', age=20}, second=User{name='bbb', age=30}}
    }

    /**
     * json字符串转成集合泛型
     * @throws IOException
     */
    @Test
    public void jsonToMighty() throws IOException {
        /*Author author = new Author();
        author.setAge(20);
        author.setName("abc");

        Book<Author> book = new Book<>();
        book.setValue(author);
        book.setName("book");

        String str = objectMapper.writeValueAsString(book);
        System.out.println(str);*/
        // {"name":"book","value":{"name":"abc","age":20}}

        String jsonStr = "{\"name\":\"book\",\"value\":{\"name\":\"abc\",\"age\":20}}";
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Book.class, Author.class);
        Book<Author> book1 = objectMapper.readValue(jsonStr, javaType);
        System.out.println(book1);
        // Book{name='book', value=Author{name='abc', age=20}}

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

class Book<T> {
    private String name;
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

class Author {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}