package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.crew.cdm.common.PairingKeyType;
import com.swacorp.crew.cdm.pairing.PairingEvent;
import com.swacorp.service.utils.PPSConstants;
import org.apache.commons.lang3.Validate;

/**
 * Created by x222905 on 8/1/2016.
 */
public class PairingCDMMessage extends CrewCDMMessage {
    private final PairingEvent pairingEvent;

    public PairingCDMMessage(final String xmlMessage, final PairingEvent pairingEvent) {
        super(xmlMessage);
        this.pairingEvent = Validate.notNull(pairingEvent, "pairingEvent cannot be null.");
    }

    @Override
    public CdmEventType getCdmEventType() {
        return this.pairingEvent;
    }

    @Override
    public String getEventKey() {
        PairingKeyType pairingKeyType = this.pairingEvent.getPairing().getPairingKey();

        StringBuilder stringBuilder = new StringBuilder();

        if (pairingKeyType.getStartDate() != null) {
            stringBuilder.append(pairingKeyType.getStartDate().toString());
            stringBuilder.append(KEY_SEPARATOR);
        }

        stringBuilder.append(pairingKeyType.getPairingName());
        stringBuilder.append(KEY_SEPARATOR);
        stringBuilder.append(pairingKeyType.getPositionType());

        return stringBuilder.toString();
    }

    @Override
    public String getEventType() {
        return PPSConstants.PARALLEL_PROD_PAIRING_PROCESSING;
    }

    @Override
    public String getEventTypeDescription() {
        return PPSConstants.PAIRING_EVENT_DESCRIPTION;
    }
}
