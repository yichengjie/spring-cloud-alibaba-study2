package com.yicj.rocket.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

public interface OrderSource {
    String OUTPUT = "orderOutput";

    @Output(Source.OUTPUT)
    MessageChannel output();
}
