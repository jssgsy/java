# 定义
> 是一种结构型设计模式，给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用

是一种静态的代理。注，其实代理类所持有的对象类型也可以是父类，没必要一定是具体的子类，视实际情况使用。

# UML类图
与装饰器模式的类图基本一样，这里的Proxy就相当于装饰器模式中的Abstract Decorator一样，只是代理模式中的Proxy不像装饰器模式的Decorator可能还有子Decorator
![代理模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/proxy/proxy_uml.png)

# 与装饰器模式的区别
* 更多的是从语义上区分：装饰模式是为装饰的对象增强功能；而代理模式对代理的对象施加控制(记录日志，增加缓存)，但不对对象本身的功能进行增强；