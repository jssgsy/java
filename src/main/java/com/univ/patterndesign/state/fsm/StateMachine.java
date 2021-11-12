package com.univ.patterndesign.state.fsm;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态机，其职责为：接收一个输入(当前状态与具体的事件)，输出下一个状态(当然，其中会做一些业务逻辑)
 */
public class StateMachine {

    /**
     * 让状态机运转起来：通过当前状态和事件让状态变更到下一状态
     * @param state 当前状态
     * @param event 事件，注意，不是事件类型，流转是基于具体的事件进行的(携带有业务数据)
     * @return 下一状态
     */
    public StateEnum run(StateEnum state, Event event) {
        for (Transition transition : transitionList) {
            // 找转换：转换是三元组(当前状态，事件类型，下个状态)，当前状态、事件类型唯一确认一个转换
            if (transition.getCurrentState().equals(state) && transition.getEventTypeEnum().equals(event.getEventTypeEnum())) {
                // 状态转移时需要处理的业务逻辑
                transition.dealBusinessLogical(event);
                return transition.getNextState();
            }
        }
        return null;
    }

    /**
     * 封装了所有的转换
     */
    private List<Transition> transitionList;

    /**
     * 由状态机维护所有的转换枚举
     */
    public StateMachine() {
        transitionList = new ArrayList<>();
        transitionList.add(new NewToMiddleTransition(StateEnum.NEW, EventTypeEnum.NEW_TO_MIDDLE, StateEnum.MIDDLE));
        transitionList.add(new MiddleToFinishTransition(StateEnum.MIDDLE, EventTypeEnum.MIDDLE_TO_FINISH, StateEnum.FINISH));
    }
}















