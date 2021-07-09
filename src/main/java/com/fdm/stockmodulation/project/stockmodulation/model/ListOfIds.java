package com.fdm.stockmodulation.project.stockmodulation.model;

import java.util.List;

public class ListOfIds {

    private List<Long> longList;

    public ListOfIds() {
    }

    public ListOfIds(List<Long> longList) {
        this.longList = longList;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public void setLongList(List<Long> longList) {
        this.longList = longList;
    }
}
