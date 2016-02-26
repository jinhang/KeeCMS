## KeeCMS

> Java开发的CMS
> Spring MVC为表现层，Spring为业务层，MyBatis为数据层。
>
* 优化表设计
* 对于目录和文章，有附件和评论
* 支持4级目录
* 初始化后台登录用户

**前台：** http://localhost:8080/index.htm

**后台：** http://localhost:8080/auth/admin/login.htm

## 技术
* jQuery
* Bootstrap
* Java
* Maven
* Spring
* Spring MVC
* MyBatis
* MySQL
* FreeMarker
* Lucene


## 安装
>	# 进入CMS目录

>	cd CMS

>	# 清理

>	mvn clean

>	# 编译

>	mvn compile

>	# 修改数据库配置文件

>		1、 把 src/main/resources/keecms.properties 拷贝到 CMS目录下

>		2、 修改keecms.properties里的数据库信息

>	# 安装

>	mvn exec:java -Dexec.mainClass="com.kee.cms.Install"
>	# 运行

>	mvn jetty:run

>	# 后台地址

>	http://localhost:8080/auth/admin/login.htm

>	登录邮箱：***@***.com
>	登录密码：***
