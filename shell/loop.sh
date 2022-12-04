#!/bin/zsh
# for循环 参考：https://bash.cyberciti.biz/guide/For_loop
# 可以对如下六种数据进行循环处理
    # 字符串
    # 数字
    # 命令参数
    # 文件名
    # 命令产生的结果(如ls命令)

# 循环处理数字
for i in 1 2 3 4
do
    echo "\$i: $i"
done

# 循环处理字符串
for s in aaa bbb ccc
do
    echo "\$s: $s"
done

# 循环处理命令
for cmd in date pwd ls
do
    echo "命令${cmd}的输出结果如下："
    # 执行命令,注意，下面才是执行命令
    $cmd
done

# 循环处理文件
for f in /etc/passwd /etc/group /etc/shadow /etc/gshdow
do
	[  -f $f ] && echo "$f file found" || echo "*** Error - $f file missing."
done

# c风格的for循环 for (( EXP1; EXP2; EXP3 ))
for (( i = 0; i < 5; i++))
do
    echo "i: $i"
done

# for嵌套循环
for (( i = 1; i <= 5; i++ ))
do
    for (( j = 1; j <= 5; j++))
    do
        # 注意算术运算的语法
        echo "$i * $j = $(( $i * $j))"
    done
done

# while循环 while [ condition ] do ... done，注意：condition为真才会执行循环语句
w=1
while [ $w -le 5 ]
do
    echo "\$w: $w"
    w=$(( $w + 1))
done

# until循环 until [ condition ] do ... done，注意：condition为假才会执行循环语句，即如果为真则终止循环
u=1
until [ $u -gt 5 ]
do
    echo "\$u: $u"
    u=$(( $u + 1))
done

# 补充：shell中也有break、break N、continue等关键词，含义与编程语言中一样；还有一个select循环
#---------------------------------------------------------------------