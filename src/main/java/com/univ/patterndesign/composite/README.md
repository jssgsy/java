# 定义
>  组合模式组合多个对象形成树形结构以表示“整体-部分”的结构层次。

# 自己的理解
* 组合模式的前提是结构为树形的；要达到的目的是统一对待叶子结点与非叶子结点的访问；
* `非叶子结点就是子结点的一个容器`，方法调用时非叶子结点也只是简单的将请求转发给所有的子结点(最终到达非叶子节点)；

# 核心
* 组合模式本身的职责仅仅只是将相似对象以树形结构表示，以取得对叶子结点与非叶子结点的统一形式的访问，但实际场景中，`叶子结点的操作更多应该是读取、查找类的操作`，
因为如果是写操作的话，不可能去委托给所有子结点统一处理；见如下实际应用部分；
  
* 要明确非叶子结点与叶子结点的职责：
    * 非叶子结点：作用很纯粹，是一个框架概念，`仅仅只是一个容器的作用，其不应包含任何的业务逻辑`，业务逻辑都在最终的叶子结点中，非叶子结点仅仅只是委托给其子结点去完成而已；
    * 叶子结点：`真正实现业务逻辑的组件`，与业务较强相关；

# when to use
> Composite should be used when clients ignore the difference between compositions of objects and individual objects.
If programmers find that they are using multiple objects in the same way, 
and often have nearly identical code to handle each of them, then composite is a good choice; 
it is less complex in this situation to treat primitives and composites as homogeneous

> 需要表示一个对象整体或部分层次，在具有整体和部分的层次结构中，希望通过一种方式忽略整体与部分的差异，可以一致地对待它们


# UML
![组合模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/patterndesign/composite/composite.png)

总共有两种形式的UML类图，主要区分在于是将add、remove、clear等子结点相关方法放在顶层的Component中还是放在Composite中。
* 放在Component中：此时能保留对叶子与非叶子结点访问的一致性，但其实叶子结点是不需要这些方法的(类型不案例)；
* 放在Composite中：此时客户端需要区别对待Composite与Leaf结点，当然一般只需要与Composite打交道即可；

# 实际应用
参见spring mvc中的HandlerMethodArgumentResolver、HandlerMethodArgumentResolverComposite。
其中：
* HandlerMethodArgumentResolver：就是这里的Component；
* HandlerMethodArgumentResolverComposite就是这里的Composite;

```
public interface HandlerMethodArgumentResolver {
    // Component中的方法，也是具体的业务方法(最终由叶子结点来实现)
	boolean supportsParameter(MethodParameter parameter);
	Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;
}

public class HandlerMethodArgumentResolverComposite implements HandlerMethodArgumentResolver {
    // 保存所有的子结点
	private final List<HandlerMethodArgumentResolver> argumentResolvers = new LinkedList<>();
	
	// 委托给所有的子结点！！！
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return (getArgumentResolver(parameter) != null);
	}
	
	public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        // 思路与supportsParameter一样，也是委托给去遍历所有的子结点
	}

	@Nullable
	private HandlerMethodArgumentResolver getArgumentResolver(MethodParameter parameter) {
		
        for (HandlerMethodArgumentResolver methodArgumentResolver : this.argumentResolvers) {
            if (methodArgumentResolver.supportsParameter(parameter)) {
                result = methodArgumentResolver;
                break;
            }
        }
		return result;
	}
	
	public HandlerMethodArgumentResolverComposite addResolvers(@Nullable HandlerMethodArgumentResolver... resolvers) {
		。。。
	}
	public void clear() {
		this.argumentResolvers.clear();
	}
}
```
由此可知，这里其实是将子结点相关的方法放在了Composite当中。
