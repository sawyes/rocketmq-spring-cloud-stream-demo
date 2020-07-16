package rocketmq.controller;

import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@EnableBinding(Source.class)
public class TagsController {

    @Autowired
    private Source source;

    @GetMapping("/mysink")
    /**
     * test commond curl 127.0.0.1:9091/tags/mysink
     *
     */
    public String mySink() {
        // 没有设置任何header, 且consumer也没有condition
        Message<String> message = MessageBuilder
                .withPayload("tags")
                .setHeader(RocketMQHeaders.TAGS, "tag1")
                .build();

        this.source.output().send(message);
        return "ok";
    }
}
