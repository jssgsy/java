package com.univ.patterndesign.state.simple;

/**
 * @author univ
 * date 2023/11/14
 */
public class Context {
    // 默认状态
    private State state = new NewState();

    // 用来转移状态
    public void setState(State state) {
        this.state = state;
    }

    public void business() {
        // another thing

        // 由状态决定的业务交由状态类处理
        // 注意，这里传递了this，是状态的主类，也可以认为是状态执行上下文
        state.doAction(this);
    }
}
