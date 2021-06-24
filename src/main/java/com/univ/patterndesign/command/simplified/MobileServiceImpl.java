package com.univ.patterndesign.command.simplified;

/**
 * @author univ
 * 2021/6/24 9:44 上午
 */
public class MobileServiceImpl implements MobileService {

    @Override
    public void call() {
        System.out.println("MobileServiceImpl#打电话了");
    }

    @Override
    public void shutdown() {
        System.out.println("MobileServiceImpl#关机了");
    }

    @Override
    public void playGame() {
        System.out.println("MobileServiceImpl#玩游戏了");
    }
}
