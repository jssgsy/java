# 简介
* [github](https://github.com/FasterXML/jackson);
* [入门看这里](https://www.baeldung.com/jackson);

与fastjson一样，是一个java bean与json字符串互转的工具。

# 常见注解
* @JsonIgnore：被标记的字段不参与serialization与deserialization过程；
* @JsonIgnoreProperties：指定哪些字段不参与serialization；
* @JsonInclude：只作用于serialization阶段，顾名思义，就是生成的json串中包含哪些内容；
* @JsonProperty：将java bean中的属性与json串中的哪个key对应(默认是一一对应)；
    * 参见JacksonBean2.java；
* @JsonFormat：用在Date类型上，顾名思义即可；
* @JsonInclude：

# 特点
* 支持接口(多态类)与json字符串互转；
    * fastjson是不支持的；
* 支持泛型，参见jacksonTest.java#generic;  
* 提供了丰富的自定义行为能力；
