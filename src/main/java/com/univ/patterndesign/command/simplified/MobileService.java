package com.univ.patterndesign.command.simplified;

/**
 * 电话服务
 * @author univ
 * 2021/6/24 9:42 上午
 */
public interface MobileService {

    /**
     * 打电话
     */
    void call();

    /**
     * 关机
     */
    void shutdown();

    /**
     * 玩游戏
     */
    void playGame();
}
