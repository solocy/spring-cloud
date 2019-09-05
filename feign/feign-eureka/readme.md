
#spring cloud feign eureka
本module主要演示了如何通过spring cloud feign 集成eureka  调用微服务


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8112
spring:
  application:
    name: feign-eureka-client
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8113/eureka
```
##启动类


```
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class FeignEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignEurekaClientApplication.class, args);
    }

    @Autowired
    private MyFeignClient myFeignClient;

    // 自定义负载均衡策略
    @Bean
    public IRule iLoadBalancer() {
        return new CustomRule();
    }

    @GetMapping("/")
    public String getData() {
        return  myFeignClient.getData();
    }
}

```


##运行
1.  启动所有服务
2.  访问http://localhost:8110/ 可以看到显示测试，访问到了其他服务了

