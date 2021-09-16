package com.univ.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author univ
 * 2021/9/16 2:17 下午
 */
public class ZkTest {

    /**
     * 只是示例代码，不要在实际中使用
     */
    static ZooKeeper zooKeeper = null;

    @Test
    public void test() throws IOException, KeeperException, InterruptedException {

        String connectString = "127.0.0.1:2181";
        int sessionTimeOut = 5000;
        String zNode = "/zk_test";
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, event -> {
            String path = event.getPath();
            Watcher.Event.KeeperState keeperState = event.getState();
            Watcher.Event.EventType eventType = event.getType();
            System.out.println("监听到节点变动了，znode:" + path);
            System.out.println("监听到节点变动了，keeperState:" + keeperState.name());
            System.out.println("监听到节点变动了，eventType:" + eventType.name());
            try {
                // 重新注册:一个watcher只能被触发一次(收到一次消息)
                Stat stat = zooKeeper.exists(zNode, true);
                System.out.println("stat:" + stat);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        while (true) {

        }

    }
}
