# Spring cloud 各种实现案例

_项目简介_

Spring cloud 是用来学习Spring cloud 的项目
该项目现已集成 Spring cloud config （分布式配置中心,集成git），Spring cloud consul (服务发现和注册中心)，Spring cloud eureka (服务发现和注册中心),Spring cloud Gateway（服务网关,集成eureka），Spring cloud netflix hystrix (熔断器)，Spring cloud alibaba nacos (服务发现和注册中心,集成Spring cloud config),Spring cloud alibaba sentinel（接口限流，熔断），Spring cloud sleuth （链路监控，集成 zipkin和rabbitmq）, Spring cloud stream (消息驱动，集成rabbitmq),Spring cloud zuul（服务网关，集成eureka）

-------

_开发环境_
jdk 1.8
maven 3.5
intellij idea 2019+


-------

_运行方式_

1. git clone https://github.com/solocy/spring-cloud.git

2. 使用idea 打开项目
3. 在 IDEA 中 Maven Projects 的面板导入项目根目录下 的 pom.xml 文件
4. 找到各个 Module 的 Application 类就可以运行各个 demo 了
-------
