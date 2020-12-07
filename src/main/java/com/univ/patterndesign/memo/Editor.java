package com.univ.patterndesign.memo;

import lombok.Data;

/**
 * @author univ
 * @date 2020/12/7 10:16 上午
 * @description 即模式中的Originator
 * 编辑器，可以存档也可以撤回，支持多次撤回
 */
@Data
public class Editor {

    /**
     * 此字段需要支持撤回
     */
    private String content;

    /**
     * 此字段不用支持撤回
     */
    private String author;

    public Editor(String content, String author) {
        this.content = content;
        this.author = author;
    }

    /**
     * 保存，即存档，其实创建就是当前内容的一个备份。
     * 内部源数据(Editor类)才能决定哪些内容(字段)是可以存档的，外部没法操作
     * 注：Editor依赖了EditorState对象
     */
    public EditorState save() {
        // 这里当然可以将new EditorState(this.content)移到一个称之为createEditorState的方法中，要活用
        return new EditorState(this.content);
    }

    /**
     * 撤回
     * 注：撤回时，是从外界传入一个要恢复的备份
     */
    public void undo(EditorState editorState) {
        this.setContent(editorState.getContent());
    }
}
