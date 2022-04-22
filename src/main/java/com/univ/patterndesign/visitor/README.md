# 定义
> 表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
> The purpose of a Visitor pattern is to define a new operation without introducing the modifications to an existing object structure.
Imagine that we have a composite object which consists of components. 
> `The object's structure is fixed` – we either can't change it, 
> or we don't plan to add new types of elements to the structure.


# 核心
* 数据结构是稳定的，或者说`被访问者是有限的、固定的`。
    * 因为每个visitor都需要事先定义好可能要访问哪些被访问者， 因为如果数据结构不稳定，则当后期需要新加被访问者时，
此时需要在visitor父类中重新加入对此被访问者方法，同时所有的已存在的visitor也都需要同步修改。这非常大程度上违反了open-close原则。
这也是为什么所有资料上都说`访问者模式很少用到`的原因。因为这个大前提一般不能被满足；
* 了解一个词：`双重分派(发)`；见ConcreteElement1.java
* 带来的好处：`对新增visitor开放`，只需要新增具体的visitor类即可；
    * 注，由使用前提限定，不会新增被访问者(否则就不适合使用此模式)；
* 关于角色ObjectStructure：其实就是一个粘合剂，用来保存所有的被访问者，以便实现【当访问时一定是访问所有的被访问者】 


# 自己的理解
* 共两个想到独立的维度：被访问者(Element)维度与访问者(visitor)维度；
* 使用范式：`当访问者来访时，一定是同时访问所有的被访者的`(ObjectStructure存在的意义)；


# UML类图
![组合模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/visitor/visitor.png)

# 参考资料
[]()