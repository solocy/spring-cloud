
#spring cloud sleuth zipkin rabbitmq
本module主要演示了如何通过spring cloud sleuth zipkin 和rabbitmq 实现 链路追踪


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.amqp</groupId>
    <artifactId>spring-rabbit</artifactId>
</dependency>
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
  port: 8056
spring:
  application:
    name: sleuth-zipkin-rabbitmq
  rabbitmq:
    username: admin
    password: admin
    host: localhost
  zipkin:
#    base-url: http://localhost:9411
    sender:
      type: rabbit # 申明通过rabbitmq 来传递数据
  sleuth:
    web:
      client:
        enabled: true
    sampler:
      probability: 1.0
#logging:
#  level:
#    Root: debug
```
##启动类


```


@SpringBootApplication
@RestController
public class SleuthZipkinRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthZipkinRabbitmqApplication.class, args);
	}


    @GetMapping("/")
    public String hello() {
        return "hello  sleuth";
    }
}
```


##运行
1.  启动zipkin服务端
2.  启动rabbitmq 
3.  启动所有服务
4.  打开浏览器 ，访问 http://localhost:8056可以看到服务的控制台打印

```

2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] org.apache.tomcat.util.http.Parameters   : Set encoding to UTF-8
2019-08-27 15:10:23.580 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] o.s.web.servlet.DispatcherServlet        : GET "/", parameters={}
2019-08-27 15:10:23.587 DEBUG [eureka-consumer,736c2aea1b80db50,736c2aea1b80db50,true] 6283 --- [nio-8050-exec-1] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped to public java.lang.String com.example.eurekaconsumer.EurekaConsumerApplication.hello()

```
4.  访问http://localhost:9411 可以看到每次访问时的记录和耗时