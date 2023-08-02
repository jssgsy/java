package com.univ.thirdutils.neo4j;

/**
 * 使用驱动的方式演示cypher语言的基本用法
 *
 * @author univ
 * 2023/4/7 09:19
 */
public class CypherExample {
/*

    */
/**
     * 固定使用bolt协议
     *//*

    String uri = "bolt://localhost:7687";

    String username = "neo4j";

    String password = "12345678";

    */
/**
     * 这个是neo4j中的类，不是java.sql.Driver
     *//*

    private Driver driver;

    */
/**
     * 类似于jdbc中的Connection对象
     *//*

    private Session session;

    @Before
    public void before() {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
        this.session = this.driver.session();
    }

    @After
    public void after() throws RuntimeException {
        driver.close();
        session.close();
    }

    */
/**
     * 创建不带任何属性的节点
     *
     * session.run：这里没有在事务中执行；
     *//*

    @Test
    public void createNode() {
        Query query = new Query("create (a:Person) return a");
        Result result = session.run(query);
        // k: a , v: []
        printResult(result);
    }

    */
/**
     * 创建带属性的切点
     *
     * 注：session.executeWrite，这里在事务中执行的；
     *//*

    @Test
    public void createNode2() {
        Query query = new Query("create (a:Person {name: \"hello\", age:32}) return a");
        session.executeWrite((tx) -> {
            // 重点：Result必须在事务中使用，不能返回给executeWrite再使用，要看下此源码
            Result result = tx.run(query);
            // k: a , v: [name, age]
            printResult(result);
            return result;
        });
    }

    */
/**
     * 创建节点且设置节点属性，使用参数映射
     *//*

    @Test
    public void createNode3() {
        String message = "okk";
        // 使用参数映射，比直接写字符串拼接要方便一些
        Query query = new Query("CREATE (a:Greeting) SET a.message = $message RETURN a",
                Values.parameters("message", message));
        session.executeWrite((tx) -> {
            Result result = tx.run(query);
            // k: a , v: [message]
            printResult(result);
            return result;
        });
    }

    @Test
    public void queryCypher() {
        Query query = new Query("match (a:Person) return a");
        Result result = session.run(query);
        printResult(result);
    }

    */
/**
     * 清空db
     *//*

    @Test
    public void emptyDB() {
        Query query = new Query("match (a:Person) delete a");
        session.run(query);
        System.out.println("db empty");
    }

    private void printResult(Result result) {
        result.stream().forEach(r -> r.keys().forEach(k -> System.out.println("k: " + k + " , v: " + r.get(k).keys())));
    }

    */
/**
     * 创建关系，本质其实都是Cypher的应用
     *//*

    @Test
    public void createRelation() {
        Query query = new Query("create (s:Student) -[:read_in]-> (s1:School) return s, s1");
        Result result = session.run(query);
        printResult(result);
    }
*/
}
