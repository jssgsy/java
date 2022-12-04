# sh文件总共有两种执行方式
# 方式1：sh xxx.sh
  # * 此时会新起shell执行，即xxx.sh是在子shell中执行的；
  # * 带来的结果就是，xxx.sh中设置的变量在父shell中不生效；

# 方式2：source xxx.sh 或者 ./xxx.sh
   # 表示不新起子shell；
   # 带来的结果就是，xxx.sh中设置的变量在父shell中依然生效的；
   # 注意，是./xxx.sh，而不是xxx.sh；
     # 运行其它二进制的程序也一样，直接写xxx.sh，linux系统会去PATH里寻找有没有叫xxx.sh的，
     # 当前目录通常不在PATH里，所以写成xxx.sh是会找不到命令的，要用./xxx.sh告诉系统说，就在当前目录找。
     # 当前，此时要看xxx.sh是否有执行的权限问题；