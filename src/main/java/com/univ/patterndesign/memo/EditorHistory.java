package com.univ.patterndesign.memo;

import java.util.Stack;

/**
 * @author univ
 * @date 2020/12/7 10:22 上午
 * @description 即模式中的CareTaker
 *
 * Editor对应的所有历史，内部用栈表示撤回操作的先进后出特性
 *
 * 客户端需要感知此类的存在，即客户端使用此类来实现保存与撤回的操作；
 */
public class EditorHistory {

    /**
     * 注，这里存的是EditorState而不是Editor，因为Editor可能并不是所有字段都需要撤回
     */
    private Stack<EditorState> stack = new Stack<>();

    /**
     * 保存(快照)，其实就是将当前的Editor对应的状态EditorState保存到历史中
     * @param editorState
     */
    public void push(EditorState editorState) {
        stack.push(editorState);
    }

    /**
     * 撤回，其实就是从历史记录中返回最新的一个版本
     * @return
     */
    public EditorState pop() {
        return stack.pop();
    }

}
