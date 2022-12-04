#!/bin/zsh
# shell中也是有数组类型的，一般用到循环结构中
# 下标从1开始
# arr[0]=xxx  # 非法  assignment to invalid subscript range
arr[1]=aaa
arr[2]=bbb
arr[3]=ccc
echo ${arr[1]}
echo ${arr[2]}
echo ${arr[3]}
echo $arr # 也可以直接这样使用

# 也可以这样定义数组，注意，没有逗号
arr2=(111 ddd 333)
echo ${arr2[1]}
echo ${arr2[2]}
echo ${arr2[3]}
echo $arr2

echo ------
for file in $arr2
do
    echo $file
done