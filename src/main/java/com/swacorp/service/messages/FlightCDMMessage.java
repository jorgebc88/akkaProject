package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.crew.cdm.common.FlightKeyType;
import com.swacorp.crew.cdm.flight.FlightEvent;
import com.swacorp.service.utils.PPSConstants;
import org.apache.commons.lang3.Validate;

/**
 * Created by x221032 on 8/12/2016.
 */
public class FlightCDMMessage extends CrewCDMMessage{
    private final FlightEvent flightEvent;

    public FlightCDMMessage(final String xmlMessage, final FlightEvent flightEvent) {
        super(xmlMessage);
        this.flightEvent = Validate.notNull(flightEvent, "flightEvent cannot be null.");
    }

    @Override
    public CdmEventType getCdmEventType() {
        return this.flightEvent;
    }

    @Override
    public String getEventKey() {
        FlightKeyType pairingKeyType = this.flightEvent.getFlight().getFlightKey();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(pairingKeyType.getDepartureDate().toString());
        stringBuilder.append(KEY_SEPARATOR);

        stringBuilder.append(pairingKeyType.getDepartureStation().getIATACode());
        stringBuilder.append(KEY_SEPARATOR);

        stringBuilder.append(pairingKeyType.getFlightNumber());
        stringBuilder.append(KEY_SEPARATOR);
        stringBuilder.append(pairingKeyType.getSuffix());

        return stringBuilder.toString();
    }

    @Override
    public String getEventType() {
        return PPSConstants.PARALLEL_PROD_FLIGHT_PROCESSING;
    }

    @Override
    public String getEventTypeDescription() {
        return PPSConstants.FLIGHT_EVENT_DESCRIPTION;
    }
}
