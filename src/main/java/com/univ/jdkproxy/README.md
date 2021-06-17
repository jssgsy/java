# 个人理解
* proxy对象：由系统生成(调用Proxy.newInstance方法);
* target：此对象也是必要的，也是由应用提供，而不是由系统自动生成；
    * 也没法由系统自动生成，因为不同的target实例有不同的字段信息、不同的方法实现；
    * 代理最终的调用会到达target的调用，因此target由应用提供；
    
* 所谓代理：其实就是说在调用代理对象方法时(看起来像是调用target对象的方法)，由系统进行拦截，先不要直接去执行target的相应方法，而是先拦截下来，转而去执行另一段逻辑，且在此段逻辑中包含target的调用。这段拦截调用的逻辑就是InvocationHandler的invoke方法。
参考：![JDK动态代理](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/jdkproxy/proxy.png)
  
# 补充
* jdk提供的动态代理是接口维度的，且只要代理了，就代理了接口中的所有方法，不能只设置成代理某个方法(不过可以在InvocationHandler中对方法进行过滤);