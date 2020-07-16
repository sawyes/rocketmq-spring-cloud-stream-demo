# spring cloud stream

[spring-cloud-stream 英文document](https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/2.2.0.RELEASE/spring-cloud-stream.html)

[参考](https://cloud.tencent.com/developer/article/1480983)

## pom

[ali maven](https://maven.aliyun.com/mvn/search)

```
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rocketmq</artifactId>
        <version>2.2.1.RELEASE</version>
    </dependency>
```

## Spring Cloud Stream 介绍

Spring Cloud Stream 是一个用于构建基于消息的微服务应用框架。它基于 SpringBoot 来创建具有生产级别的单机 Spring 应用，并且使用 Spring Integration 与 Broker 进行连接。

Spring Cloud Stream 提供了消息中间件配置的统一抽象，推出了 publish-subscribe、consumer groups、partition 这些统一的概念。

Spring Cloud Stream 内部有两个概念：`Binder` 和 `Binding`。

* Binder: 跟外部消息中间件集成的组件，用来创建 Binding，各消息中间件都有自己的 Binder 实现。
比如 Kafka 的实现 KafkaMessageChannelBinder，RabbitMQ 的实现 RabbitMessageChannelBinder 以及 RocketMQ 的实现 RocketMQMessageChannelBinder。

* Binding: 包括 Input Binding 和 Output Binding。
Binding 在消息中间件与应用程序提供的 Provider 和 Consumer 之间提供了一个桥梁，实现了开发者只需使用应用程序的 Provider 或 Consumer 生产或消费数据即可，屏蔽了开发者与底层消息中间件的接触。



## 加配置

```
spring: 
  cloud:
    stream:
      rocketmq:
        binder:
          name-server: 127.0.0.1:9876
      bindings:
        output:
          # 指定topic
          destination: stream-test-topic
```


