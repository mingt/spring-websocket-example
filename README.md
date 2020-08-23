# Spring Websocket Example
这是一个演示 Spring OAuth2 上整合 Websockect 的例子，同时有一个整合 RabbitMQ 作为 Message Broker 的分支。

## 特性
* 以 Spring Cloud starter 项目为例。
* Gradle 构建，多层模块结构。同时演示一份微服务模块的代码结构。
* Spring OAuth2, Websockect, RabbitMQ

## 缘由
（待补充）

## 用法
（待补充）

### 相关实现
* （待补充）

### 编译和使用

（1） build-all-projects.sh 直接运行 ```bash build-all-projects.sh``` 或参考其中的编译命令单独编译。

<pre>
#!/bin/sh

# build_type 有 release 或 test 等

cd base-common; ./gradlew clean build -P build_type=release; cd ..
cd websocket-teaching; ./gradlew clean build -P build_type=release; cd ..
</pre>

（2） 运行 websocket-teaching 。请参照 ```websocket-teaching/run-local.sh``` 的启动命令行，可能需要调整 jar 的版本号。

<pre>
java -jar build/libs/basic-websocket-teaching-0.0.1.jar
或调整为：
java -jar build/libs/basic-websocket-teaching-1.0.0.jar
</pre>

(3) Websocket 前端辅助调试页面
（待补充）

(4) 业务指令样例。

### FAQ

（1） 背景知识

（2） 下一步还能做什么？

(待补充)

## 其他

### Spring Cloud starter
* 注册中心，配置中心，认证中心等已略去。 本项目的业务模块配置改回本地 Spring Boot 配置项。
* 来源于以下项目： [anilallewar/microservices-basics-spring-boot](https://github.com/anilallewar/microservices-basics-spring-boot) 致谢原作者，其最近更新也为 k8s 部署提供了一个方向。
* 为了方便国内访问，正常查看配图，目前在 Gitee 做了一份克隆： [ahming/microservices-basics-spring-boot](https://gitee.com/m1024ing/microservices-basics-spring-boot)
