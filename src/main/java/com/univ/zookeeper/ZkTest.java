package com.univ.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import lombok.SneakyThrows;

/**
 * @author univ
 * 2021/9/16 2:17 下午
 */
public class ZkTest {

    @Test
    public void test() throws IOException, KeeperException, InterruptedException {

        String connectString = "127.0.0.1:2181";
        int sessionTimeOut = 5000;
        String zNode = "/zk_test";

        // watcher为null，表示不设置默认watcher
        ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeOut, null);
        Watcher watcher = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent event) {
                System.out.println("process收到WatchedEvent事件了");
                String path = event.getPath();
                Stat stat = new Stat(); // 用来接收返回结果的stat状态，不是很面向对象(用入参接收返回值)
                byte[] data = zooKeeper.getData(path, false, stat);
                System.out.println("process znode的值为： " + new String(data));
                System.out.println("返回znode的stat值为: " + stat);
            }
        };
        // 设置一个watcher，且丢弃返回znode的state信息
        byte[] zNodeData = zooKeeper.getData(zNode, watcher, null);
        System.out.println("znode的值为： " + new String(zNodeData));

        while (true) {

        }

    }
}
