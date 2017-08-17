##IoC容器：
- 控制：
- 反转：
如：某一接口具体实现类的选择控制权从调用类中移除，转交给第三方决定，即由spring容器借由Bean配置来进行控制

###注入方式：
1. 构造函数注入
2. 属性注入
3. 接口注入

### 反射机制
#### 类装载器 ClassLoader
类装载器就是寻找类的解码文件并构造出类在JVM内部表示对象的组件。

装载步骤：
1. 装载：查找和导入Class文件
2. 链接：执行校验、准备和解析步骤
     - 校验：检查载入Class文件数据的正确性
     - 准备：给类的静态变量分配存储空间
     - 解析：将符号引用转换成直接引用
3. 初始化：对类的静态变量、静态代码块执行初始化工作

JVM装载类时使用“全盘负责委托机制”

重要方法：
- Class loadClass(String name) 

    name参数指定类装载器需要装载类的名字，必须使用全限定类名

- Class defineClass(String name,byte[] b,int off,int len)

    将类文件的字节数组转换为JVM内部的java.lang.Class对象。

- Class findSystemClass(String name)

    从本地文件系统中载入Class文件

- Class findLoadedClass(String name)

    调用该方法来查看ClassLoader是否已装入某个类

- ClassLoader getParent()

    获取类装载器的父装载器

### 主要反射类
- Constructor: 类的构造函数反射类，通过Class#getConstructors()方法可以获取类的所有构造函数反射对象数组

- Method : 类方法的反射类，通过 Class#getDeclaredMethod()方法可以获取类的所有方法反射类对象数组Method[]

- Field : 类的成员变量的反射类，通过 Class#getDeclaredField()方法可以获取类的成员变量反射类对象数组，通过 Class#getDeclaredField(String name)方法可以某个特定名称的成员变量反射类对象数组

特例运用：通过反射调用private和protected成员变量和方法，只要JVM的安全机制允许


## 资源抽象接口



Resource接口的主要方法

- boolean exists():资源是否存在
- boolean isOpen():资源是否打开
- URL getURL() throws IOException : 如果底层资源可以表示成URL，则改方法返回对应的URL对象
- File getFile() throws IOException : 如果底层资源对应一个文件，则改方法返回对应的File对象
- InputStream getInputStream() throws IOException : 返回资源对应的输入流
 
 Resource接口的具体实现类
 
 - WritableResource : 可写资源接口
 - ByteArrayResource : 二进制数组表示的资源
 - ClassPathResource : 类路径下的资源
 - FileSystemResource : 文件系统下的资源 
 - InputStreamResource : 以输入流返回标识的资源
 - ServletContextResouce : 为访问Web容器上下文中的资源而设计的类
 - UrlResource : 可以通过URL访问的资源
 - PathResource : 可以访问任何可以通过URL、Path、系统文件路径表示的资源

例子：
FileSourceExample

可以通过ServletContextResource以相对于Web应用的根目录的方式访问文件资源
对于远程服务器（Web服务器或者ftp服务器）的文件资源，可通过UrlResource进行访问

### 资源加载

资源地址表达式：
classpath: 
file:
http://
ftp://
无

classpath:和classpath*: 的区别：前者只加载扫描到的第一个文件，后者则会扫描所有

Ant风格的资源地址支持三种匹配符：
?: 匹配文件名中的一个字符
*：匹配文件名中的任意字符
**：匹配多层路径

### BeanFactory 和 ApplicationContext

org.springframework.beans.factory.BeanFactory
IoC容器，框架基础，面向spring

org.springframework.context.ApplicationContext
spring容器,面向开发者

#### BeanFactory
 主要方法是 getBean(String beanName),从容器中返回特定名称的Bean 
 
 扩展接口：
 ListableBeanFactory
 HierarchicalBeanFactory
 ConfigurableBeanFactory
 AutowireCapableBeanFactory
 SingletonBeanRegistry
 BeanDefinitionRegistry
 
 #### ApplicationContext
 主要的实现类:
- ClassPathXmlApplicationContext : 默认从类路径加载配置文件
- FileSystemXmlApplicationContext : 默认从文件系统中装载配置文件
 
 扩展接口：
 ApplicationEventPublisher
 MessageSource
 ResourcePatternResolver
 LifeCycle
 
 ConfigurableApplicationContext 新增 refresh() 、close() 用于刷新缓存和关闭上下文
 
 ClassPathXmlApplicationContext和FileSystemXmlApplicationContext都可以显示使用带资源类型前缀的路径，它们的区别在于如果不显示指定资源类型前缀，则分别将路径接续为文件系统路径和类路径
 
 ### 初始化区别
 
 BeanFactory 在初始化容器时，并未实例化Bean，知道第一次访问某个Bean是才实例化目标Bean；
 ApplicationContext则在初始化应用上下文时就实例化所有单实例的Bean。
 因此，后者的初始化时间会稍长一点
 
 Spring支持基于类注解的配置方式。由于JavaConfig
 @Configuration
 
 
 Groovy脚本语言
 
 ### WebApplicationContext
 
 专门为Web应用准备的，允许从相对于Web根目录的路径中装载配置文件完成初始化
 
 在非Web应用的环境下，Bean只有singleton和prototype两种作用域，WebAppliationContext为Bean添加了三个新的作用域：request/session/global session
 
 从Web容器中获取WebApplicationContext：
 WebApplicationContext wac = (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOR_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
 
 从Web容器中获取WebApplicationContext的初始化需要ServletContext实例
 
 Spring分别提供了用于启动WebApplicationContext的Servlet和Web容器监听器
 org.springframework.web.context.ContextLoaderServlet
 org.springframework.web.context.ContextLoaderListener
 其内部都实现了启动WebApplicationContext实例的逻辑
 
 ### Bean的生命周期
 Bean的作用范围或实例化Bean是所经历的一系列阶段
 
 
 
 