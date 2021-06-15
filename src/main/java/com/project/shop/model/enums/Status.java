package com.project.shop.model.enums;

import lombok.Getter;

@Getter
public enum Status {


    AKTYWNE("AKTYWNE"),
    ZABLOKOWANE("ZABLOKOWANE");

    private final String StatusName;

    Status(String StatusName) {
        this.StatusName = StatusName;
    }

    public String getStatusName() {
        return StatusName;
    }



}
