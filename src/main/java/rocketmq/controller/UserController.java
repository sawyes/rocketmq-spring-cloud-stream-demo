package rocketmq.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.dao.UserMapper;
import rocketmq.model.User;
import org.springframework.messaging.Message;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@EnableBinding(Source.class)
public class UserController {

    @Resource
    UserMapper userMapper;

    @Autowired
    private Source source;


    // @GetMapping("/list")
    // /**
    //  * 测试 生产者/消费者模式
    //  */
    // public List<User> list() {
    //
    //     List<User> users = userMapper.selectAll();
    //
    //     List<Message> messages = new ArrayList<Message>();
    //
    //     for (User user : users) {
    //
    //         Message<String> message = MessageBuilder
    //                 .withPayload(JSON.toJSONString(user))
    //                 .setHeader(RocketMQHeaders.KEYS, "KEY_" + user.getId())
    //                 .build();
    //
    //         source.output().send(message);
    //     }
    //
    //     return users;
    // }

    @GetMapping("/handle")
    /**
     * TestConsumer
     *
     * 1. hello方法因为condition hello == world ,条件不满足,不可以消费
     * 2. 没有condition的handler方法可以消费
     * 3.hello方法因为condition hello == job ,条件不满足,不可以消费
     *
     * test commond curl 127.0.0.1:9091/user/handle
     */
    public String handle()
    {
        // 没有设置任何header, 且consumer也没有condition
        Message<String> message = MessageBuilder
                .withPayload("hello world")
                .build();

        source.output().send(message);

        return "send ok";
    }

    @GetMapping("/hello")
    /**
     * TestConsumer
     *
     * 1. hello方法因为condition hello == world 可以消费
     * 2. 没有condition的handler方法也可以消费
     * 3.hello方法因为condition hello == job 不可以消费
     *
     * test commond curl 127.0.0.1:9091/user/hello
     */
    public String hello()
    {
        Message<String> message = MessageBuilder
                .withPayload("hello world")
                .setHeader("hello", "world")
                .build();

        source.output().send(message);
        // 输出结果
        //
        // TestConsumer.Received.hello.Received: hello world
        // TestConsumer.Received.handle.Received: hello world
        return "send ok";
    }

    @GetMapping("/jobs")
    /**
     * TestConsumer
     *
     * 1. hello方法因为condition hello == world 不可以消费
     * 2. 没有condition的handler方法也可以消费
     * 3.hello方法因为condition hello == job 可以消费
     *
     * test commond curl 127.0.0.1:9091/user/hello
     */
    public String jobs()
    {
        Message<String> message = MessageBuilder
                .withPayload("hello world")
                .setHeader("hello", "jobs")
                .build();

        source.output().send(message);

        // 输出结果
        // TestConsumer.Received.jobs.Received: hello world
        // TestConsumer.Received.handle.Received: hello world
        return "send ok";
    }
}
