# ThreadLocal
* Thread类的threadLocals的类型是ThreadLocal.ThreadLocalMap，说明`ThreadLocalMap是ThreadLocal(而不是Thread)的内部类`;
    * 这是可理解的，因为ThreadLocalMap的key是ThreadLocal的this，而ThreadLocalMap又是在ThreadLocal创建的；
```
ThreadLocal.java
void createMap(Thread t, T firstValue) {
    t.threadLocals = new ThreadLocalMap(this, firstValue);
}
```

# InheritableThreadLocal
* 用在父线程向子线程传递数据。
* 是ThreadLocal的子类；
* Thread类中除了有字段ThreadLocal.ThreadLocalMap threadLocals外，还有字段ThreadLocal.ThreadLocalMap inheritableThreadLocals；

源码较简单。
`ThreadLocal类中的getMap与createMap都是勾子`，而InheritableThreadLocal是ThreadLocal的子类，重写了这两个方法，
其中使用了inheritableThreadLocals而不是threadLocals外变量，而至于将子从父线程值传递给子线程就是jdk做的事了。
