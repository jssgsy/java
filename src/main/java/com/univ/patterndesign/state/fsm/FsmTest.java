package com.univ.patterndesign.state.fsm;

import org.junit.Test;

/**
 * 有限状态机
 * @author univ
 * 2021/11/12 5:42 下午
 */
public class FsmTest {

    @Test
    public void testRun() {
        StateMachine stateMachine = new StateMachine();
        // 注：事件产生时就需要有业务数据了，可以理解成:具体哪个数据发生了转变
        stateMachine.run(StateEnum.NEW, new NewToMiddleEvent(1L));

        stateMachine.run(StateEnum.MIDDLE, new MiddleToFinishEvent(1L));

        stateMachine.run(StateEnum.FINISH, new MiddleToFinishEvent(1L));

        /**
         * 注：上述的测试只是给定了输入，然后促使状态机进行运转，但仅仅只是运转到下一个状态。
         * 实际项目中，状态机肯定是需要永不停歇的运转的，此时要考虑状态机的持续动力是什么，
         * 动力一般是由业务来提供，如消费到binlog或者监听到消息
         */
    }
}
