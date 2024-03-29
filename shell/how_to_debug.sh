#!/bin/bash

# 参考：https://bash.cyberciti.biz/guide/Debug_a_script
# 脚本中常用的调试手段如下，其实就是set命令与shopt命令的应用：
  # set -x : Display commands and their arguments as they are executed.此时每个执行到的命令都会显示出来，且前面会以+号标识
  # set -v : Display shell input lines as they are read.
  # set -n : Read commands but do not execute them. This may be used to check a shell script for syntax errors.
  # set -e：如果任何语句的执行结果不是true则应该退出
  # set -o pipefail：同set -e，-o pipefail 的作用域是管道, 也就是说在Linux脚本中的管道, 如果前面的命令执行出了问题, 应该立即退出

# 有多种方法可以达到调试shell文件，两种主要的方式：
#   1. 在执行文件时用set -x控制开启shell逐行执行的过程及输出；
#   2. 一种是在shell文件中加入各种debug开关；

echo "如果要调试shell文件，可以在运行时指定 -x，-n等 选项"

if test -f $(dirname "$0")/README.md;  then
    echo "$(dirname "$0")/README.md 文件存在";
else
    echo "$(dirname "$0")/README.md 文件不存在";
fi

#----------shell文件中开启/关闭debug模式---------------
# 开启debug模式
set -x

echo "debug模式开启了"

# 关闭debug模式
set +x
echo "debug模式关闭了"

#----------使用sh xxx.sh时的调试方法，主要是几个参数---------------
# -n：不真正执行shell文件，只是检查语法
# -x：与上面的set -x一样，用来将执行的脚本语句输出；
  # 这里相当于全局开启了，而上述的只在特定位置处开启，择机使用；
# -v：执行之前先将shell脚本全部输出，包含注释，不太有用；