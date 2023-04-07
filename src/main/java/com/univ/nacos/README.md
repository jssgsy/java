# [官网](https://nacos.io/zh-cn/docs/v2/guide/user/sdk.html)

# 目标
单纯java使用nacos备忘；

# 依赖
```xml
<!-- https://mvnrepository.com/artifact/com.alibaba.nacos/nacos-client -->
<dependency>
    <groupId>com.alibaba.nacos</groupId>
    <artifactId>nacos-client</artifactId>
    <version>2.0.0</version>
</dependency>
```
注，由于Nacos Java SDK在2.0版本后引入了gRPC，导致nacos的包较大，可考虑使用纯净版的依赖，具体参见官网；