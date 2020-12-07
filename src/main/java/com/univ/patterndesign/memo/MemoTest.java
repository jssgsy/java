package com.univ.patterndesign.memo;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/7 10:33 上午
 * @description
 */
public class MemoTest {

    @Test
    public void test() {
        Editor editor = new Editor("content", "author");
        System.out.println("原始editor为: " + editor);

        // 客户端需要使用EditorHistory对象，由客户端来将备份放到History中，也由客户端从History中取出备份
        EditorHistory history = new EditorHistory();

        // 修改内容
        editor.setContent("content_v2");

        // 备份(ctrl + s)
        EditorState e1 = editor.save(); // Editor的保存操作只是返回一个当前状态对应的EditorState
        history.push(e1); // 由客户端将备份存档
        // 备份
        editor.setContent("content_v3");
        history.push(editor.save());

        // 撤回
        // 首先从History中取出最近的备份(实际需要取出最近第二次的备份)
        EditorState pop = history.pop();
        editor.undo(pop);
        System.out.println("撤回一次后editor为:" + editor);

        editor.undo(history.pop());
        System.out.println("撤回两次后editor为:" + editor);
    }
}
