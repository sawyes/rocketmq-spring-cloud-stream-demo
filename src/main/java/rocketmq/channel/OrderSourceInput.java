package rocketmq.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderSourceInput {

    String INPUT = "orderSourceInput";

    @Input(OrderSourceInput.INPUT)
    SubscribableChannel input();

}
