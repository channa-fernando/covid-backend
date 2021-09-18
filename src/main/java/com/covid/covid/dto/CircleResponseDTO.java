package com.covid.covid.dto;

import java.util.List;
import java.util.Map;

public class CircleResponseDTO {
    List<Map> latLangList;

    public List<Map> getLatLangList() {
        return latLangList;
    }

    public void setLatLangList(List<Map> latLangList) {
        this.latLangList = latLangList;
    }
}
