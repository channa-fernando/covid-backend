package com.covid.covid.scheduler;

import com.covid.covid.dto.LocationDetailDTO;
import com.covid.covid.entity.LocationDetail;
import com.covid.covid.repository.LocationDetailRepository;
import com.covid.covid.service.FireBaseCloudBasedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class FireBaseUpdateScheduler {
    private final Logger logger = LoggerFactory.getLogger(FireBaseUpdateScheduler.class);

    @Autowired
    private FireBaseCloudBasedService fireBaseCloudBasedService;

    @Autowired
    private LocationDetailRepository locationDetailRepository;

    public static final String TIME_ZONE = "GMT+05:30";
    public static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Scheduled(initialDelay = 10000, fixedRate = 3600000)
    public void fireBaseUpdateScheduler() {
        try {

            List<LocationDetail> unsavedLocationDetails = locationDetailRepository.unsavedLocationDetails();
            for (LocationDetail locationDetail : unsavedLocationDetails) {
                String saveTime = fireBaseCloudBasedService.saveUpdateLocationDetails(new LocationDetailDTO(locationDetail.getId(), locationDetail.getDate(), locationDetail.getFrom(), locationDetail.getTo(), locationDetail.getLatLang(), getSriLankanTime(), locationDetail.getContactTracingDetail() == null ? null : locationDetail.getContactTracingDetail().getId()));
                locationDetail.setFireBaseSavedTime(saveTime);
            }
            locationDetailRepository.saveAll(unsavedLocationDetails);

        } catch (Exception e) {
            logger.error("Exception in FireBaseUpdateScheduler ==> fireBaseUpdateScheduler: {}", e.getMessage());
        }
    }

    String getSriLankanTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(TIME_ZONE));
        return FORMAT.format(localDateTime);
    }

}
