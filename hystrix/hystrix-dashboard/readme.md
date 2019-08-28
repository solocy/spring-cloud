
#spring cloud hystrix dashboard
本module主要演示了如何通过spring cloud hystrix dashboard监测hystrix 服务


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

##application.yml


```
server:
  port: 8021
spring:
  application:
    name: hystrix-dashboard
```
##启动类


```

@SpringBootApplication
@EnableHystrixDashboard  #申明使用 hystrix dashboard
@EnableCircuitBreaker
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }

}
```


##运行
1.  启动服务，打开浏览器 ，访问 http://localhost:8021可以看到网页，在输入对应的hystrix stream （http://localhost:8020/hystrix.stream） 
