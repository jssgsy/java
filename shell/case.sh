#!/bin/zsh
# shell中的case分支不用breake；
case $a in
    1)  # 当$a的值为1时命中
        echo "\$a is $a"
        echo "两个分号不能少，表示这上面的所有命令都会被执行"
        ;;  # 固定写法，表示此分支结束
    2|3|4)
        echo "\$a 是（2，3，4）中的一个值"
        ;;
    *)  # 当$a的值没有命中上述任何一个分支时，相当于java中的default
        echo "这里是默认会被执行的语句"
esac
# case可以用正则表达式来匹配，如下面 忽略大小写
s=TaR
case $s in
    [Tt][Aa][Rr])
        echo "the cmd is tar"
        ;;
    [Jj][Aa][Rr])
        echo "the cmd is jar"
        ;;
    *)
        echo "unknow cmd"
        ;;
esac