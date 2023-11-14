package com.univ.patterndesign.state.simple;

/**
 * @author univ
 * date 2023/11/14
 */
public interface State {
    void doAction(Context context);
}

class NewState implements State {
    @Override
    public void doAction(Context context) {
        // doSomething

        // 状态转移，根据实际情况
        context.setState(new RunningState());
    }
}

class RunningState implements State {
    @Override
    public void doAction(Context context) {
        // doSomething

        // 状态转移，根据实际情况
        context.setState(new EndState());
    }
}

class EndState implements State {
    @Override
    public void doAction(Context context) {
        // doSomething

        // 状态转移，根据实际情况
        // context.setState(null);
    }
}