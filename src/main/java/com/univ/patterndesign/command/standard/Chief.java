package com.univ.patterndesign.command.standard;

/**
 * 厨师，命令最终的执行者，即Receiver
 * <p>
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
