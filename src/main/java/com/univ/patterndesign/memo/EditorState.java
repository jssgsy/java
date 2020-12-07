package com.univ.patterndesign.memo;

/**
 * @author univ
 * @date 2020/12/7 10:20 上午
 * @description 即模式中的Memento，Editor的一个备份、快照。
 * 备忘录对象：用来存储另外一个对象内部状态的快照的对象
 *
 * 将对应Editor需要支持撤回操作的字段放这里(言外之意是Editor并不是所有数据都需要支持撤回)
 */
public class EditorState {

    /**
     * Editor的content字段需要支持撤回，但author字段不用，因此这里保留content字段即可。
     *
     * 这里不要为content提供setter方法，因为只是个快照，不能修改
     */
    private final String content;

    public String getContent() {
        return content;
    }

    public EditorState(String content) {
        this.content = content;
    }
}
