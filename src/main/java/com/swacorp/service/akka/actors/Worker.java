package com.swacorp.service.akka.actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.swacorp.service.utils.WorkerStatus;
import scala.concurrent.duration.FiniteDuration;

/**
 * Created by equi_ on 1/2/2017.
 */
public class Worker extends UntypedActor {

    private String status = WorkerStatus.IDLE.getWorkerStatus();
    private boolean isRegistered = false;
    private boolean isRequestingWork = false;

    public Worker(final ActorRef mediator, final Props workExecutorProps,
                  final FiniteDuration registerInterval){

    }

    @Override
    public void onReceive(final Object message) throws Exception {
        this.processMessage(message);
    }

    private void processMessage(final Object message) {
        if(this.isWorkerIdle()){
            this.processMessageWhileWorkerIsIdle(message);
        }else if(this.isWorkerWaitingForWork()){
            this.processMessageWhileWorkerIsWaitingForWork(message);
        }else if(this.isWorkerWorking()){
            this.processMessageWhileWorkerIsWorking(message);
        }
    }

    private void processMessageWhileWorkerIsWorking(final Object message) {
    }

    private void processMessageWhileWorkerIsWaitingForWork(final Object message) {
    }

    private void processMessageWhileWorkerIsIdle(final Object message) {
    }


    public boolean isWorkerIdle() {
        return status.equals(WorkerStatus.IDLE.getWorkerStatus());
    }

    public boolean isWorkerWaitingForWork () {
        return status.equals(WorkerStatus.WAITINGFORWORK.getWorkerStatus());
    }

    public boolean isWorkerWorking () {
        return status.equals(WorkerStatus.WORKING.getWorkerStatus());
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }

    public boolean requestWork() {
        return this.isRequestingWork;
    }
}
