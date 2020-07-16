package rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.channel.OrderSourceOutput;

@RestController
@RequestMapping("order")
@EnableBinding(OrderSourceOutput.class)
public class OrderController {

    @Autowired
    OrderSourceOutput orderSource;

    @GetMapping("list")
    public String list(){
        // 没有设置任何header, 且consumer也没有condition
        Message<String> message = MessageBuilder
                .withPayload("hello world")
                .build();

        orderSource.output().send(message);

        return "ok";
    }

}
