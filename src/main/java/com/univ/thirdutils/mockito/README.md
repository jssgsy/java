# 测试框架mockito

maven依赖
```xml
<!--测试框架-->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-all</artifactId>
    <version>1.10.19</version>
    <!--生产环境中这里可以设为test-->
    <scope>compile</scope>
</dependency>
```

# @Mock与@InjectMocks的区别
> @Mock creates a mock. @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock (or @Spy) annotations into this instance.
Note that you must use @RunWith(MockitoJUnitRunner.class) or Mockito.initMocks(this) to initialize these mocks and inject them.

总结：用@InjectMocks模拟service层对象(需要测试业务逻辑方法的对象)，用@Mock来模拟代表数据源的对象(如dao，es，缓存)等