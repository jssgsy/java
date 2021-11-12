package com.univ.patterndesign.state.fsm;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 转移，是一个封装概念，是一个三元组(当前状态，引起转移的事件类型，下一个状态)
 *
 * 转移也是可枚举的，一般维护在状态机中
 *
 * 注：把事件发生时的动作也封装在此了，丰富了转移的内涵，方便编程
 */
@Getter
@AllArgsConstructor
public abstract class Transition {
    // 如下三个字段为转移的上下文

    /**
     * 当前状态
     */
    protected StateEnum currentState;

    /**
     * 使状态发生改变的【事件类型】
     *
     * 注意这里的语义，是【事件类型】，而不是具体的【事件实例】，因此这里的类型不能用Event
     */
    protected EventTypeEnum eventTypeEnum;

    /**
     * 转移后的状态
     */
    protected StateEnum nextState;

    /**
     * 当状态由一个转变为另一个时业务上应该做的事情
     *
     * @return 转换后的状态
     */
    abstract public StateEnum dealBusinessLogical(Event event);
}
