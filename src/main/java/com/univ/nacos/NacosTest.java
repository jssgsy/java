package com.univ.nacos;

import java.util.Properties;

import org.junit.Test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

/**
 * 配置中心相关
 *
 * @author univ
 * 2022/12/7 15:12
 */
public class NacosTest {

    /**
     * 即可以是ip，也可以是ip:port, port默认是8848
     */
    static String serverAddr = "127.0.0.1:8848";

    static String dataId = "com.unicom.dplus";

    static String group = "DEFAULT_GROUP";

    /**
     * 指定namespace，默认为空，即""
     */
    static String namespace = "";


    /**
     * 获取配置内容
     * 注：是获取整个配置文件的内容，不是获取单个配置项；单纯的nacos似乎也做不到配置项的维度，需结合spring完成
     *
     * @return
     * @throws NacosException
     */
    @Test
    public void getContent() throws NacosException {
        /**
         * 承载初始化时的各个配置参数
         */
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, namespace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
    }

    /**
     * 发布配置。<br>
     * 注：此方法是整个文件维度的，即如果之前配置文件中有内容，则会被这里给覆盖掉；
     *      即不是配置项的维度，单纯的nacos似乎也做不到配置项的维度，需结合spring完成
     *
     * @throws NacosException
     */
    @Test
    public void pushConf() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        System.out.println(isPublishOk);
    }

}
