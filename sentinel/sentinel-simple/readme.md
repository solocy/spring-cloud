
#spring cloud alibaba sentinel simple 
本module主要演示了如何通过spring cloud alibaba sentinel 实现接口断流限制


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
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
  port: 8081
spring:
  application:
    name: sentinel-simple-server
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8080  #sentinel服务端地址
      eager: true
```
##启动类


```
@SpringBootApplication
@RestController
public class SentinelSimpleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelSimpleServerApplication.class, args);
    }


    @GetMapping("/")
    @SentinelResource("resource")  # 指定资源名
    public String getTest() {
        return "hello sentinel";
    }

    @PostConstruct
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("resource");
        // set limit qps to 20
        rule.setCount(1);  # 设置每秒访问次数
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
```


##运行
1.  启动sentinel 面板，打开浏览器 ，访问 http://localhost:8080可以看到sentinel网页，可以对每个接口设置访问限制
2.  启动服务，打开浏览器 ，访问 http://localhost:8081可以看到控制台打印出"hello sentinel"的信息，同一秒多次访问，会在控制台中打印堆栈异常信息
