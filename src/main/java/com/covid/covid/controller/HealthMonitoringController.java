package com.covid.covid.controller;

import com.covid.covid.dto.PatientReportDTO;
import com.covid.covid.service.HealthMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitoring")
public class HealthMonitoringController {
    Logger logger = LoggerFactory.getLogger(HealthMonitoringController.class);

    @Autowired
    private HealthMonitoringService healthMonitoringService;

    @GetMapping("/patientRecords/{id}")
    public ResponseEntity<PatientReportDTO> getByReportById(@PathVariable Long id) {
        PatientReportDTO patientReportDTO = healthMonitoringService.reportById(id);
        logger.info("Patient Record: {}", patientReportDTO.toString());
        if (patientReportDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(patientReportDTO, HttpStatus.OK);
        }

    }

    @GetMapping("/notificationList/{id}")
    public ResponseEntity<String> getByNotificationsById(@PathVariable Long id) {
        String notifications = healthMonitoringService.notificationsById(id);
        logger.info("Patient List: {}", notifications);
        if (notifications == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        }

    }

}
