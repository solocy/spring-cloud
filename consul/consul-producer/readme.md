#spring cloud consul producer
本module主要演示了如何通过spring cloud consul 注册服务
##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
		
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

##application.yml


```
spring:
  application:
    name: consul-consumer
  cloud:
    consul:
      port: 8500
      host: localhost
      discovery:
        service-name: producer1    # 注册时的服务名
```
##启动类


```

@SpringBootApplication
@EnableDiscoveryClient  # 申明为服务注册客户端
@RestController
public class ConsulProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulProducerApplication.class, args);
	}

    @GetMapping("/health")
    public String health(){
        System.out.println("+=======");
        return "hello consul";
    }
}
```

##运行

1.  启动docker :consul.yml。访问http://localhost:8500。可以看到注册上的服务
