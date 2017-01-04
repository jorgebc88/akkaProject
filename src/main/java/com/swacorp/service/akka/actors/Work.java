package com.swacorp.service.akka.actors;

import akka.actor.ActorRef;
import com.swacorp.service.messages.IncomingMessage;
import com.swacorp.service.utils.WorkStatus;

/**
 * Created by jorge.bravo on 03/01/2017.
 */
public class Work {

    private final ActorRef originalSender;
    private IncomingMessage incomingMessage;
    private String status = WorkStatus.PENDING.getWorkStatus();
    private String workerId;
    private Object result = null;

    public Work(final ActorRef originalSender, final IncomingMessage incomingMessage){
        this.originalSender = originalSender;
        this.incomingMessage = incomingMessage;
    }

    public String getEventId(){
        return this.incomingMessage.getEventId();
    }

    public long getOrder(){
        return this.incomingMessage.getOrder();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public boolean isPendingWork(){
        return status.equals(WorkStatus.PENDING.getWorkStatus());
    }

    public boolean isDoneWork(){
        return status.equals(WorkStatus.PENDING.getWorkStatus());
    }

    public boolean isFailedWork(){
        return status.equals(WorkStatus.PENDING.getWorkStatus());
    }
}
