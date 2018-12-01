package com.univ.thirdutils.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author univ
 * @datetime 2018/12/1 9:50 PM
 * @description 本地缓存LoadingCache的使用
 */
public class LoadingCacheTest {

    private LoadingCache<String, Integer> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(10)    // 最多能存入的缓存的key的个数
            .expireAfterAccess(2, TimeUnit.HOURS) // 每个key在读、写后超过一定时间后没有被再次读、写，则自动从缓存中删除
            .expireAfterWrite(1, TimeUnit.DAYS) // // 每个key在创建、更新后超过一定时间后没有被再次写(不包括读)，则自动从缓存中删除
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    // 从数据库或者其它途径获取数据
                    return 10;
                }
            });

    /**
     * 注意，这里用的junit测试，所以需要多次调用get方法以模拟出第一次get时会调用load方法将缓存返回的结果， 后面的调用会直接从缓存中拿
     * @throws ExecutionException
     */
    @Test
    public void test() throws ExecutionException {
        // 第一次被调用时缓存未命中，此时会调用load访求并将返回结果进行缓存
        System.out.println(loadingCache.get("hello"));

        // 后面的访问就直接从缓存中进行获取了
        System.out.println(loadingCache.get("hello"));
    }
}
