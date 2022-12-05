#!/bin/zsh
#set -x
# 语法都好懂。注意几个常用的判断方法
# 文件相关
# -e：文件是否存在；
# -f：文件是否存在且为文件类型；
# -d：文件是否存在且为文件夹类型；
# 权限相关，是否有rwx权限：-r、-w、-x

# 字符串相关
# -z 是否为空字符串或者不存在
# -n 字符串不为空且长度不为0

# test
str=
test -z $str && echo "\$str不存在或为空串 : true"
# 经验证，用test判断相等时，不能使用==，只能使用=
test "$str" = "aaa" && echo "$str存在但为空串 : true" || echo strange

# []
# * [右边与]左边必须有空格；
## 使用范示 如果-则-否则
[ -e "$(PWD)/basic.sh" ] && echo "${PWD}/basic.sh exists" || echo "${PWD}/basic.sh do not exists"

i=3
# 使用单个=时，用[]
[ 3 = $i ] && echo "3 == $i"
## 使用两个=时，用[[]]，这可能也是为何test只能用单个=的原因
[[ 3 == "$i" ]] && echo "3 == $i"
[ 5 != "$i" ] && echo "5 != $i"
# 大小小于号不能用><，需要用-gt、-lt
#[ 5 > $i ] echo "5 > $i"
[ 5 -gt $i ] echo "5 -gt $i"
# 单个[]表达多个条件，此时 且为-a，或为-o
# 实际使用中，一个[]只表达一个条件，多条件用[]组合

# 一般不这样用
[ 5 -gt $i -a 1 -lt $i ] && echo "一般不在一个[]中表达多个条件"
# 标准用法, []与[]之间用 && || 连接
[ 5 -gt $i ] && [ 1 -lt $i ] && echo "多条件用多个[]连接"

# if语句
# 表达式结尾需要有分号";"
if [ -e $(dirname "$0")/README.md ]; then
    echo "if-yes"
fi

# 多个条件要用多个[]结合&& 和|| 使用
a=4
if [ ${a} -gt 3 ] && [ ${a} -lt 5 ];then
    echo "多个条件需要多个[]结合&&与||使用"
fi

# 3. if-else
if [ -e $(dirname $0)/xxx.md ]; then
    echo "if-else-yes"
else
    echo "if-else-no"
fi

# 4. if-elseif-else
a=4
if [ ${a} -gt 1 ] && [ ${a} -lt 2 ];then
    echo "$a >= 1 && $a <= 2"
elif [ ${a} -gt 3 ] && [ ${a} -lt 5 ];then
    echo "$a >= 3 && $a <= 4"
elif [ ${a} -gt 6 ] && [ ${a} -lt 8 ];then
    echo "$a >= 6 && $a <= 8"
else
    echo "unknow $a"
fi