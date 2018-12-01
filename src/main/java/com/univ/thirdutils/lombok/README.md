# lombok
演示lombok的基本用法
* 是intellij idea的一个插件；
* 同时也要引入相应的jar包；
> lombok既是一个IDE插件,也是一个项目要依赖的jar包.lombok是依赖jar包的原因是因为编译时要用它的注解.是插件的原因是他要在编译器编译时通过操作AST(抽象语法树)改变字节码生成.也就是说他可以改变java语法. 他不像spring的依赖注入或者hibernate的orm一样是运行时的特性,而是编译时的特性.
