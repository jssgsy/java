
# 常见类
* Executor：用来执行异步任务的对象。可理解成替换创建Thread的用法。
    * 之前是用：new Thread(new(RunnableTask())).start()；
    * 现在可用：executor.execute(new RunnableTask())
* ExecutorService：也是一个Executor，只是功能更完善，比如可中止任务
* Executors：工具类，用来生成 Executor, ExecutorService, ScheduledExecutorService, ThreadFactory
    * 可将Runnable转成Callable
    
    
# FutureTask
FutureTask和Runnable一样代表一个异步任务，区别在于FutureTask同时实现了Future，因此此异步任务可获取到执行结果、取消执行等。

![FutureTask继承图](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/multithread/future/future_task_uml.png)
