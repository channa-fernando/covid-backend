package com.covid.covid.controller;

import com.covid.covid.dto.CircleResponseDTO;
import com.covid.covid.dto.DataDTO;
import com.covid.covid.dto.ReadingsDTO;
import com.covid.covid.dto.TracingQueryDTO;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.covid.covid.util.Constant.EXCHANGE;
import static com.covid.covid.util.Constant.ROUTING_KEY;

@Controller
@RequestMapping("/data")
public class PublisherController {
    Logger logger = LoggerFactory.getLogger(PublisherController.class);
    List<ReadingsDTO> readingsDTOList = new ArrayList<>();
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/temperature")
    public ResponseEntity<String> sendDataToQueue(@RequestBody DataDTO dataDTO) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, dataDTO);
        logger.info("To Exchange: Data Packet: {}", EXCHANGE, ROUTING_KEY, dataDTO.getData());
        return new ResponseEntity<String>("Success!", HttpStatus.OK);
    }

    @PostMapping("/submitreadings")
    public ResponseEntity<String> getReadings(@RequestBody ReadingsDTO readingsDTO) {
        readingsDTOList.add(readingsDTO);
        logger.info(readingsDTOList.toString());
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/getcircles")
    public ResponseEntity<CircleResponseDTO> getCircles(@RequestBody TracingQueryDTO tracingQueryDTO) {
        logger.info(tracingQueryDTO.toString());
        CircleResponseDTO circleResponseDTO = new CircleResponseDTO();
        List<Map> latLangListResponse = new ArrayList<>();
        try {
            Date dateFromQuery = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(tracingQueryDTO.getDate().trim() + " " + tracingQueryDTO.getFrom());
            Date dateToQuery = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(tracingQueryDTO.getDate().trim() + " " + tracingQueryDTO.getTo());
            for (ReadingsDTO readingsDTO : readingsDTOList) {
                List<Map> tracings = readingsDTO.getTracing();
                for (Map trace : tracings) {
                    Map<String, String> traceMap = trace;
                    String dateStr = traceMap.get("date");
                    String fromStr = traceMap.get("from");
                    String toStr = traceMap.get("to");
                    String latLangStr = traceMap.get("latLang");

                    Date dateFrom = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(dateStr.trim() + " " + fromStr);
                    Date dateTo = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(dateStr.trim() + " " + toStr);
                    int comp1 = dateFrom.compareTo(dateToQuery);
                    int comp2 = dateFromQuery.compareTo(dateTo);
                    if (comp1 <= 0 && comp2 <= 0){
                        Map<String, String> latLang = new HashMap<>();
                        List<String> latLangList = Arrays.asList(latLangStr.split("\\s*,\\s*"));
                        latLang.put("latitude",latLangList.get(0).trim());
                        latLang.put("longitude",latLangList.get(1).trim());
                        latLangListResponse.add(latLang);
                    }
                }
            }
        }
        catch(ParseException e){
            logger.info(e.getMessage());
        }
        circleResponseDTO.setLatLangList(latLangListResponse);
        logger.info("Response Circles: {}" ,latLangListResponse);
        return new ResponseEntity<>(circleResponseDTO, HttpStatus.OK);
    }


}
