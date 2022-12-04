#!/bin/zsh

# if语句
# 1. "["与"]"的前后均需要有空格，否则报错
if [ -e $(dirname $0)/README.md ]; then
    echo "if-yes"
fi

# 2. 多个条件要用多个[]结合&& 和|| 使用
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