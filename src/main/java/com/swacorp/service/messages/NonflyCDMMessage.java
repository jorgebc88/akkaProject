package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.crew.cdm.common.CrewMemberKeyType;
import com.swacorp.crew.cdm.common.NonflyKeyType;
import com.swacorp.crew.cdm.nonfly.NonflyEvent;
import com.swacorp.service.utils.PPSConstants;
import org.apache.commons.lang3.Validate;

/**
 * Created by x222905 on 8/1/2016.
 */
public class NonflyCDMMessage extends CrewCDMMessage {

    private final NonflyEvent nonflyEvent;

    public NonflyCDMMessage(final String xmlMessage, final NonflyEvent nonflyEvent) {
        super(xmlMessage);
        this.nonflyEvent = Validate.notNull(nonflyEvent, "nonflyEvent cannot be null.");
    }

    @Override
    public CdmEventType getCdmEventType() {
        return this.nonflyEvent;
    }

    @Override
    public String getEventKey() {
        NonflyKeyType nonflyKeyType = this.nonflyEvent.getNonfly().getNonflyKey();
        CrewMemberKeyType crewMemberKey = Validate.notNull(nonflyKeyType.getCrewMemberKey(),
                "The Crew Member Key cannot be null");

        final StringBuilder stringBuilder = new StringBuilder(String.valueOf(nonflyKeyType.getNonflyNumber()))
                .append(KEY_SEPARATOR)
                .append(nonflyKeyType.getNonflyCode())
                .append(KEY_SEPARATOR)
                .append(crewMemberKey.getEmployeeNumber())
                .append(KEY_SEPARATOR)
                .append(crewMemberKey.getEmployeeName());

        return stringBuilder.toString();
    }

    @Override
    public String getEventType() {
        return PPSConstants.PARALLEL_PROD_NONFLY_PROCESSING;
    }

    @Override
    public String getEventTypeDescription() {
        return PPSConstants.NONFLY_EVENT_DESCRIPTION;
    }
}
