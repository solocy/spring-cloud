
#spring cloud sleuth zipkin 
本module主要演示了如何通过spring cloud sleuth zipkin实现 链路追踪


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8053
spring:
  application:
    name: eureka-consumer
  zipkin:
    base-url: http://localhost:9411 # zipkin 服务端地址
  sleuth:
    web:
      client:
        enabled: true
    sampler:
      probability: 1.0   # 采样比例设置 1.0 全部获取  0->1
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8055/eureka
  instance:
    statusPageUrlPath: http://${eureka.instance.hostname}:${server.port}/actuator/info
    healthCheckUrlPath: http://${eureka.instance.hostname}:${server.port}/actuator/health
    hostname: localhost
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
        return restTemplate.getForEntity("http://eureka-producer",String.class).getBody();
    }
}
```


##运行
1.  启动zipkin服务端
2.  启动所有服务
3.  打开浏览器 ，访问 http://localhost:8053可以看到consumer 和producer 服务的控制台打印

```

2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] org.apache.tomcat.util.http.Parameters   : Set encoding to UTF-8
2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] o.s.web.servlet.DispatcherServlet        : GET "/", parameters={}
2019-08-27 15:10:23.587 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to public java.lang.String com.example.eurekaconsumer.EurekaConsumerApplication.hello()

```
4.  访问http://localhost:9411 可以看到每次访问时的记录和耗时