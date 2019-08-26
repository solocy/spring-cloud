#spring cloud consul consumer
本module主要演示了如何通过spring cloud consul 注册服务
##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
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
        register: true # 表示注册到服务中心
        service-name: consumer1    # 注册时的服务名    
```
##启动类


```

@SpringBootApplication
@EnableDiscoveryClient    # 申明为服务注册的客户端
@RestController
public class ConsulConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulConsumerApplication.class, args);
	}

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @GetMapping("/service")
    public String service() {
        String services = "Services: " + discoveryClient.getInstances("producer1");
        System.out.println(services);
        return services;
    }
}
```

##运行

1.  启动docker : consul.yml 访问http://localhost:8500。可以看到注册上的服务
2.  将项目运行起来，打开浏览器 ，访问 http://localhost:8012/dc 可以看到注册的服务名
3. 访问 http://localhost:8012/service 可以看到producer1 服务的详细信息


##⚠️
1. 在http://localhost:8500 有个状态检测， 若没有引入spring-boot-starter-actuator依赖，就会为false (在 producer module 中引入了，这个项目没有引入，就当案例了)