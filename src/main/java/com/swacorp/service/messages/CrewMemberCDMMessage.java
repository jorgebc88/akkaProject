package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.crew.cdm.common.CrewMemberKeyType;
import com.swacorp.crew.cdm.crewmember.CrewMemberEvent;
import com.swacorp.service.utils.PPSConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * Created by x222905 on 8/1/2016.
 */
public class CrewMemberCDMMessage extends CrewCDMMessage {

    private final CrewMemberEvent crewMemberEvent;

    public CrewMemberCDMMessage(final String xmlMessage, final CrewMemberEvent crewMemberEvent) {
        super(xmlMessage);
        this.crewMemberEvent = Validate.notNull(crewMemberEvent, "crewMemberEvent cannot be null.");
    }

    @Override
    public CdmEventType getCdmEventType() {
        return this.crewMemberEvent;
    }

    @Override
    public String getEventKey() {
        CrewMemberKeyType crewMemberKeyType = this.crewMemberEvent.getCrewMember().getCrewMemberKey();

        if (StringUtils.isBlank(crewMemberKeyType.getEmployeeNumber())
                || StringUtils.isBlank(crewMemberKeyType.getEmployeeName())) {
            return null;
        }

        return String.format("%s|%s", crewMemberKeyType.getEmployeeNumber(), crewMemberKeyType.getEmployeeName());
    }

    @Override
    public String getEventType() {
        return PPSConstants.PARALLEL_PROD_CREW_MEMBER_PROCESSING;
    }

    @Override
    public String getEventTypeDescription() {
        return PPSConstants.CREW_MEMBER_EVENT_DESCRIPTION;
    }
}
