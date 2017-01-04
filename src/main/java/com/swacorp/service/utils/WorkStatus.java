package com.swacorp.service.utils;

/**
 * Created by jorge.bravo on 03/01/2017.
 */
public enum WorkStatus {
    PENDING("PENDING"),
    FAILED("FAILED"),
    DONE("DONE");

    private final String workStatus;

    WorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkStatus () {
        return this.workStatus;
    }

}
