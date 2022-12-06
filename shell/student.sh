#! /bin/bash
# 定义变量供其它文件引用
studentName="zhangsan"

# 定义函数供其它文件引用
function getAge() {
    echo "student.sh getAge()";
    return 20;
}