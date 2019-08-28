
#spring cloud zuul eureka
本module主要演示了如何通过spring cloud zuul 作为服务网关 连通eureka 服务


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8041
spring:
  application:
    name: zuul-gateway
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8042/eureka # 注册到eureka
zuul:
#  ignored-patterns: '*'
  routes:
    producer:
      path: /eureka-producer/**
      serviceId: eureka-producer # 转到 指定服务

```
##启动类


```
@SpringBootApplication
@EnableZuulProxy #申明zuul
@EnableDiscoveryClient
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

}

```


##运行
1.  启动服务
2.  访问http://localhost:8041 返回404
3.  访问http://localhost:8044/eureka-producer 返回"hello eureka producer"