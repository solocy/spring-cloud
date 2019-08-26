#spring cloud config client
本module主要演示了如何通过spring cloud config获取远程config server 服务配置

##pom.xml

``` 
        // 关键依赖
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```

##bootstrap.yml

config读取配置一定要申明在bootstrap.yml 文件中,因为configclient服务启动时会默认先访问bootstrap文件，然后绑定configserver,然后在获取appliaction文件信息


```
spring:
  cloud:
    config:
      enabled: true
      fail-fast: false    # 如果设置为ture 未找到config server 就会启动失败
      uri: http://localhost:8001 # config server 地址
      profile: prod  # 指定文件类型
      name: application # 文件名
```
