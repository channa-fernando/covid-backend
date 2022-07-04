package com.covid.covid.dto;

public class LocationDetailDTO {
    private Long id;
    private String date;
    private String from;
    private String to;
    private String latLang;
    private String createTime;
    private Long contactTracingDetailId;

    public LocationDetailDTO(Long id, String date, String from, String to, String latLang, String createTime, Long contactTracingDetailId) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.latLang = latLang;
        this.createTime = createTime;
        this.contactTracingDetailId = contactTracingDetailId;
    }

    public LocationDetailDTO() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getContactTracingDetailId() {
        return contactTracingDetailId;
    }

    public void setContactTracingDetailId(Long contactTracingDetailId) {
        this.contactTracingDetailId = contactTracingDetailId;
    }
}
