package com.univ.patterndesign.command.standard;

import org.junit.Test;

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

