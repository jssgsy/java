package com.univ.patterndesign.state.fsm;

/**
 * @author univ
 * 2021/11/12 5:39 下午
 */
public class MiddleToFinishEvent extends Event {

    // 每一种具体的【事件】都与某个具体的【事件类型】关联。因此可在构造函数中指定
    public MiddleToFinishEvent(Long businessId) {
        super(EventTypeEnum.MIDDLE_TO_FINISH, businessId);
    }
}
