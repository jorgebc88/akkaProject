package com.swacorp.service.messages;

import java.io.Serializable;

/**
 * Created by x222905 on 7/20/2016.
 */
public abstract class IncomingMessage implements Serializable{

    private static final long serialVersionUID = -6231940485633414296L;

    public abstract String getEventId();

    public abstract String getEventKey();

    public abstract long getOrder();

    public abstract <T> T getEvent();

    public abstract String getMessageString();

    public abstract String getEventType();

    public abstract String getEventTypeDescription();

    public abstract String getEventSubType();

    public abstract String getSequenceNumber();

    public abstract String getCreationTime();
}
