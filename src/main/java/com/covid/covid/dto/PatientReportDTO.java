package com.covid.covid.dto;

public class PatientReportDTO {
    private String patientId;
    private String patientName;
    private String report;
    private String basicQuestions;
    private String dateOfSubmission;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(String dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getBasicQuestions() {
        return basicQuestions;
    }

    public void setBasicQuestions(String basicQuestions) {
        this.basicQuestions = basicQuestions;
    }

    @Override
    public String toString() {
        return "PatientReportDTO{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", report='" + report + '\'' +
                ", basicQuestions='" + basicQuestions + '\'' +
                ", dateOfSubmission='" + dateOfSubmission + '\'' +
                '}';
    }
}
