package com.covid.covid.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@javax.persistence.Entity
public class ContactTracingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quarantineCenterContact;
    private String quarantineCenter;
    private String spo2Level;
    private String bodyTemp;
    private String nameOfPositiveCase;
    private String areYouVaccinated;
    private String areYouLongTermTreat;
    private Integer daysOfContact;
    private Integer daysOfLastContact;
    private String natureOfContactAnswers;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = true)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = true)
    private LocalDateTime updateDateTime;

    @OneToMany(mappedBy = "contactTracingDetail", cascade = CascadeType.ALL)
    private List<LocationDetail> locationDetailList;

    @ManyToOne
    private UserAccount userAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuarantineCenterContact() {
        return quarantineCenterContact;
    }

    public void setQuarantineCenterContact(String quarantineCenterContact) {
        this.quarantineCenterContact = quarantineCenterContact;
    }

    public String getQuarantineCenter() {
        return quarantineCenter;
    }

    public void setQuarantineCenter(String quarantineCenter) {
        this.quarantineCenter = quarantineCenter;
    }

    public String getSpo2Level() {
        return spo2Level;
    }

    public void setSpo2Level(String spo2Level) {
        this.spo2Level = spo2Level;
    }

    public String getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(String bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public List<LocationDetail> getLocationDetailList() {
        return locationDetailList;
    }

    public void setLocationDetailList(List<LocationDetail> locationDetailList) {
        this.locationDetailList = locationDetailList;
    }

    public String getNameOfPositiveCase() {
        return nameOfPositiveCase;
    }

    public void setNameOfPositiveCase(String nameOfPositiveCase) {
        this.nameOfPositiveCase = nameOfPositiveCase;
    }

    public String getAreYouVaccinated() {
        return areYouVaccinated;
    }

    public void setAreYouVaccinated(String areYouVaccinated) {
        this.areYouVaccinated = areYouVaccinated;
    }

    public String getAreYouLongTermTreat() {
        return areYouLongTermTreat;
    }

    public void setAreYouLongTermTreat(String areYouLongTermTreat) {
        this.areYouLongTermTreat = areYouLongTermTreat;
    }

    public Integer getDaysOfContact() {
        return daysOfContact;
    }

    public void setDaysOfContact(Integer daysOfContact) {
        this.daysOfContact = daysOfContact;
    }

    public Integer getDaysOfLastContact() {
        return daysOfLastContact;
    }

    public void setDaysOfLastContact(Integer daysOfLastContact) {
        this.daysOfLastContact = daysOfLastContact;
    }

    public String getNatureOfContactAnswers() {
        return natureOfContactAnswers;
    }

    public void setNatureOfContactAnswers(String natureOfContactAnswers) {
        this.natureOfContactAnswers = natureOfContactAnswers;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
