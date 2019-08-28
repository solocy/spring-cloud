
#spring cloud zuul url
本module主要演示了如何通过spring cloud zuul 作为服务网关


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
</dependency>
```

##application.yml
```
server:
  port: 8040
spring:
  application:
    name: zuul-geteway-url
zuul:
  ignored-patterns: '*' # 过滤其他请求
  routes:
    blog:
      path: /blog/**
      url: blog.lovem.wang
    git:
      path: /git/**
      url: http://www.lovem.wang


```
##启动类


```
@SpringBootApplication
@EnableZuulProxy # 申明使用zuul 
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

}
```


##运行
1.  启动服务
2.  访问http://localhost:8044 返回404
3.  访问http://localhost:8044/blog 返回401