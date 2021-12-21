# 定义
是一种结构型设计模式。
> 动态地给一个对象添加一些额外的职责。就增加功能来说，相比生成子类更为灵活。

# 好处
> 把核心功能和附加功能给解耦了
> 如果要新增核心功能，就增加Component的子类。如果要增加附加功能，就增加Decorator的子类。
> 两部分都可以独立地扩展，而具体如何附加功能，由调用方自由组合，从而极大地增强了灵活性。

# 核心
* 一个独立的核心功能由一个component充当；
* 一个独立的附加功能由一个decorator充当；
* decorator也是Component的一种；
    * `装饰器与被装饰的对象类型相同`，这样就可以用来链式装饰；
* 装饰器模式中有一个抽象的装饰器(用来抽出多个装饰器的共同部分，如都需要持有被装饰对象的引用，不如抽离出来)，可视具体情况退化；

# 示例
## FilterInputStream
InputStream是component，FileInputStream是具体的component，FilterInputStream是一个abstract decorator，BufferedInputStream是一个具体的decorator。
```
// 核心功能
InputStream is = new FileInputStream();

// 用附加功能进行装饰，具体如何装饰，由调用方决定，相当于给的是积木，但如何搭建房子就由人来发挥了
InputStream bis = new BufferedInputStream(is);

// 可接着装饰
InputStream bis = new GZIPInputStream(bis);
```

## ThreadPoolTaskExecutor
* wiz://open_document?guid=a6dc408d-e7bf-4917-91af-c97376d016af&kbguid=&private_kbguid=a82b7684-a5b7-4e10-9976-4c1ab655aa0f

# UML类图
![装饰器模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/decorator/decorator_uml.png)