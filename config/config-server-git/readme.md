#spring cloud config server git
本module主要演示了如何通过spring cloud config server 获取git配置

##pom.xml

``` 
        // 关键依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

```

##application.yml


```
server:
  port: 8001
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/solocy/configinfo # git 地址 可改为自己配置的地址
  application:
    name: spring cloud config server git
```
##启动类


```
@SpringBootApplication
@EnableConfigServer // 申明启动config server
public class ConfigServerGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerGitApplication.class, args);
    }

}

```

