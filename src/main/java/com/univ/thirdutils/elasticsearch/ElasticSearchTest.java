package com.univ.thirdutils.elasticsearch;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author univ
 * @date 2019/7/30 8:18 PM
 * @description
 * 1. 客户端的版本和服务器端的版本要一致，如服务器端的版本为6.2.4，则引入的es的pom依赖也要为6.2.4，避免出现不兼容情况；
 *
 */
public class ElasticSearchTest {

    private RestHighLevelClient client;

    private final String INDEX = "gb";

    private final String TYPE = "gb_type";

    @Before
    public void setUp() {

        System.out.println(System.getProperty("java.classpath"));
        /**
         * 初始化client，连接到es
         */
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @After
    public void tearDown() {
        try {
            /**
             * Q：为何需要close？
             * A：The high-level client will internally create the low-level client used to perform requests based on the provided builder.
             * That low-level client maintains a pool of connections and starts some threads
             * so you should close the high-level client when you are well and truly done with it
             * and it will in turn close the internal low-level client to free those resources.
             * 即：释放掉内部用来维护连接的连接池及线程资源
             */
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要仔细的查看GetRequest与GetResponse等对象，建立体系
     * @throws IOException
     */
    @Test
    public void getDocument() throws IOException {
        // 在初始化时就指定index、type、与id(注意是string类型)
        GetRequest getRequest = new GetRequest(INDEX, TYPE, "1");
        GetResponse response = client.get(getRequest);
        System.out.println(response);

        // 注意type是可选的，GetRequest提供了很多方法(链式)来设置参数
        /*GetRequest getRequest1 = new GetRequest(INDEX);
        getRequest1.id("1");
        client.get(getRequest);*/
    }

    /**
     * 1. {@link org.elasticsearch.action.get.GetRequest#fetchSourceContext}：设置只获取哪些_source中的字段，或者排除哪些_source中的字段
     */
    @Test
    public void fetchSourceContext() throws IOException {
        GetRequest getRequest = new GetRequest(INDEX, TYPE, "1");

        // 只取_source中的userId字段
        String[] includes = new String[]{"userType"};
        // 不排除_source中的任何字段
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext sourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(sourceContext);
        System.out.println(client.get(getRequest));
    }

    /**
     * {@link org.elasticsearch.action.get.GetRequest#storedFields}：只获取_source中指定的字段，默读为_source
     * 前提：必须在创建索引时指定了字段为stored。如下，counter字段的store属性被置为false，只有tags字段的store属性被置为true
     * PUT twitter
     {
         "mappings": {
         "tweet": {
             "properties": {
                 "counter": {
                     "type": "integer",
                     "store": false
                 },
                 "tags": {
                     "type": "keyword",
                     "store": true
                     }
                 }
             }
         }
     }
     */
    @Test
    public void storedFields() throws IOException {
        GetRequest getRequest = new GetRequest(INDEX, TYPE, "1");
        getRequest.storedFields("userType");
        // 因为userType与userType的store属性均没有指定为true，因此下面输出均为null
        System.out.println(client.get(getRequest).getField("userType"));
        System.out.println(client.get(getRequest).getField("userType"));
    }
}
