# [参考资料](https://www.baeldung.com/javax-validation)

# 何为validate？
其实就是校验某个参数，某个字段是否符合一定的要求，如是否为空，长度等等。这样可以少写很多if之类的判断语句。
都在javax.validation包下。
注意：在纯java应用中，不是使用了限制注解(@NotNull等)，则在程序运行中会自动判断，而是要调用相应的api去手动校验，所以这种方式肯定不会直接在项目中使用，而是借助spring mvc，此时如果参数不对则自动出错。

# 引入依赖
```xml
<!--java bean validation，只是定义了接口，真正的实现在hibernate-validator中(hibernate-validator是最常用的一种实现)-->
<!--<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>2.0.1.Final</version>
</dependency>-->

<!--传递依赖了validation-api，所以不用再显示引入-->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>5.2.1.Final</version>
</dependency>

<!--使用java bean validation不可少的一个依赖-->
<dependency>
    <groupId>javax.el</groupId>
    <artifactId>javax.el-api</artifactId>
    <version>3.0.1-b06</version>
</dependency>
```

# 使用
1. 获得Validator对象；
2. 构造需要校验的对象obj；
3. 用第一步中的Validator对象去校验第二步中需要校验的对象；
4. 校验是否有校验失败的字段；

# 常用的限制(Constraint)
* @NotNull
* @NotBlank
* @Min(注意，修饰包装类型时，如果值为null，则会经过校验)
* @Max