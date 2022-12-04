#!/bin/zsh

# shell中的数值类型只能到整数，因此1/3的值为0
# 指定为整形必须要用declare -i来声明
declare -i sum
# 注意，前后不能有空格
sum=100+200
echo $sum

# 直接这样写也可以
declare -i s2=200+300;
echo $s2;

# 没有显示指明类型情况下，参与数字计算，语法：$((expression))
a=10; # 此时是字符串
b=20;
sum=$((a + b ))
echo "${a} + ${b} = ${sum}" # 10 + 20 = 30