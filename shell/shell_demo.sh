#!/bin/bash
# 说明
# 1. sh文件第一行必须为 “#!/bin/bash”;
# 2. “#!”是一个约定的标记，它告诉系统这个脚本需要什么解释器来执行
# 3. sh文件的执行是从上到下，从左到右，所以使用之前(如函数调用)必须先定义


# 这是注释，最基本的输出---------------------------------------------------
echo "hello, linux bash"
#---------------------------------------------------------------------


# $0,$1,$2为何物----------------------------------------------------------------
# 执行命令时可以直接在后面加任意个参数，如sh shell_demo.sh param1 param2 param3
# 单双引号的区别跟PHP类似
echo "\$0：$0"   # $0表示此文件名
echo "\$1：$1"   # $1表示第一个参数，即param1，类似的，$n表示第几个参数，即paramn
echo "\$2：$2"
echo "\$3：$3"
echo "\$4：$4"
echo "\$#：$#"   # 表示参数个数
echo "\$@：$@"   # 表示所有的参数，即param1 param2 param3
echo "\$*：$*"   # 与$@类似

# $@与$*的区别 参考：https://bash.cyberciti.biz/guide/How_to_use_positional_parameters
# $@ expanded as "$1" "$2" "$3" ... "$n"
# $* expanded as "$1y$2y$3y...$n", where y is the value of $IFS variable i.e. "$*" is one long string and $IFS act as an separator or token delimiters.
# 即$@相当于是以空格作为分隔符来区分开各个参数，而$*则是以变量$IFS的值作为分隔符来区分开参数
IFS=","
echo "差别\$@: $@"    # one two three
echo "差别\$*: $*"    # one,two,three
#---------------------------------------------------------------------



# 函数基础---------------------------------------------------------------------
# 1. 函数是不用加参数列表的；下面演示$0，$1，$2等在函数内部使用的含义
function fn1() { # function关键字可以省略
    echo "\$0：$0"   # $0仍表示此文件名
    echo "\$1：$1"   # $1表示函数的第一个参数，$n表示函数第几个参数
    echo "\$2：$2"
    echo "\$3：$3"
    echo "\$4：$4"
    echo "\$#：$#"   # 表示函数参数个数
    echo "\$@：$@"   # 表示函数所有的参数，即param1 param2 param3
    echo "\$*：$*"   # 与$@类似
}
# 下面是函数调用的方式，
`fn1 funtion_param1 funtion_param2 funtion_param3`



# 2.函数返回值
# 可以显式增加 return 语句；如果不加，会将最后一条命令运行结果作为返回值。
# 函数返回值只能是整数！，一般用来表示函数执行成功与否，0表示成功，其他值表示失败，似乎如果返回大于255的数字，则会被转换
function fn2() {
    return 254
}
fn2
echo $? # $?表示最近一次函数调用的返回值
# 下面是错误的，这不是函数调用，更不能通过这样的方式获得fn2函数的返回值，会直接输出字符串fn2
echo fn2



# 3作用域
# 和c一样，函数内可以引用函数外的变量，可以在函数内使用local定义变量
name="univ_name"    # 注意，变量名和等号之间不能有空格
age=2324
function fn3(){
    name="new_univ_name"; # 注意，这里不要使用$name
    echo "-------";
    local age=45;
    age=50;   # 此时改变的是函数内的age变量，而不会改变全局变量
}
fn3
echo ${name}; # new_univ_name
echo ${age}; # 28,不会被fn3内改变

# 补充：一般使用${variable_name}，而不是$variable_name,这主要是帮助解释器识别变量的边界
#---------------------------------------------------------------------




# 文件路径相关-----------------------------------------------------------
# 1. 当前目录（相对路径，即. ，常用来定位其它文件）,$(command)
echo $(dirname $0)
# 等价如下，调用命令用反引号` 括起来
echo `dirname $0`;

# 2. 当前目录，绝对路径,$(command)
echo $(pwd)
# 等价如下，调用命令用反引号` 括起来
echo `pwd`;
#---------------------------------------------------------------------





# if语句
# 1. [与]的前后均需要有空格，否则报错
if [ -e $(dirname $0)/README.md ]; then
    echo "if-yes"
fi

# 2. 多个条件要用多个[]结合&& 和|| 使用
a=4
if [ ${a} -gt 3 ] && [ ${a} -lt 5 ];then
    echo "多个条件需要多个[]结合&&与||使用"
fi


# 3. if-else
if [ -e $(dirname $0)/READMsE.md ]; then
    echo "if-else-yes"
else
    echo "if-else-no"
fi

# 4. if-elseif-else
a=4
if [ ${a} -gt 1 ] && [ ${a} -lt 2 ];then
    echo "$a >= 1 && $a <= 2"
elif [ ${a} -gt 3 ] && [ ${a} -lt 5 ];then
    echo "$a >= 3 && $a <= 4"
elif [ ${a} -gt 6 ] && [ ${a} -lt 8 ];then
    echo "$a >= 6 && $a <= 8"
else
    echo "unknow $a"
fi

# 5. case
case $a in
    1)
        echo "\$a is $a"
        echo "两个分号不能少，表示这上面的所有命令都会被执行"
        ;;
    2|3|4)
        echo "\$a 是（2，3，4）中的一个值"
        ;;
    *)
        echo "这里是默认会被执行的语句"
        echo "这里是默认会被执行的语句"
esac


#---------------------------------------------------------------------



# 补充
# 参考资料：https://bash.cyberciti.biz/guide/Main_Page
# 1. 注意，一定要写成./test.sh，而不是test.sh，运行其它二进制的程序也一样，直接写test.sh，linux系统会去PATH里寻找有没有叫test.sh的，
# 而只有/bin, /sbin, /usr/bin，/usr/sbin等在PATH里，你的当前目录通常不在PATH里，
# 所以写成test.sh是会找不到命令的，要用./test.sh告诉系统说，就在当前目录找。

#---------------------------------------------------------------------
# 文件包含
source student.sh;
# 调用其它文件中的函数
getAge;
echo $?
#---------------------------------------------------------------------


#---------------------------------------------------------------------
# 将命令赋值给变量以复用
path=`dirname $0`
echo ${path}; # .
cd "$path/..";
path=`pwd`
echo ${path}; # /Users/univ/gitRepos/java

#---------------------------------------------------------------------
# 数字计算，语法：$((expression))
a=10;
b=20;
sum=$((a + b ))
echo "${a} + ${b} = ${sum}" # 10 + 20 = 30





