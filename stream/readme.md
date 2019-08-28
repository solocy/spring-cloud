
#spring cloud stream
本module主要演示了如何通过spring cloud stream 和 rabbitmq 实现 数据的发送和接收 


##pom.xml

``` 
// 关键依赖
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-test-support</artifactId>
    <scope>test</scope>
</dependency>
```

##application.yml
```
server:
  port: 8051
spring:
  application:
    name: stream-rabbitmq
  rabbitmq:
    username: admin
    password: admin
  cloud:
    stream:
      bindings:
        output:
          group: test  # 在同一个服务 高可用时，定义为同一个组，则可使同一个服务每次消息来临时会自动分配指定一个服务去获取数据
          destination: test  # 指定通道
        input:
          group: test  # 在同一个服务 高可用时，定义为同一个组，则可使同一个服务每次消息来临时会自动分配指定一个服务去获取数据
          destination: test # 指定通道
```
##启动类


```



@SpringBootApplication
@EnableBinding(Source.class)
public class StreamRabbitOutputApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitOutputApplication.class, args);
    }


    @PostConstruct
    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedRate = "5000")) // 输出  每5秒一次
    public Message<String> sendMessage() {
        System.out.println("hello");
        return MessageBuilder.withPayload("hello").build();
    }
    
        @StreamListener(Sink.INPUT) // 输出
    public void getMessage(Object object) {
        System.out.println(object.toString());
    }
}
```


##运行
1.  启动rabbitmq服务端
2.  启动所有服务
3.  可以看到控制台的记录