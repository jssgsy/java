package com.univ.patterndesign.state.fsm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 事件(条件)：促使状态发生变化的事件(条件)
 */
@Data
@AllArgsConstructor
public class Event {

    /**
     * 每个事件有一个对应的事件类型
     */
    private EventTypeEnum eventTypeEnum;

    /**
     * 业务信息
     * 因事件是由业务发出的，也就是具体的事件知道业务的信息，因此可将业务信息封装到事件中
     *
     * 如果业务信息有多个的话，可考虑使用map，灵活性更大，这里简化成一个业务id
     */
    private Long businessId;

    /**
     * Q：把事件发生时需要做的事情放在这？
     * A：no，事件应该是纯粹的、独立的，其不知道当前的状态是什么
     */
    /*public void handle() {}*/
}
