package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.css.util.DateTimeHelper;
import com.swacorp.service.utils.PPSConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Created by x222905 on 7/20/2016.
 */
public abstract class CrewCDMMessage extends IncomingMessage {

    protected static final String KEY_SEPARATOR = "|";
    public final String HEADER_BODY_SEPARATOR_TAG = "</EventHeader>";
    private String cdmEventTypeMessageString;

    public CrewCDMMessage(final String cdmEventTypeMessageString) {
        this.cdmEventTypeMessageString = Validate.notEmpty(cdmEventTypeMessageString,
                "The cdmEventTypeMessageString cannot be null or empty");
    }

    protected abstract CdmEventType getCdmEventType();

    @Override
    public boolean equals(final Object obj) {

        if (obj == null || !this.getClass().equals(obj.getClass())) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        CrewCDMMessage oldCrewCDMMessage = (CrewCDMMessage) obj;

        return this.areBothMessageBodiesEqual(this.cdmEventTypeMessageString, oldCrewCDMMessage.cdmEventTypeMessageString);
    }

    @Override
    public String getEventId() {
        return this.getCdmEventType().getEventHeader().getEventID();
    }

    @Override
    public abstract String getEventKey();

    @Override
    public String getEventSubType() {
        return this.getCdmEventType().getEventHeader().getEventSubType();
    }

    @Override
    public String getSequenceNumber() {
        return this.getCdmEventType().getEventHeader().getEntitySequenceNumber();
    }

    @Override
    public String getCreationTime() {
        return this.getCdmEventType().getEventHeader().getEventCreationTime();
    }

    @Override
    public long getOrder() {
        String eventCreationTime = this.getCdmEventType().getEventHeader().getEventCreationTime();
        return DateTimeHelper.getCalendarFromFormattedString(eventCreationTime,
                PPSConstants.DATE_TIME_FORMAT_EVENT_CREATION_TIME).getTimeInMillis();
    }

    @Override
    public String getMessageString() {
        return this.cdmEventTypeMessageString;
    }

    @Override
    public CdmEventType getEvent() {
        return this.getCdmEventType();
    }

    private boolean areBothMessageBodiesEqual(final String lhsMessage, final String rhsMessage) {
        String lhsBody = StringUtils.substringAfter(lhsMessage, this.HEADER_BODY_SEPARATOR_TAG);
        String rhsBody = StringUtils.substringAfter(rhsMessage, this.HEADER_BODY_SEPARATOR_TAG);

        return lhsBody.equals(rhsBody);
    }
}
