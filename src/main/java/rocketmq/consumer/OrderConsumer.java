package rocketmq.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import rocketmq.channel.OrderSourceInput;

@EnableBinding(OrderSourceInput.class)
public class OrderConsumer {

    @StreamListener(OrderSourceInput.INPUT)
    public void handle(String message) {
        System.out.println("OrderConsumer.Received.handle.Received: " + message);
    }
}
