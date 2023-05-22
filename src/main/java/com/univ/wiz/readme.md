删除导出后的为知笔记中多余的index_files目录，且将对应文件上移至正确目录。
示例：
之前：
* aaa
    * bbb.md
        * bbb.md
        * index_files # 其下没有图片资源，可直接删除
            * x.css
          
现在：
* aaa
    * bbb.md