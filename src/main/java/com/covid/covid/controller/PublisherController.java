package com.covid.covid.controller;

import com.covid.covid.dto.CircleResponseDTO;
import com.covid.covid.dto.DataDTO;
import com.covid.covid.dto.ReadingsDTO;
import com.covid.covid.dto.TracingQueryDTO;
import com.covid.covid.service.GoogleMapContactTracingService;
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

import javax.servlet.http.HttpServletRequest;

import static com.covid.covid.util.Constant.EXCHANGE;
import static com.covid.covid.util.Constant.ROUTING_KEY;

@Controller
@RequestMapping("/data")
public class PublisherController extends BaseController {
    Logger logger = LoggerFactory.getLogger(PublisherController.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GoogleMapContactTracingService googleMapContactTracingService;

    @PostMapping("/temperature")
    public ResponseEntity<String> sendDataToQueue(HttpServletRequest request, @RequestBody DataDTO dataDTO) {
        if (!validateUser(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, dataDTO);
        logger.info("To Exchange: Data Packet: {}", EXCHANGE, ROUTING_KEY, dataDTO.getData());
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/submitreadings")
    public ResponseEntity<String> getReadings(HttpServletRequest request, @RequestBody ReadingsDTO readingsDTO) {
        if (!validateUser(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        googleMapContactTracingService.submitReadings(readingsDTO);
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/getcircles")
    public ResponseEntity<CircleResponseDTO> getCircles(HttpServletRequest request, @RequestBody TracingQueryDTO tracingQueryDTO) {
        if (!validateUser(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info(tracingQueryDTO.toString());
        CircleResponseDTO circleResponseDTO = googleMapContactTracingService.contactTracingFinder(tracingQueryDTO);
        return new ResponseEntity<>(circleResponseDTO, HttpStatus.OK);
    }


}
