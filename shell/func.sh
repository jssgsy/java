#!/bin/zsh

# 函数基础---------------------------------------------------------------------
# 1. 函数是不用加参数列表的，即函数定义时不能形参，实参直接由调用时传入即可
function fn1() {
    echo "fn1"
}
fn1 # 调用此函数
# 2.函数返回值
# 可以显式增加 return 语句；如果不加，会将最后一条命令运行结果作为返回值。
# 函数返回值只能是整数！，一般用来表示函数执行成功与否，0表示成功，其他值表示失败，似乎如果返回大于255的数字，则会被转换
function fn2() {
    return 254
}
fn2
echo $? # $?表示最近一次函数调用的返回值

# 这不是函数调用，只是简单的echo输出
echo fn2

# 3作用域
# 和c一样，函数内可以引用函数外的变量，
# 若要定义局部变量，使用 local 标识；
name="univ_name"    # 注意，变量名和等号之间不能有空格
age=2324
function fn3(){
    name="new_univ_name"; # 注意，这里不要使用$name
    echo "-------";
    # 指定作用域为局部
    local age=45;
    age=50;   # 此时改变的是函数内的age变量，而不会改变全局变量
}
fn3
echo ${name}; # new_univ_name
echo ${age}; # 28,不会被fn3内改变

# 函数中也有内置变量$0,$1,$2等等
function fn4() { # function关键字可以省略
    echo "\$0：$0"   # $0仍表示此文件名
    echo "\$1：$1"   # $1表示函数的第一个参数，$n表示函数第几个参数
    echo "\$2：$2"
    echo "\$3：$3"
    echo "\$4：$4"
    echo "\$#：$#"   # 表示函数参数个数
    echo "\$@：$@"   # 表示函数所有的参数，即param1 param2 param3
    echo "\$*：$*"   # 与$@类似
}
fn4 aaa bbb ccc ddd

# 函数定义也可以不用function关键字
fn5() {
  echo "this is output of fn5"
}
fn5