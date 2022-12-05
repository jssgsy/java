#!/bin/zsh
set -x

# $0,$1,$2为何物----------------------------------------------------------------
echo "\$0：$0"   # $0表示此文件名，注意：是绝对路径(包含文件名)
echo "\$1：$1"   # $1表示第一个参数，即param1，类似的，$n表示第几个参数，即paramn
echo "\$2：$2"
echo "\$3：$3"
echo "\$4：$4"
echo "\$#：$#"   # 表示参数个数
echo "\$@：$@"   # 表示所有的参数，即param1 param2 param3，以空格区分
echo "\$*：$*"   # 与$@类似，但可以通过IFS来指定分隔符

# $@与$*的区别 参考：https://bash.cyberciti.biz/guide/How_to_use_positional_parameters
# $@ expanded as "$1" "$2" "$3" ... "$n"
# $* expanded as "$1y$2y$3y...$n", where y is the value of $IFS variable i.e. "$*" is one long string and $IFS act as an separator or token delimiters.
# 即$@相当于是以空格作为分隔符来区分开各个参数，而$*则是以变量$IFS的值作为分隔符来区分开参数
IFS=","
echo "差别\$@: $@"    # one two three
echo "差别\$*: $*"    # one,two,three

# 文件路径相关-----------------------------------------------------------
# 当前目录，主要是dirname命令的使用
# 因为dirname有可能返回.，因此此时不一定返回全路径
echo "当前目录为：$(dirname $0)"

# 如果一定要获取当前目录的全路径名，采用下面的方法
echo "当前目录(全路径)为：$(cd dirname $0;pwd)"

#---------------------------------------------------------------------

#---------------------------------------------------------------------
# 文件包含
source student.sh;
# 调用其它文件中的函数
getAge;
echo $?
#---------------------------------------------------------------------