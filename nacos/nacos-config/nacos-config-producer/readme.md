
#spring cloud alibaba nacos config producer 
本module主要演示了如何通过spring cloud alibaba nacos 作为 配置中心


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
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

## bootstrap.yml
```
spring:
  application:
    name: nacos-config-producer
  cloud:
    nacos:
      config:
        server-addr: localhost:8848 # nacos 服务地址
        file-extension: yaml # 指定文件类型
        group: DEFAULT_GROUP #指定使用组
#        prefix: test
#        namespace: 9ceee635-c040-44be-a08c-4b062615b291
        ext-config:
          - dataId: test.yaml
            group: dev_GROUP
            refresh: true #修改自动刷新
          - dataId: test_TEST.yaml
            group: DEFAULT_GROUP
            refresh: true

```
##application.yml


```
server:
  port: 8062
```
##启动类


```


@SpringBootApplication
@RestController
@RefreshScope  #申明刷新
public class NacosConfigProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigProducerApplication.class, args);
    }

    @Value("${application.test}")
    private String test;

    @GetMapping("/")
    public String getTest() {
        return test;
    }
}
```


##运行
1.  启动nacos 服务端，打开浏览器 ，访问 http://localhost:8848/nacos可以看到nacos 服务端网页，添加配置
2.  启动服务，打开浏览器 ，访问 http://localhost:8062可以看到控制台打印出配置的信息
