package com.covid.covid.controller;

import com.covid.covid.dto.DataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.covid.covid.util.Constant.EXCHANGE;
import static com.covid.covid.util.Constant.ROUTING_KEY;

@Controller
@RequestMapping("/send")
public class PublisherController {
    Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/temperature")
    public ResponseEntity<String> sendDataToQueue(@RequestBody DataDTO dataDTO) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, dataDTO);
        logger.info("To Exchange: Data Packet: {}", EXCHANGE, ROUTING_KEY, dataDTO.getData());
        return new ResponseEntity<String>("Success!", HttpStatus.OK);
    }
}
