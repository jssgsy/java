# 核心
* `迭代器一般定义在具体类型的集合中`，因为不同结构集合的迭代实现方法是不同的；
    * 如ArrayList的迭代器Itr定义在ArrayList中，且为private；
* `迭代器的成员变量一般与具体类型集合的结构有关`；如ArrayList的迭代器Itr有int型的cursor等；
* 迭代器迭代期间插入元素？？？
* 优点：遍历集合元素而无需暴露集合的内部细节；

# Q&A
## Q:为何需要Iterable与Iterator两个接口？
A:首先，Iterable的iterator是用来获取Iterator对象的。需要Iterable与Iterator两个接口的原因：`允许同一个集合能在多处迭代`。
核心：`iterator方法每次都是new一个新Iterator对象`(这样就保证了每次迭代时都是从头开始迭代)
> 因为Iterator接口的核心方法next()或者hasNext()都是依赖于迭代器的当前迭代位置的。
> 如果Collection直接实现Iterator接口，势必导致集合对象中包含当前迭代位置的数据，
> 当集合在不同方法间被传递时，由于当前迭代位置不可预置，那么next()方法的结果会变成不可预知的。
> 除非再为Iterator接口添加一个reset()方法，用来重置当前迭代位置。
> 但即使这样，Collection也同时只能存在一个当前迭代位置。而Iterable，每次调用都返回一个从头开始计数的迭代器，多个迭代器时互不干扰。

示例：
ArrayList的iterator:
```
public Iterator<E> iterator() {
    return new Itr();
}
```
HashMap的entrySet的iterator:
```
public final Iterator<Map.Entry<K,V>> iterator() {
    return new EntryIterator();
}
```
# 补充-关于foreach
```
for (String item : someList) {
    System.out.println(item);
}
```
相当于如下：
```
// 重点：每次迭代开始时都是新建了一个迭代器(调用了iterator()方法)
for (Iterator<String> i = someList.iterator(); i.hasNext();) {
    String item = i.next();
    System.out.println(item);
}
```