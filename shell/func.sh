#!/bin/zsh

# 函数基础---------------------------------------------------------------------
# 1. 函数是不用加参数列表的，即函数定义时不能形参，实参直接由调用时传入即可
function fn1() {
    echo "fn1"
}
fn1 # 调用此函数

# 函数返回值
# 可以显式增加 return 语句；如果不加，会将最后一条命令运行结果作为返回值。
# 函数返回值只能是整数！，一般用来表示函数执行成功与否，0表示成功，其他值表示失败，似乎如果返回大于255的数字，则会被转换
# shell中函数的返回值含义和其它高级编程中的返回值不同，一般不要显示返回，因为外界可能会用$?==0来判断函数是否执行成功
function fn2() {
    # 返回值范围只能在[0, 255]
    # 一般不要显示return
    return 255
}
fn2
echo "\$?: $?" # $?表示最近一次函数调用的返回值
# 如果函数想要给外界传递信息，可以在函数内部使用echo,print等，然后外界使用变量接收即可，如下：
fn21() {
  # 这是想要传递给外界的信息
  echo $(($1 + $2));
}
# 外界使用变量琰接收
sum=$(fn21 10 20)
echo "\$sum: $sum"
echo "\$?仍然还是表示含义是否执行成功: $?"

# 这不是函数调用，只是简单的echo输出
echo fn2

# 作用域
# 和c一样，函数内可以引用函数外的变量，
# 若要定义局部变量，使用 local 标识；
name="univ_name"
age=2324
function fn3(){
    name="new_univ_name"; # 注意，这里不要使用$name
    echo "-------";
    # 定义局部变量age
    local age=45;
    age=50;   # 此时改变的是函数内的age变量，而不会改变全局变量
}
fn3
echo ${name}; # new_univ_name
echo ${age}; # 2324,不会被fn3内改变

# 函数中也有内置变量$0,$1,$2等等
function fn4() { # function关键字可以省略
    echo "\$0表示此函数名：$0"   # $0仍表示此函数名
    echo "\$1表示函数的第一个参数：$1"   # $1表示函数的第一个参数，$n表示函数第几个参数
    echo "\$2表示函数的第二个参数：$2"
    echo "\$3表示函数的第三个参数：$3"
    echo "\$4表示函数的第四个参数：$4"
    echo "\$#表示函数参数个数：$#"   # 表示函数参数个数
    echo "\$@表示函数所有的参数：$@"   # 表示函数所有的参数，即param1 param2 param3
    echo "\$*与\$@类似：$*"   # 与$@类似
}
fn4 aaa bbb ccc ddd

# 函数定义也可以不用function关键字
fn5() {
  echo "this is output of fn5"
}
fn5