# [官网](https://neo4j.com/docs/getting-started/current/languages-guides/java/java-intro/)

# CypherExample
正好sql是关系型数据库的查询语言一样，cypher是图数据库的查询语言；

# 驱动
```
<groupId>org.neo4j.driver</groupId>
<artifactId>neo4j-java-driver</artifactId>
<version>5.7.0</version>
```
* 注意版本问题：When developing with `Neo4j 5.x, use Java 17` and your preferred IDE.
* 使用驱动的方式就和使用jdbc一样，是比较原始的方式，cypher都是原始的字符串拼接；