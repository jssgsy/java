#!/usr/bin/env bash
# 字符串相关演示

# 1. 字符串拼接
# 1.1 字面量与字面量拼接
echo "1""2" # 12

# 1.2 变量与变量拼接
str1=aaa
str2=bbb
echo ${str1}${str2} # aaabbb

# 1.3 简单的变量与字面量拼接，
echo ${str1}ccc # aaaccc
# 1.3 变量与字面量拼接，注意，最好将字面量用引号，避免某些情况下拼接失败
str3="http://domain?q1="${str1}"&q2="${str2}    # 若这里的字面量不用引号，则解析会失败
echo $str3  # http://domain?q1=aaa&q2=bbb
