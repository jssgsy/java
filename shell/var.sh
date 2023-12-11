#!/bin/bash
# 赋默认值方法一：
# 【:-】：不论是null变量还是空串变量，在操作后，内侧的原始变量仍然还是没值，而外侧的结果变量均有了值
# 没有赋值的null变量
echo "原始null_var: $null_var"
var1=${null_var:-"default_value1"}
echo "使用【:-】赋默认值后，内侧变量null_var: $null_var"
echo "使用【-】赋默认值后，外侧变量var1: $var1"

# 值为空串的变量
empty_str_var=''
var2=${empty_str_var:-"default_value2"}
echo "使用【:-】赋默认值后，内侧变量empty_str_var: $empty_str_var"
echo "使用【-】赋默认值后，外侧变量var2: $var2"

echo ---------------------

# 赋默认值方法二
# 【:=】：不论是null变量还是空串变量，在操作后，内侧的原始变量和外侧的结果变量均有了值
# 没有赋值的null变量
echo "原始null_var2: $null_var2"
var3=${null_var2:="default_value3"}
echo "使用【:=】赋默认值后，内侧变量null_var:2 $null_var2"
echo "使用【-】赋默认值后，外侧变量var3: $var3"
# 值为空串的变量
empty_str_var=''
var4=${empty_str_var:="default_value4"}
echo "使用【:=】赋默认值后，内侧变量empty_str_var: $empty_str_var"
echo "使用【-】赋默认值后，外侧变量var4: $var4"

echo ---------------------

# 赋默认值方法三
# 含义有些奇怪，一般不要用
# 没有赋值的null变量
echo "原始null_var3: $null_var3"
var5=${null_var3-"default_value5"}
echo "使用【-】赋默认值后，内侧变量null_var3: $null_var3"
echo "使用【-】赋默认值后，外侧变量var5: $var5"
# 值为空串的变量
empty_str_var=''
var6=${empty_str_var-"default_value6"}
echo "使用【-】赋默认值后，内侧变量empty_str_var: $empty_str_var"
echo "使用【-】赋默认值后，外侧变量var6: $var6"