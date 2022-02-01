package com.covid.covid.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@javax.persistence.Entity
public class LocationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String from;
    private String to;
    private String latLang;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false, nullable = true)
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = true)
    private LocalDateTime updateDateTime;

    @ManyToOne
    private ContactTracingDetail contactTracingDetail;

    public LocationDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLatLang() {
        return latLang;
    }

    public void setLatLang(String latLang) {
        this.latLang = latLang;
    }

    public ContactTracingDetail getContactTracingDetail() {
        return contactTracingDetail;
    }

    public void setContactTracingDetail(ContactTracingDetail contactTracingDetail) {
        this.contactTracingDetail = contactTracingDetail;
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
}
