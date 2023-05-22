删除导出后的为知笔记中多余的index_files目录，且将对应文件上移至正确目录。
示例：
之前：
* aaa
    * bbb.md（目录）
        * bbb.md（文件）
        * index_files # 其下没有图片资源，可直接删除
            * x.css
          
现在：
* aaa
    * bbb.md（文件）

重点：日常工作中，在将图片资源上传之后可再次运行，能将文件移至正确目录。
示例：
* aaa
  * bbb.md（目录）
    * bbb.md（文件）
    * index_files # 日常手动上传这里的图片并删除此目录
      * a.png
      * b.png

变成
* aaa
    * bbb.md（目录）
        * bbb.md（文件）

运行此项目，变成：
* aaa
    * bbb.md（文件）