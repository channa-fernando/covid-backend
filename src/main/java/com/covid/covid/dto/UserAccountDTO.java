package com.covid.covid.dto;

public class UserAccountDTO {
    private String fullName;
    private String email;
    private String faculty;
    private String category;
    private String passWord;
    private String address;
    private String contactNumber;
    private String contactNumberPHI;
    private String gramaSewa;
    private String policeStation;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumberPHI() {
        return contactNumberPHI;
    }

    public void setContactNumberPHI(String contactNumberPHI) {
        this.contactNumberPHI = contactNumberPHI;
    }

    public String getGramaSewa() {
        return gramaSewa;
    }

    public void setGramaSewa(String gramaSewa) {
        this.gramaSewa = gramaSewa;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", faculty='" + faculty + '\'' +
                ", category='" + category + '\'' +
                ", passWord='" + passWord + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactNumberPHI='" + contactNumberPHI + '\'' +
                ", gramaSewa='" + gramaSewa + '\'' +
                ", policeStation='" + policeStation + '\'' +
                '}';
    }
}
