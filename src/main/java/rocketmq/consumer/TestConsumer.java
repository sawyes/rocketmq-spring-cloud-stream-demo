package rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@Slf4j
public class TestConsumer {

    @StreamListener(Sink.INPUT)
    public void handle(String message) throws Exception{
        Thread.sleep(5000);
        log.info("TestConsumer.Received.handle.Received: " + message);
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['hello'] == 'world'")
    public void hello(String message) {
        System.out.println("TestConsumer.Received.hello.Received: " + message);
    }

    @StreamListener(value = Sink.INPUT, condition = "headers['hello'] == 'jobs'")
    public void jobs(String message) {
        System.out.println("TestConsumer.Received.jobs.Received: " + message);
    }
}
