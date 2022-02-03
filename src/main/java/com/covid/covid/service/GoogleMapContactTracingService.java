package com.covid.covid.service;

import com.covid.covid.dto.CircleResponseDTO;
import com.covid.covid.dto.ReadingsDTO;
import com.covid.covid.dto.TracingQueryDTO;
import com.covid.covid.entity.ContactTracingDetail;
import com.covid.covid.entity.LocationDetail;
import com.covid.covid.repository.ContactTracingDetailRepository;
import com.covid.covid.repository.LocationDetailRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GoogleMapContactTracingService {
    Logger logger = LoggerFactory.getLogger(GoogleMapContactTracingService.class);
    List<ReadingsDTO> readingsDTOList = new ArrayList<>();

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocationDetailRepository locationDetailRepository;

    @Autowired
    private ContactTracingDetailRepository contactTracingDetailRepository;

    public void submitReadings(ReadingsDTO readingsDTO) {
        ContactTracingDetail contactTracingDetail = modelMapper.map(readingsDTO, ContactTracingDetail.class);
        ArrayList<LocationDetail> locationDetails = new ArrayList<>();
        for (Map map : readingsDTO.getTracing()) {
            LocationDetail locationDetail = modelMapper.map(map, LocationDetail.class);
            locationDetail.setContactTracingDetail(contactTracingDetail);
            locationDetails.add(locationDetail);
        }
        String answerCommaSeperatedArray = String.join(",", readingsDTO.getAnswersArray());
        contactTracingDetail.setNatureOfContactAnswers(answerCommaSeperatedArray);
        contactTracingDetail.setLocationDetailList(locationDetails);
        contactTracingDetailRepository.save(contactTracingDetail);
        readingsDTOList.add(readingsDTO);
        logger.info(readingsDTOList.toString());
    }

    public CircleResponseDTO contactTracingFinder(TracingQueryDTO tracingQueryDTO) {
        CircleResponseDTO circleResponseDTO = new CircleResponseDTO();
        List<Map> latLangListResponse = new ArrayList<>();
        List<ContactTracingDetail> contactTracingDetailsList = contactTracingDetailRepository.findAll();
        try {
            Date dateFromQuery = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(tracingQueryDTO.getDate().trim() + " " + tracingQueryDTO.getFrom());
            Date dateToQuery = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(tracingQueryDTO.getDate().trim() + " " + tracingQueryDTO.getTo());
            for (ContactTracingDetail contactTracingDetail : contactTracingDetailsList) {
                List<LocationDetail> tracings = contactTracingDetail.getLocationDetailList();
                for (LocationDetail trace : tracings) {
                    String dateStr = trace.getDate();
                    String fromStr = trace.getFrom();
                    String toStr = trace.getTo();
                    String latLangStr = trace.getLatLang();

                    Date dateFrom = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(dateStr.trim() + " " + fromStr);
                    Date dateTo = new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(dateStr.trim() + " " + toStr);
                    int comp1 = dateFrom.compareTo(dateToQuery);
                    int comp2 = dateFromQuery.compareTo(dateTo);
                    if (comp1 <= 0 && comp2 <= 0) {
                        Map<String, String> latLang = new HashMap<>();
                        List<String> latLangList = Arrays.asList(latLangStr.split("\\s*,\\s*"));
                        latLang.put("latitude", latLangList.get(0).trim());
                        latLang.put("longitude", latLangList.get(1).trim());
                        latLangListResponse.add(latLang);
                    }
                }
            }
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        circleResponseDTO.setLatLangList(latLangListResponse);
        logger.info("Response Circles: {}", latLangListResponse);
        return circleResponseDTO;
    }
}
