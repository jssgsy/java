# [java的spi机制](http://cxis.me/2017/04/17/Java%E4%B8%ADSPI%E6%9C%BA%E5%88%B6%E6%B7%B1%E5%85%A5%E5%8F%8A%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90/)
java的spi机制主要通过`ServiceLoader`类来实现，查看其源码可了解所有细节。

# 步骤
1. 在资源路径下的`META-INF/services/`下新建一个文件，文件名为提供服务的接口(HelloService)的全路径名，文件内容为此服务接口的具体的实现类，一行一个；
2. 使用ServiceLoader.load方法来加载并实例化具体的实现类；
3. 使用