# 依赖
```
<!--依赖了logback-core，所以不用显示引入logback-core-->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```
注：可能版本冲突，需要排除其它包依赖的slf4j, 如orika-core就依赖了slf4j-api