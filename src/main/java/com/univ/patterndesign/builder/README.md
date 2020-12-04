# 定义
> The intent of the Builder design pattern is to separate the construction of a complex object from its representation. By doing so the same construction process can create different representations.
> 
>将一个复杂对象的构建与其表示分离，使得同样的构建过程可以创建不同的表示

* 对象是由Builder创建的，Director的职责是指导各部分构成整个最终对象的流程(这个流程是稳定的，如先后顺序、某个部分依赖另一个部分等等)

# 核心
* 要创建的对象是复杂的，由多个部分组成；
* 组成对象的各部分可有不同的实现，如轮胎可以是米其林，也可以是马牌等；
* 客户端只需明确想要什么类型的对象(找到的具体的builder);

# 与工厂模式的区别
相同点：
    * 都是创建型的模式；
    * 要创建的对象一般都是复杂的对象(工厂模式强调的是可能要较多的初始化工作，而建造者强调的是对象由多个部分组成)；
    
不同点：
    * 工厂模式是在工厂类中完成整个对象的创建；
    * 建造者模式中对象是`由多个部分组成`，且`每个部分单独构建`(且能有不同的实现)，最后组装成一个完整的对象，对象的组成可能还依赖于各部分的顺序等限制条件；

# UML类图
![建造者模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/builder/builer_uml.jpg)