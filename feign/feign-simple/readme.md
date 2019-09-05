
#spring cloud feign
本module主要演示了如何通过spring cloud feign  调用服务提供者


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8110
spring:
  application:
    name: feign-client
```
##启动类


```
@SpringBootApplication
@EnableFeignClients
@RestController
public class FeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Autowired
    private MyFeignClient myFeignClient;


    @GetMapping("/")
    private String  test() {
        return myFeignClient.getTest();
    }
}

```


##运行
1.  启动所有服务
2.  访问http://localhost:8110/ 可以看到显示测试，访问到了其他服务了

