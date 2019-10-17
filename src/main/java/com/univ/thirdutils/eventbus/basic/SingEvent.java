package com.univ.thirdutils.eventbus.basic;

import lombok.Data;

/**
 * EventBus中的事件就是一个简单的POJO，没有任何其它限制
 */
@Data
public class SingEvent {
    private String event_name = "sing_event";
}
