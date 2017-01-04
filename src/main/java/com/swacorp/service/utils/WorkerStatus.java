package com.swacorp.service.utils;

/**
 * Created by equi_ on 1/2/2017.
 */
public enum WorkerStatus {
    IDLE("IDLE"),
    WAITINGFORWORK("WAITINGFORWORK"),
    WORKING("WORKING");

    private final String workerStatus;

    WorkerStatus(String workerStatus) {
        this.workerStatus = workerStatus;
    }

    public String getWorkerStatus () {
        return this.workerStatus;
    }
}
