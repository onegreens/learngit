## 一、springboot整合要点：

1. pom.xml中jar包的添加：添加专属的springboot包，其中就会包含大部分需要的类
2. springboot的启动方式有两种
     - 通过添加插件spring-boot-maven-plugin，然后通过插件运行
     - 通过启动类MyApplication调用
3. springboot无需配置spring配置文件，它会自动扫描资源文件中的application.properties
4. 启动类需要放在所有的文件外面
5. 在整合mybatis时，需要注意扫描需要的文件
6. 在整合springmvc时，需要注意文件的归属，

application.properties文件属性：
7. springmvc :说明jsp文件的前缀后缀
8. 链接数据库的各项属性说明
9. mybatis的各配置的扫描路径

## 二、使用spring cache
1. 需要为需要进行缓存的实体类进行序列化

2. 使用自定义缓存
如 CacheServiceImpl#login(String username, String password)


3. 使用注解缓存
如 CacheServiceImpl#getUserPo(String userName)
3.1 需要添加包：spring-boot-starter-cache
3.2 Application添加注解：@EnableCaching
3.2 CacheServiceImpl添加注解：@CacheConfig
3.3 方法getUserPo()添加注解：@Cacheable(cacheNames = "getUserPo")

### 2.1 注解

#### @Cacheable 

- 作用：指定被注解方法的返回值是可被缓存的
- 原理：Spring先字啊缓存中查找数据，如果没有则执行方法并缓存结果，然后返回数据。
- 要求：必须提供缓存名
- 多缓存：@Cacheable("name1","name2")
- 键生成器：缓存的本质就是键/值对集合
- 带条件的缓存：

    condition 接收：@Cacheable(cacheNames = "name1", condition = "#user.age <35")
    
    unless 排除：@Cacheable(cacheNames = "name1", condition = "#user.age <35")

#### @CachePut

效果等同于@Cacheable 

区别：
@Cacheable 跳过方法直接获取缓存时，@CachePut会强制执行方法以更新缓存

#### @CacheEvict

效果等同于@Cacheable的反向操作，从给定缓存中移除一个值
属性：key|condition|allEntries:是否移除所有条目，默认否|beforeInvocation：在调用方法之前还是调用方法之后完成移除操作，默认调用之后运行

#### @Caching

组注解

例子：CachingServiceImpl

#### @CacheConfig

全局缓存注解 

该类下的方法默认使用缓存

### 2.2 缓存管理器

1. SimpleCacheManager

简易版本

配置缓存列表，并进行相关操作


配置示例：

```xml
<bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">
    <property name="caches">
    <set>
    <bean id="users" class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" />
</set>
</property>
</bean>
```

2.NoOpCacheManager

主要用于测试目的

```xml
<bean id="cacheManager" class="org.springframework.cache.support.NoOpCacheManager"/>
```

3.ConcurrentMaoCacheManager

与SimpleCacheManager相似，但是不需要定义缓存列表

```xml
<bean id="cacheManager" class="org.springframework.cache.concurrent.ConcurrentMapCacheManager"/>
```

4.CompositeCacheManager

可定义多个缓存管理器

```xml
<bean id="cacheManager" class="org.springframework.cache.support.CompositeCacheManager">
<peoperty name="cacheManagers">
<list>
<bean  class="org.springframework.cache.support.SimpleCacheManager">
    <property name="caches">
    <set>
    <bean id="users" class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean" />
</set>
</property>
</bean>
<bean class="com.hazelcast.spring.cache.HazelcastCacheManager">
<constructor ref="hazelcast" />
</bean>
</list>
</peoperty>
</bean>

```

### 2.3 SpEl表达式

可基于上下文并通过使用缓存抽象，提供与root对象相关联的缓存特定的内置参数

| 名称          | 位置     | 描述                                       | 实例                   |
| ----------- | ------ | ---------------------------------------- | -------------------- |
| methodName  | root对象 | 当前被调用的方法名                                | #root.methodName     |
| method      | root对象 | 当前被调用的方法                                 | #root.method         |
| target      | root对象 | 当前被调用的目标对象的实例                            | #root.target         |
| targetClass | root对象 | 当前被调用的目标对象的类                             | #root.targetClass    |
| args        | root对象 | 当前被调用的方法的参数列表                            | #root.args[0]        |
| caches      | root对象 | 当前党阀使用的缓存列表                              | #root.caches[0].name |
| Argument    | 执行上下文  | 当前被调用的方法的参数，如findByUser(User user),可以通过#user.id获取参数 | #user.id             |
| result      | 执行上下文  | 方法执行后的返回值（仅当前方法执行之后的判断有效）                | #result              |


### 2.4 基于XML的Cache声明
### 2.5 编程方式初始化缓存
### 2.6 自定义缓存注解

