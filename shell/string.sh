#!/usr/bin/env bash
# 字符串相关演示
# 变量定义，=前后不能有空格
str1=aaa
# 默认为字符串类型
str2=100+200
echo $str2  # 结果：100+200

# 变量读取:主要两种方式
echo $str1
echo ${str1}  # 一般使用这种方式，主要是帮助解释器识别变量的边界
# 获得命令的执行结果也有两种方式
# 一律推荐使用这种方式
echo $(uname -r)
# 不推荐此种方式，是遗留写法
echo `uname -r`

# 双引号与单引号的区别
  # 双引号：变量会得到解析；
  # 单引号：变量不会得到解析
echo "$str1 hello quote"
echo '$str1 hello single quote'

# 变量拼接
echo $str1:bbb
# 涉及到有空格时，则必须使用双引号了
echo "$str1 is god"

# 变量默认值
# 参考var.sh文件内容