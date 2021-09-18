package com.covid.covid.dto;

import java.util.List;
import java.util.Map;

public class ReadingsDTO {
    private String quarantineCenterContact;
    private String quarantineCenter;
    private String spo2Level;
    private String bodyTemp;
    private List<Map> tracing;

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

    public List<Map> getTracing() {
        return tracing;
    }

    public void setTracing(List<Map> tracing) {
        this.tracing = tracing;
    }
}
