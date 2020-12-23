# 定义
这里讨论的对象适配器模式。

> 适配器模式是一种结构型模式，将一个类的接口转换成客户期望的另一个接口，让原本不兼容的接口可以合作无间。
> 适配器的别名为`包装器`

使用场景：系统需要使用现有的类，而这些类的接口不符合系统的需要

# 核心
* 适配器(Adapter)继承旧接口(Target)；
* 适配器(Adapter)引用新接口(Adaptee)，因为要将旧接口的请求转发给新接口
* 客户端并不直接访问适配者类，只直接使用目标类(Target、旧接口)
* 还是没有太理解；


# [网络资料](https://blog.csdn.net/zhangjg_blog/article/details/18735243)

# UML类图(来自网络)
![对象适配器模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/adapter/adapter_uml.jpg)

