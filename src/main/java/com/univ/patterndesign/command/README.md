# 定义
> 命令模式属于对象的行为模式。命令模式又称为行动(Action)模式或交易(Transaction)模式。
> 命令模式把一个请求或者操作封装到一个对象中（就是出引入一个命令对象）。
> 命令模式允许系统使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能（其实就是说Invoker可动态添加、删除命令对象）。
> 命令模式是对命令的封装。命令模式把`发出命令的责任`（Invoker）和`执行命令的责任`（Receiver）分割开，委派给不同的对象


# 自己的理解
以客户点餐为例，客户(Invoker)不直接告诉厨师(Receiver)说要什么菜，客户只是发出一个点菜命令
* 请求者封装成一个对象（Invoker，负责调用命令对象执行请求），接收者封装成一个对象（Receiver，负责具体实施和执行一个请求）；
* 命令也封装成一个对象（Command）

# UML类图
![命令模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/command/command_url.png)

由图可知
1. 具体的命令需要引用一个receiver（具体的命令最终由谁实现）；
2. 请求者(Invoker)需要引用Commands(请求者能发出哪些命令)；
