# 工厂模式
这里讨论的创建的对象，一般指`对象的创建过程比较复杂`，如果只是一个简单的对象，直接new就好了。
其实背后的思想就是`封装`，因为当对象的创建很复杂而如果又在需要使用到的地方创建的时候，会导致大量重复代码的存在，当需要修改创建过程时会很麻烦，
因此引出对象工厂这个概念，用来封装复杂对象的创建过程

# 简单工厂模式
简单工厂模式不算是一种严格意义上的模式。
> 属于类创建型模式。在简单工厂模式中，可以根据参数的不同返回不同类的实例。简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。

个人的理解：将根据入参来选择要创建的对象的逻辑封装到了一个工厂中(超级工厂，一个工厂创建所有的对象)。

# 工厂方法模式
> 定义一个创建对象的接口，但让实现这个接口的类来决定实例化哪个类。工厂方法让类的实例化推迟到子类中进行。
> 或者：将类的实例化（具体产品的创建）延迟到工厂类的子类（具体工厂）中完成，即由子类来决定应该实例化（创建）哪一个类。

简单工厂模式是只有一个对象工厂，`由对象工厂生产所有的对象`，且`将选择对象的逻辑进行了封装`。而工厂方法模式则是`一个工厂生产一种对象`，
且`让客户端来选择使用哪个工厂`(即让客户端决定要创建哪个对象)

Q：既然现在是由客户端来选择创建哪个对象了，那直接new一下不就好了吗？为何还要引入一个工厂类？

A：目的是解耦。客户端直接和工厂类打交道，找工厂类要对象，而不用关心对象的创建过程，当对象的创建过程需要发生变化时，修改工厂类一处即可，外部千万个调用均不需要变化。
即，`解耦了对象的创建过程和使用过程`(**不需要在使用对象的地方也知道对象的创建过程**，当然，这里说的创建对象比较复杂的对象，比如依赖一个初始化操作)

## UML类图
![工厂方法模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/factory/factorymethod/factory_method.png)

# 抽象工厂方法模式
较之工厂模式更进一步，即一个工厂创建各种对象(产品簇)，只是`这些对象逻辑上都属于同一产品簇`。实际应用中好像不怎么能碰到。
如台式电脑下有联想，也有惠普，则可以有一个台式电脑工厂类；笔记本电脑下有联想也有惠普，则可以有一个笔记本电脑工厂类。


