#!/bin/bash

# 参考：https://bash.cyberciti.biz/guide/Debug_a_script
#set -x : Display commands and their arguments as they are executed.此时每个执行到的命令都会显示出来，且前面会以+号标识
#set -v : Display shell input lines as they are read.
#set -n : Read commands but do not execute them. This may be used to check a shell script for syntax errors.

# 有多种方法可以debug shell文件，两种主要的方式：
#   1. 在执行文件时用-x控制开启debug开关与否；
#   2. 一种是在shell文件中加入各种debug开关；

echo "如果要调试shell文件，可以在运行时指定 -x，-n等 选项"


if test -f `dirname $0`/README.md;  then
    echo "`dirname $0`/README.md 文件存在";
else
    echo "`dirname $0`/README.md 文件不存在";
fi

#----------shell文件中开启/关闭debug模式---------------
# 开启debug模式
set -x

echo "debug模式开启了"

# 关闭debug模式
set +x
echo "debug模式关闭了"

#----------不运行只检查shell脚本的语法---------------
# sh -n how_to_debug.sh