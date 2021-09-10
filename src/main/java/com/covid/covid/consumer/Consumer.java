package com.covid.covid.consumer;

import com.covid.covid.dto.DataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.covid.covid.util.Constant.QUEUE_NAME;

@Component
public class Consumer {
    Logger logger = LoggerFactory.getLogger(Consumer.class);

}
