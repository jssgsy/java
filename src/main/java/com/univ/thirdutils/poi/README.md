# 引入依赖
```xml
<!--处理excel文件-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.17</version>
</dependency>
<!--这个也不能少-->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.17</version>
</dependency>

```

# 注意
不同版本的excel使用的api不同，在高版本的poi中可以直接使用统一的WorkBook，Sheet等来统一处理