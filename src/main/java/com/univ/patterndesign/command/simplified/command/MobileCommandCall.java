package com.univ.patterndesign.command.simplified.command;

import com.univ.patterndesign.command.simplified.MobileService;

/**
 * 打电话命令
 * @author univ
 * 2021/6/24 9:56 上午
 */
public class MobileCommandCall implements MobileCommand{

    /**
     * 具体的命令当然知道其由谁来实现
     */
    private MobileService mobileService;

    public MobileCommandCall(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    @Override
    public void execute() {
        mobileService.call();
    }
}
