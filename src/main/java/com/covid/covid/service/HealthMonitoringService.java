package com.covid.covid.service;

import com.covid.covid.dto.PatientReportDTO;
import com.covid.covid.entity.ContactTracingDetail;
import com.covid.covid.entity.UserAccount;
import com.covid.covid.repository.ContactTracingDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class HealthMonitoringService {
    @Autowired
    private ContactTracingDetailRepository contactTracingDetailRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ModelMapper modelMapper;

    public PatientReportDTO reportById(Long id) {
        Optional<ContactTracingDetail> contactTracingDetail = contactTracingDetailRepository.findContactTracingDetailByUserAccountId(id);
        if (contactTracingDetail.isPresent()) {
            ContactTracingDetail contactTracingDetailEntity = contactTracingDetail.get();
            PatientReportDTO patientReportDTO = new PatientReportDTO();
            patientReportDTO.setPatientId(contactTracingDetailEntity.getUserAccount().getId().toString());
            patientReportDTO.setPatientName(contactTracingDetailEntity.getUserAccount().getFullName());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            patientReportDTO.setDateOfSubmission(contactTracingDetailEntity.getCreateDateTime().format(formatter));
//            List<String> answers = Arrays.asList(contactTracingDetailEntity.getNatureOfContactAnswers().split("\\s*,\\s*"));
//            String report = "";
//            for (int i = 1; i < answers.size(); i++) {
//                report += "Question " + i + ": " + answers.get(i - 1) + " ";
//            }
            patientReportDTO.setBasicQuestions(contactTracingDetailEntity.getAreYouVaccinated() + "," + contactTracingDetailEntity.getAreYouLongTermTreat() + "," + contactTracingDetailEntity.getNameOfPositiveCase() + "," + contactTracingDetailEntity.getDaysOfContact() + "," + contactTracingDetailEntity.getDaysOfLastContact());
            patientReportDTO.setReport(contactTracingDetailEntity.getNatureOfContactAnswers());

            return patientReportDTO;
        } else {
            return null;
        }
    }

    public String notificationsById(Long id) {
        String notifications = null;
        Optional<UserAccount> userAccount = userAccountService.findById(id);
        if (userAccount.isPresent() && userAccount.get().getRole().equals("DOCTOR")) {
            notifications = "";
            List<ContactTracingDetail> contactTracingDetailList = contactTracingDetailRepository.findUseByUserRole("OTHER");
            for (ContactTracingDetail contactTracingDetail : contactTracingDetailList) {
                notifications += contactTracingDetail.getUserAccount().getId() + ":" + contactTracingDetail.getUserAccount().getFullName() + ",";
            }
        }
        return notifications;
    }
}
