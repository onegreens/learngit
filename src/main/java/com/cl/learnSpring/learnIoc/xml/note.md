XML：可扩展标记语言

## XStream

用于将Java对象序列化为XML或者将XML序列化为Java对象，是java对象和XML之间的一个双向转换器

### 架构组成

1. Converters （转换器）
2. I/O（输入/输出）
3. Context（上下文引用）
4. Facade（统一入口）


### XStream注解

@XStreamAlias : 别名注解 ： 类，字段
@XStreamAsAttribute : 转换为属性 ： 字段
@XStreamOmitField ：忽略字段 ： 字段
@XStreamConverter : 注入转换器 ： 对象
@XStreamImplicit : 隐式集合 ： 集合字段

加载
//判断需要转换的类型
xstream.processAnnotations(**.class)
 //自动加载注解Bean
 xstream.autodetectAnnotation(true)

