package com.univ.patterndesign.command.standard;

import lombok.Setter;

/**
 * 来块牛排
 */
@Setter
class OrderSteakCommand implements OrderCommand{

    /**
     * 某个具体的命令需知道其最终由谁实现
     */
    private Chief chief;

    /**
     * 将命令转交给最终实现者去实现
     */
    @Override
    public void execute() {
        chief.dealWithSteak();
    }
}
