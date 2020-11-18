# 定义
是一种结构型设计模式。
> 动态地给一个对象添加一些额外的职责。就增加功能来说，相比生成子类更为灵活。

# 好处
> 把核心功能和附加功能给解耦了
> 如果要新增核心功能，就增加Component的子类。如果要增加附加功能，就增加Decorator的子类。
>两部分都可以独立地扩展，而具体如何附加功能，由调用方自由组合，从而极大地增强了灵活性。

# 核心
* 一个独立的核心功能由一个component充当；
* 一个独立的附加功能由一个decorator充当；
* component与decorator拥有共同的父类，这使得链式装饰成为可能；
* 装饰器模式中一样有一个抽象的装饰器，因为附加功能可能会有多个(即有多个具体的装饰器)；

# 示例
FileInputStream是一个component，FilterInputStream是一个abstract decorator，BufferedInputStream是一个具体的decorator。
```
// 核心功能
InputStream is = new FileInputStream();

// 用附加功能进行装饰，具体如何装饰，由调用方决定，相当于给的是积木，但如何搭建房子就由人来发挥了
InputStream bis = new BufferedInputStream(is);

// 可接着装饰
InputStream bis = new GZIPInputStream(bis);
```

# UML类图
![装饰器模式](https://github.com/jssgsy/java/raw/master/src/java/com/univ/patterndesign/decorator/decorator_uml.png 

