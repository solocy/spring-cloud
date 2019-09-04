
#spring cloud ribbon
本module主要演示了如何通过spring cloud ribbon  实现 负载均衡


##pom.xml

``` 
// 关键依赖
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8100
spring:
  application:
    name: ribbon-simple
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8101/eureka
```
##启动类


```

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonSimpleApplication.class, args);
	}

    // 实现负载均衡的restTemplate
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate template;

    @GetMapping("/dc")
    public String getDC() {
        return template.getForEntity("http://eureka-producer/",String.class).getBody();
    }
}
```


##运行
1.  启动所有服务
2.  在eureka 服务端查看服务注册
3.  访问http://localhost:8100/dc 可以看到eureka-producer 和 eureka-producer-1 交替出现，这就实现了最简单的负载均衡了


##补充
自定义负载均衡策略可看 ribbon-custom 