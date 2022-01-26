* Thread类的threadLocals的类型是ThreadLocal.ThreadLocalMap，说明`ThreadLocalMap是ThreadLocal(而不是Thread)的内部类`;
    * 这是可理解的，因为ThreadLocalMap的key是ThreadLocal的this，而ThreadLocalMap又是在ThreadLocal创建的；
```
ThreadLocal.java
void createMap(Thread t, T firstValue) {
    t.threadLocals = new ThreadLocalMap(this, firstValue);
}
```    
