package com.univ.patterndesign.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import lombok.Setter;

/**
 * @author univ
 * @date 2020/12/18 10:17 上午
 * @description
 */
public class CommandTest {

    @Test
    public void test() {
        // 来了一个顾客
        Customer customer = new Customer();

        // 有一个厨师(最终完成命令的对象)
        Chief chief = new Chief();

        // 可能有哪些请求(命令)
        OrderSteakCommand steakCommand = new OrderSteakCommand();
        // 此命令最终由谁实现
        steakCommand.setChief(chief);
        OrderVegetableCommand vegetableCommand = new OrderVegetableCommand();
        vegetableCommand.setChief(chief);

        // 顾客能发出哪些请求(命令)，此即将请求封装成命令对象
        /*customer.setSteakCommand(steakCommand);
        customer.setVegetableCommand(vegetableCommand);*/
        customer.addCommand(steakCommand);
        customer.addCommand(vegetableCommand);

        // 可动态新增命令
        customer.addCommand(steakCommand);
        // 亦可动态删除命令
        customer.removeCommand(steakCommand);

        // 下单(发出命令)
        /*customer.giveMeSteak();
        customer.giveMeVegetable();*/

        /**
         * 下单(有些将将多个命令打包一起执行的感觉)
         */
        customer.order();
    }
}

/**
 * 点菜命令
 */
interface OrderCommand {
    void execute();
}

/**
 * 厨师，命令最终的执行者，即Receiver
 *
 * 注：Invoker有多个方法(命令)，则Receiver就需要多少方法，因为命令最终都是Receiver实现的
 */
class Chief {

    /**
     * 做牛排
     */
    public void dealWithSteak() {
        System.out.println("牛排做好了");
    }

    /**
     * 做蔬菜
     */
    public void dealWithVegetable() {
        System.out.println("蔬菜做好了");
    }
}

/**
 * 来块牛排
 */
@Setter
class OrderSteakCommand implements OrderCommand {
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

/**
 * 来盘蔬菜
 */
@Setter
class OrderVegetableCommand implements OrderCommand {

    /**
     * 某个具体的命令需知道其最终由谁实现
     */
    private Chief chief;

    /**
     * 将命令转交给最终实现者去实现
     */
    @Override
    public void execute() {
        chief.dealWithVegetable();
    }
}

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
     * @param command
     */
    public void addCommand(OrderCommand command) {
        commandList.add(command);
    }

    /**
     * 客户可动态删除发出的命令
     * 重要：此即模式中所说的撤销操作(命令)
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
