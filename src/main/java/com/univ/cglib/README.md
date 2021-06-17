# cglib
cglib：code generation library。即代码生成库，底层基于asm

# 与jdk动态代理对比
* jdk动态代理：基于接口代理；
* cglib动态代理：可直接基于类代理(当然也可代理接口)，且是通过继承实现的；
    * `生成的代理类是被代理类的子类`；
    * 所以被代理类的`final方法是不能被代理的`，当然final类更加不能使用cglib来代理；
    
* 因为jdk动态代理是基于接口，因此需要显示提供一个被代理的实例，而cglib是基于继承，所以当代理非接口(即直接代理某个类)时，不用提供被代理的实例；
    * 当代理接口时，用法就基本和jdk动态代理一模一样了；
* 两者的使用方法基本一致：
    * 设置回调：jdk动态代理是InvocationHandler,cglib是MethodInterceptor；
    * 设置要代理哪个类；
    * 创建代理对象：jdk动态代理是Proxy.newInstance，cglib是enhance.create
    * 调用代理对象的相应方法




