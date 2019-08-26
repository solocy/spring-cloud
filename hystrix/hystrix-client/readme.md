
#spring cloud hystrix client
本module主要演示了如何通过spring cloud hystrix 实现熔断器


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##application.yml


```
server:
  port: 8020
spring:
  application:
    name: hystrix-client
```
##启动类


```
@SpringBootApplication
@EnableCircuitBreaker # 申明熔断器的使用
@RestController
public class HystrixClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixClientApplication.class, args);
	}


   # 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/dc")
    @HystrixCommand(fallbackMethod = "fallback")
    public String getDC() {
        return restTemplate.getForEntity("http://192.168.1.106:8010/health",String.class).getBody();
    }

    public String fallback() {
        return "fallback";
    }
}
```


##运行
1.  启动服务端，打开浏览器 ，访问 http://localhost:8070可以看到访问到百度页面
