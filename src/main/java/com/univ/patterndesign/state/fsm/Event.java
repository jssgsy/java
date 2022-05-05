package com.univ.patterndesign.state.fsm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 事件(条件)：促使状态发生变化的事件(条件)
 *
 * 注：这里将事情发生时的业务数据放在了事件中(离的最近)，这种应该是最合理的。
 *
 * 也可以考虑单独将业务数据抽象成一个参数上下文，这样就不需要此类的存在了。不过似乎没有放在Event类中更好，语义上也是随着Event而产生
 *
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
     * A：no，事件应该是纯粹的、独立的，其不知道当前的状态是什么，也不知道下一个状态是什么
     */
    /*public void handle() {}*/
}
