package com.univ.patterndesign.command.simplified.command;

import com.univ.patterndesign.command.simplified.MobileService;

/**
 * @author univ
 * 2021/6/24 9:59 上午
 */
public class MobileCommandPlayGame implements MobileCommand {

    /**
     * 具体的命令当然知道其由谁来实现
     */
    private MobileService mobileService;

    public MobileCommandPlayGame(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    @Override
    public void execute() {
        mobileService.playGame();
    }
}
