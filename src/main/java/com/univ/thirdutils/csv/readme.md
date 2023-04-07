使用Apache Commons CSV来处理csv文件；

# [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/)

# 关于utf8 BOM
* 背景： csv文件一般由excel转换而来，而excel在转换时保存的编码很可能是utf8 bom，即会在每行前加一个zwnbsp字符，这会导致csv解析时取不到第一个头的值；
  * 注：字符zwnbsp是不可见的，某些时候可能很迷惑；
* 解决方法：直接使用sublime等工具将csv文件重新以utf8的编码方式保存；

参考：
* [zwnbsp-appears-when-parsing-csv](https://stackoverflow.com/questions/72108383/zwnbsp-appears-when-parsing-csv)

# 提升处理准确度
csv文件本身数据的质量极大的决定了解析的精准度，往往`需要在解析之前对csv文件进行一定程度的预处理`；