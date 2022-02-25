# 几个基本事实
* 表达式最外层一定是一个group，即使只有一个条件；

# 核心
## 统一值的格式为List<String>
* 优点：
    * 这也是为了方便接收前端值；
    * 编码简单；
    
* 缺点；
    * 将表达式整个返回给前端时(如回显用)，前端可能需要再转成需要的类型；
    
## 表达式的校验；
最终是对末级表达式的校验。几个示例如下：
1. int i = ["1"];    ok
2. int i = ["a"];  not ok 值的类型必须为int、integer
3. int i = ["1", "2"]'; not ok 值的个数必须只能为1
4. int i like ["1"];   not ok 数据类型int不支持like操作
5. int i in ["1", "2"];  ok
核心：从【数据类型】与【操作符】两方面组合(应用了拆解的思维)来对【表达式】进行校验：
   
![建造者模式](https://github.com/jssgsy/java/raw/master/src/main/java/com/univ/common_business/condition_expression/valid_expression.png)

### 数据类型维度
* 数据类型对`值的类型`有要求；如2，int型不能作用于字符串值；
* 数据类型有操作符有要求；
    * 这条是数据类型与操作符之间的校验，也可以放到操作符维度，这里就放到操作符那里了；

### 操作符维度
* 表达式中的操作符
* `操作符必须被字段的数据类型支持`；如4，like是不能作用于int型的；
    * 这条是操作符与数据类型之间的校验，也可以放到数据类型维度，这里就放到操作符这里了；
* 操作符对`值的个数`有要求；如3，=只能作用于一个值上面；


# 逐步演变
## 1. 深刻掌握条件表达式的样式
* ConditionExpression
* ConcreteCondition
* ConditionGroup
* ConditionExpressionHelper

## 条件表达式合法性的校验、生成值
### 需要先提炼数据类型、操作符等
* DataTypeEnum、AbstractDataTypeProcessor
* OperatorEnum、AbstractOperatorProcessor

### 待办-提炼至不同的平台(sql es等)