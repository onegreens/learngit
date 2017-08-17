springboot整合要点：

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