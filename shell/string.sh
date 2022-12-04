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
echo $(uname -r)
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
# 当变量v不存在，则为v1赋默认值notset_or_empty_str_default_value，否则为v的值(包含v为空字符串)
v1=${v-notset_default_value}
echo $v1  # 因为变量v还不存在，因此值为默认值：notset_default_value
# 定义值为空的变量
v=''
v1=${v-notset_or_empty_str_default_value}
echo $v1  # 此时变量v存在(虽然值为空串),所以$v1的值就为$v的值，即空串

# 变量v2不存在若值为空串，才赋默认值，这种应该更常用
echo ${v2:-not_set_or_empty_str_default_value}  # not_set_or_empty_str_default_value
v2=
echo ${v2:-not_set_or_empty_str_default_value}  # not_set_or_empty_str_default_value