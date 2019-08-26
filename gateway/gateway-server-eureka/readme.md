
#spring cloud gateway eureka
本module主要演示了如何通过spring cloud gateway 和 Spring cloud eureka建立服务注册和路由转发实现

-------

-------
#spring cloud eureka server
服务端


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>

```

##application.yml


```
server:
  port: 8073
spring:
  application:
    name: eureka-server
eureka:
  instance:
    hostname: localhost # 定义eureka的ip 
  client:
    service-url:
      defaultZone: http://localhost:8073/eureka/ # 将当前服务器注册到服务端机群里的其他服务端
    register-with-eureka: false # 是否能注册
    fetch-registry: false # 从其他集群获取注册信息
```
##启动类


```
@SpringBootApplication
@EnableEurekaServer # 申明为eureka  服务端 
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

```


##运行
1.  启动服务端，打开浏览器 ，访问 http://localhost:8073可以看到注册的服务信息了
-------

-------
#Spring cloud eureka client
客户端
##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

```

##application.yml


```
server:
  port: 8072
spring:
  application:
    name: eureka-producer
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8073/eureka #将服务注册客户端注册到服务端
 
eureka
  instance:
    statusPageUrlPath: http://${eureka.instance.hostname}:${server.port}/actuator/info  # 若有引用Spring boot acturtor 可添加监听状态
    healthCheckUrlPath: http://${eureka.instance.hostname}:${server.port}/actuator/health # 若有引用Spring boot acturtor 可添加监听状态
    hostname: localhost
```
##启动类


```
@SpringBootApplication
@EnableDiscoveryClient  # 申明为客户端
@RestController
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

     @GetMapping("/")
    public String hello() {
        return "hello eureka producer gateway";
    }
    }


```

#Spring cloud gateway 服务端

##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

##application.yml


```
server:
  port: 8071
spring:
  application:
    name: gateway-server-eureka
  cloud:
    gateway:
      # 解决跨域问题
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods:
            - GET
      # 指定路由
      routes:
        - id: producer
          uri: lb://eureka-producer
          predicates:
            - name: Path
              args:
                - pattern: /**
# 注册到eureka上
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8073/eureka
```
##启动类


```
@SpringBootApplication
@EnableDiscoveryClient # 注册到eureka
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}


```


##运行
1.  启动服务端，打开浏览器 ，访问 http://localhost:8071可以看到访问到eureka-producer 服务，在控制台上可以看到打印的 “hello eureka producer gateway”。
