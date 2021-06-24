package com.univ.patterndesign.command.simplified;

import org.junit.Test;

import com.univ.patterndesign.command.simplified.command.MobileCommandCall;
import com.univ.patterndesign.command.simplified.command.MobileCommandPlayGame;
import com.univ.patterndesign.command.simplified.command.MobileCommandShutdown;

/**
 * 简化版命令模式小结
 *  1. 命令模式可理解成【面向接口】开发的升级版；
 *  2. 由如下test2可知，使用命令模式时，每个请求都需要封装成一个命令对象，这可能会给系统引入大量的命令对象；
 *  3. 在客户端知道服务由谁提供时，用不着命令模式，不仅多引入了一个命令中间层，也引入了很多命令对象；
 *  4. 由3知，命令模式用在客户端不知道服务由谁提供(或者不想直接和服务提供方交互)的场景下，一个合适的例子是：客户点餐，客户不需要和厨师打交道，而只需要说一声来份蛋炒饭；
 *  5. 这里没有引入Invoker的概念，Invoker其实就是指命令的发送方，这里直接使用客户端代替了；
 *
 * @author univ
 * 2021/6/24 9:45 上午
 */
public class SimplifiedCommandTest {

    /**
     * 普通的面向接口开发
     */
    @Test
    public void test1() {
        MobileService mobileService = new MobileServiceImpl();

        /**
         * 客户端：即调用方，即这里的test1
         *
         * 客户端直接和接口交互
         *
         * 重点！：这里客户端知道要和哪个接口交互，或者说客户端需要知道
         */
        mobileService.call();
        mobileService.shutdown();
        mobileService.playGame();
    }

    /**
     * 简化版的命令模式，即升级版的面向接口开发
     */
    @Test
    public void test2() {

        /**
         * 这里客户端还是指test2
         *
         * 特点：
         *  1. 不像面向接口开发中，客户端知道(或者需要知道)和哪个接口交互，即知道由哪个接口来提供服务；
         *  2. 命令模式中，客户端不知道需要和哪个接口来交互，因此客户端只顾发出命令，而不是向具体的提供服务的接口发出命令；
         *      很自然的，这里就引出了命令这个中间层，用来关联客户端与命令的实现，
         *      言外之意包含，命令(Command)需要知道其最终由谁实现
         */
        // 下面四行代码由服务提供方提供，对客户端不可见
        MobileService mobileService = new MobileServiceImpl();
        MobileCommandCall mobileCommandCall = new MobileCommandCall(mobileService);
        MobileCommandShutdown mobileCommandShutdown = new MobileCommandShutdown(mobileService);
        MobileCommandPlayGame mobileCommandPlayGame = new MobileCommandPlayGame(mobileService);


        // 下面的代码由客户端使用：客户端想要做什么事情，发出一个命令即可，不用依赖具体的服务提供者(接口)
        // 我想要打电话
        mobileCommandCall.execute();

        // 我想要关机
        mobileCommandShutdown.execute();

        // 我想要玩游戏
        mobileCommandPlayGame.execute();
    }

}
