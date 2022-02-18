package com.univ.thirdutils.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

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

    /**
     * jackson最核心元素：pojo与java bean互转的桥梁
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json转java bean
     * 核心方法：readValue
     * 注：要转换的json来源可以是其它地方，如文件，URL，InputStream等。
     * @throws IOException
     */
    @Test
    public void pojoToBean() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"name\":\"univ\",\"age\":28}";
        JacksonBean jacksonBean = objectMapper.readValue(jsonStr, JacksonBean.class);
        System.out.println(jacksonBean);
        // {"age":28,"birthday":"2018-12-08 08:39:30","nameV2":"univ"}
    }

    /**
     * java bean转json
     * 核心方法：writeValueAsString
     * 注：转成后的json可以存往其它地方，如文件、甚至OutputStream等，用writeValue方法
     */
    @Test
    public void beanToPojo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(new JacksonBean());
        System.out.println(str);    // {"name":"univ","age":28}

        // 写入到文件中
        objectMapper.writeValue(new File("src/main/java/com/univ/thirdutils/jackson/file.txt"), new JacksonBean());
    }

    /**
     * jackson的常用注解
     * @JsonIgnore
     * @JsonProperty
     * @JsonFormat
     */
    @Test
    public void annotationJackson() throws IOException {
        JacksonBean2 jacksonBean2 = new JacksonBean2();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(jacksonBean2);
        System.out.println(jsonStr);

        // json串中必须有nameV2
        String str = "{\"age\":28,\"birthday\":\"2022-02-17 19:22:52\",\"nameV2\":\"univ\", \"id\":100}";
        JacksonBean2 user11 = objectMapper.readValue(str, JacksonBean2.class);
        System.out.println(user11);
    }

    /**
     * 将数组形式的json字符串转换成list形式的java类型
     */
    @Test
    public void list() throws IOException {
        JacksonBean u1 = new JacksonBean();
        u1.setAge(20);
        u1.setName("aaa");

        JacksonBean u2 = new JacksonBean();
        u2.setAge(30);
        u2.setName("bbb");

        JacksonBean u3 = new JacksonBean();
        u3.setAge(40);
        u3.setName("ccc");

        List<JacksonBean> list = Arrays.asList(u1, u2, u3);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(list);
        System.out.println(str);
        // [{"name":"aaa","age":20},{"name":"bbb","age":30},{"name":"ccc","age":40}]

        String jsonStr = "[{\"name\":\"aaa\",\"age\":20},{\"name\":\"bbb\",\"age\":30},{\"name\":\"ccc\",\"age\":40}]";

        // json字符串转成数组
        JacksonBean[] jacksonBeans = objectMapper.readValue(jsonStr, JacksonBean[].class);
        System.out.println(Arrays.toString(jacksonBeans));
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
        // 转成list ！！！错误用法！！！
        List list = objectMapper.readValue(jsonStr, List.class);
        System.out.println(list);
        // [{name=aaa, age=20}, {name=bbb, age=30}, {name=ccc, age=40}]

        // 转成List<User> ！！！正确用法！！！
        JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, JacksonBean.class);
        List<JacksonBean> list1 = objectMapper.readValue(jsonStr, listType);
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
        JavaType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, JacksonBean.class);
        Map<String, JacksonBean> map = objectMapper.readValue(mapStrJson, mapType);
        System.out.println(map);
        // {third=User{name='ccc', age=40}, first=User{name='aaa', age=20}, second=User{name='bbb', age=30}}
    }

    /**
     * json字符串转成泛型
     * @throws IOException
     */
    @Test
    public void generic() throws IOException {
        /*Author author = new Author();
        author.setAge(20);
        author.setName("abc");
JsonTypeInfo
        Book<Author> book = new Book<>();
        book.setValue(author);
        book.setName("book");

        String str = objectMapper.writeValueAsString(book);
        System.out.println(str);*/
        // {"name":"book","value":{"name":"abc","age":20}}

        String jsonStr = "{\"name\":\"book\",\"value\":{\"name\":\"abc\",\"age\":20}}";
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericBook.class, Author.class);
        GenericBook<Author> genericBook1 = objectMapper.readValue(jsonStr, javaType);
        System.out.println(genericBook1);
        // Book{name='book', value=Author{name='abc', age=20}}
    }
}