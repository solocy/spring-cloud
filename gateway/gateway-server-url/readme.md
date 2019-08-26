
#spring cloud gateway url
本module主要演示了如何通过spring cloud gateway 实现路由转发到指定网页


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
  port: 8070
spring:
  application:
    name: gateway-server
  cloud:
    gateway:
      # 解决跨域问题
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "*"
            allowedMethods:
            - GET
      # 跳转到指定网页
      routes:
        - id: test
          uri: http://www.baidu.com
          predicates:
            - args:
              - pattern: /**
              name: Path

```
##启动类


```
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}


```


##运行
1.  启动服务端，打开浏览器 ，访问 http://localhost:8070可以看到访问到百度页面
