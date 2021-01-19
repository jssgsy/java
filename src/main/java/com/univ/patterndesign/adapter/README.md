# 定义
这里讨论的`对象适配器模式`。

> 适配器模式是一种结构型模式，将一个类的接口转换成客户期望的另一个接口，让原本不兼容的接口可以合作无间。
> 适配器的别名为`包装器`

使用场景：系统需要使用现有的类，而这些类的接口不符合系统的需要

# 核心
* 适配器(Adapter)继承目标接口(Target)，直接将Adapter当作Target使用！！！
* 适配器(Adapter)引用现有接口(Adaptee)，因为要将目标接口的请求转发给现有接口
* 客户端并不直接访问适配者类，只直接使用目标类

# 示例
Executors中将Runnable转成Callable就借助了Adapter模式。
其中Target是Callable，Adaptee是Runnable(最终Callable的call方法将委托给Runnable中的run方法)；
```
// Executors.java
static final class RunnableAdapter<T> implements Callable<T> { // 1. 实现Target类Callable
    final Runnable task;    // 2. 持有Adaptee类的引用；
    final T result;
    RunnableAdapter(Runnable task, T result) {
        this.task = task;
        this.result = result;
    }
    public T call() {
        task.run(); // 3. 将Callable的call方法委托给Runnable的run方法
        return result;
    }
}

// 用法示例：有的是Runnable(Adaptee)，需要的是Callable(Target)
public static Callable<Object> callable(Runnable task) {
    if (task == null)
        throw new NullPointerException();
    return new RunnableAdapter<Object>(task, null);
}
```

# [网络资料](https://blog.csdn.net/zhangjg_blog/article/details/18735243)

# UML类图(来自网络)
![对象适配器模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/adapter/adapter_uml.jpg)

