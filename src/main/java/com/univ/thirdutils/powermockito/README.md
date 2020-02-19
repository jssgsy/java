# maven依赖
power mockito的jar包已经依赖了junit与mockito包，直接引入即可。
需要额外注意的是，power mockito依赖了org.javassist#javassist包，而有很多其它包可能也依赖了此包，非常容易冲突。必要时需要手动引入此包，以便解决冲突。
```xml
<!--必要时手动加入，这里的版本记得和powermock.version中依赖的版本一致-->
<dependency>
    <artifactId>javassist</artifactId>
    <groupId>org.javassist</groupId>
    <version>3.24.0-GA</version>
</dependency>

<powermock.version>2.0.2</powermock.version>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-module-junit4</artifactId>
    <version>${powermock.version}</version>
    <!--<scope>test</scope>-->
</dependency>
<dependency>
    <groupId>org.powermock</groupId>
    <artifactId>powermock-api-mockito2</artifactId>
    <version>${powermock.version}</version>
    <!--<scope>test</scope>-->
</dependency>
```