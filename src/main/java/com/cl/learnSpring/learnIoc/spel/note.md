#Spring SpEL
spring动态表达式语言：支持运行时查询和操作对象图的动态语言

添加依赖：spring-expression

EvaluationContext接口
。。。

## SpEL编译器

可以将表达式直接编译成字节码，从而避免每次调用时进行语法解析所产生时间消耗；如果在后续运行时表达式发生裱花，则必须重新编译

## SpEL基础表达式

实例：

文本字符解析 ：LiteralExprSample.class

总结：SpEL就是通过将具有含义的字符串通过ExpressionParser运行
若涉及到对象，EvaluationContext将其包含