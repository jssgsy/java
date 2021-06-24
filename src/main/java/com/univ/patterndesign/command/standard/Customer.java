package com.univ.patterndesign.command.standard;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;

/**
 * 点菜的顾客，即Invoker，即请求的发出者
 */
@Setter
class Customer {
    /**
     * 顾客(Invoker)不直接和厨师(Receiver)打交道，而是和【命令】打交道，即Invoker只管发出命令即可，命令自有人去执行
     */
    /*private OrderCommand vegetableCommand;
    private OrderCommand steakCommand;*/

    /**
     * 发出命令：来块牛排
     */
    /*public void giveMeSteak() {
        steakCommand.execute();
    }*/

    /**
     * 发出命令：来盘蔬菜
     */
    /*public void giveMeVegetable() {
        vegetableCommand.execute();
    }*/

    /**
     * 客户可能会有多个命令，用list表示更灵活(反正命令的实现只是简单转交给Receiver)，可新增可删除(由客户端控制)
     */
    private List<OrderCommand> commandList = new ArrayList<>();

    /**
     * 客户可动态新增能发出的命令
     * 重要：此即模式中所说的重做
     *
     * @param command
     */
    public void addCommand(OrderCommand command) {
        commandList.add(command);
    }

    /**
     * 客户可动态删除命令
     * 重要：此即模式中所说的撤销操作(命令)
     *
     * @param command
     */
    public void removeCommand(OrderCommand command) {
        commandList.remove(command);
    }

    public void order() {
        for (OrderCommand command : commandList) {
            command.execute();
        }
    }
}
