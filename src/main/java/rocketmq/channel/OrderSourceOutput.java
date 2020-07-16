package rocketmq.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderSourceOutput {
    String OUTPUT = "OrderSourceOutput";

    @Output(OrderSourceOutput.OUTPUT)
    MessageChannel output();
}
