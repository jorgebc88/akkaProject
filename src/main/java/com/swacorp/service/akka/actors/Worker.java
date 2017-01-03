package com.swacorp.service.akka.actors;

import akka.actor.UntypedActor;
import com.swacorp.service.utils.WorkerStatus;

/**
 * Created by equi_ on 1/2/2017.
 */
public class Worker extends UntypedActor {

    private String status = WorkerStatus.IDLE.getWorkerStatus();

    @Override
    public void onReceive(final Object message) throws Exception {
        this.processMessage(message);
    }

    private void processMessage(Object message) {
    }
}
