
#spring cloud sleuth simple 
本module主要演示了如何通过spring cloud sleuth 实现 链路追踪


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8050
spring:
  application:
    name: eureka-consumer
  sleuth:
    sampler:
      probability: 1.0   # 采样比例设置 1.0 全部获取  0->1
```
##启动类


```
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String hello() {
//        return restTemplate.getForEntity("http://eureka-producer",String.class).getBody();

        return "success";
    }
    @GetMapping("/test")
    public String helloTest() {
        return restTemplate.getForEntity("http://eureka-producer",String.class).getBody();
    }
}
```


##运行
1.  启动所有服务
2.  打开浏览器 ，访问 http://localhost:8050可以看到consumer 和producer 服务的控制台打印

```

2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,false] 6283 --- [nio-8050-exec-1] org.apache.tomcat.util.http.Parameters   : Set encoding to UTF-8
2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,false] 6283 --- [nio-8050-exec-1] o.s.web.servlet.DispatcherServlet        : GET "/", parameters={}
2019-08-27 15:10:23.587 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,false] 6283 --- [nio-8050-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to public java.lang.String com.example.eurekaconsumer.EurekaConsumerApplication.hello()

```
