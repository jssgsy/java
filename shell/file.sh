#!/bin/zsh

# $0,$1,$2为何物----------------------------------------------------------------
echo "\$0：$0"   # $0表示此文件名
echo "\$1：$1"   # $1表示第一个参数，即param1，类似的，$n表示第几个参数，即paramn
echo "\$2：$2"
echo "\$3：$3"
echo "\$4：$4"
echo "\$#：$#"   # 表示参数个数
echo "\$@：$@"   # 表示所有的参数，即param1 param2 param3
echo "\$*：$*"   # 与$@类似

# $@与$*的区别 参考：https://bash.cyberciti.biz/guide/How_to_use_positional_parameters
# $@ expanded as "$1" "$2" "$3" ... "$n"
# $* expanded as "$1y$2y$3y...$n", where y is the value of $IFS variable i.e. "$*" is one long string and $IFS act as an separator or token delimiters.
# 即$@相当于是以空格作为分隔符来区分开各个参数，而$*则是以变量$IFS的值作为分隔符来区分开参数
IFS=","
echo "差别\$@: $@"    # one two three
echo "差别\$*: $*"    # one,two,three

# 文件路径相关-----------------------------------------------------------
# 1. 当前目录（相对路径，即. ，常用来定位其它文件）,$(command)
# dirname用来输出某个文件的目录，而$0表示当前文件，因此dirname $0表示输出当前目录
echo $(dirname $0)
# 等价如下，调用命令用反引号` 括起来

# 2. 当前目录，绝对路径,$(command)
echo $(pwd)
# 等价如下，调用命令用反引号` 括起来
echo `pwd`;
#---------------------------------------------------------------------

#---------------------------------------------------------------------
# 文件包含
source student.sh;
# 调用其它文件中的函数
getAge;
echo $?
#---------------------------------------------------------------------