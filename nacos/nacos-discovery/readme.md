
#spring cloud alibaba nacos discovery
本module主要演示了如何通过spring cloud alibaba nacos 作为 服务注册中心


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.1.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

##application.yml
```
server:
  port: 8061
spring:
  application:
    name: nacos-discovery-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 # nacos注册地址
```
##启动类


```
@SpringBootApplication
@EnableDiscoveryClient  #申明服务注册
@RestController
public class NacosDiscoveryConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDiscoveryConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/")
    public String hello() {
        return "hello  nacos consumer";
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        String result = restTemplate.getForEntity("http://nacos-discovery-producer/",String.class).getBody();
        return result;
    }
```


##运行
1.  启动nacos 服务端，打开浏览器 ，访问 http://localhost:8848/nacos可以看到nacos 服务端网页，点击服务列表查看服务注册
2.  启动服务，打开浏览器 ，访问 http://localhost:8061/test可以看到控制台打印出"hello  nacos producer"的信息
