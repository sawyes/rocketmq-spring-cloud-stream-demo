package rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import rocketmq.channel.MySink;

@EnableBinding({MySink.class})
@Slf4j
public class TagsConsumer {

    @StreamListener(MySink.INPUT1)
    public void input1(String message) throws Exception {
        Thread.sleep(15000);
        log.info("TagsConsumer.Received.input1.Received: " + message);
    }

    @StreamListener(value = MySink.INPUT2)
    public void input2(String message) throws Exception {
        System.out.println("TagsConsumer.Received.input2.Received: " + message);
    }

}
