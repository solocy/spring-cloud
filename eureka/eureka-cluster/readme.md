
#spring cloud eureka cluster
本module主要演示了如何通过spring cloud eureka 建立eureka 集群， 实现服务的高可用


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
  port: 8033
spring:
  application:
    name: eureka-server-1
eureka:
  instance:
    hostname: localhost # 定义eureka的ip 
  client:
    service-url:
      defaultZone: http://localhost:8034/eureka/ # 将当前服务器注册到服务端机群里的其他服务端
    register-with-eureka: true # 是否能注册
    fetch-registry: true # 从其他集群获取注册信息
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
1.  启动两个服务端，打开浏览器 ，访问 http://localhost:8033 或 http://localhost:8034 可以看到注册的服务信息了

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
  port: 8035
spring:
  application:
    name: eureka-consumer
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8033/eureka,http://localhost:8034 #将服务注册客户端注册到服务端集群里
      
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
@EnableCircuitBreaker  # 申明使用熔断器
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
1.  启动两个客户端consumer 和producer，打开浏览器 ，访问 http://localhost:8033 或 http://localhost:8034 可以看到注册的服务信息了
2.  在浏览器上访问 http://localhost:8035 可以访问到 producer ，可以看到producer 控制台打印了 “hello eureka producer”
