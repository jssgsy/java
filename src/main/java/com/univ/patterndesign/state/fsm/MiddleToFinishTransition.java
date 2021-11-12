package com.univ.patterndesign.state.fsm;

/**
 * @author univ
 * 2021/11/12 5:39 下午
 */
public class MiddleToFinishTransition extends Transition {

    public MiddleToFinishTransition(StateEnum currentState, EventTypeEnum event, StateEnum nextState) {
        super(currentState, event, nextState);
    }

    @Override
    public StateEnum dealBusinessLogical(Event event) {
        // 找到业务数据
        Long businessId = event.getBusinessId();
        // do business logical
        System.out.println("状态【" + currentState.name() +  "】" + "通过事件【" + event.toString() + "]" + "转移成了状态【" + nextState.name() + "】");
        return nextState;
    }
}
