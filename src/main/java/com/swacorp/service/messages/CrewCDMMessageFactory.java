package com.swacorp.service.messages;

import com.swacorp.crew.cdm.common.CdmEventType;
import com.swacorp.crew.cdm.crewmember.CrewMemberEvent;
import com.swacorp.crew.cdm.flight.FlightEvent;
import com.swacorp.crew.cdm.nonfly.NonflyEvent;
import com.swacorp.crew.cdm.pairing.PairingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by x222905 on 8/1/2016.
 */
public class CrewCDMMessageFactory {
    public static final String CDM_PACKAGE_PATH = "com.swacorp.crew.cdm.common";
    private static final Logger LOGGER = LoggerFactory.getLogger(CrewCDMMessage.class);
    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(CDM_PACKAGE_PATH);
        } catch (JAXBException e) {
           throw new IllegalArgumentException();
        }
    }

    public static CrewCDMMessage getCrewCDMMessage(final String cdmEventTypeMessageString) {
        CdmEventType cdmEventType = parseToCdmEventType(cdmEventTypeMessageString);
        if (cdmEventType instanceof CrewMemberEvent)
            return new CrewMemberCDMMessage(cdmEventTypeMessageString, (CrewMemberEvent) cdmEventType);
        else if (cdmEventType instanceof PairingEvent)
            return new PairingCDMMessage(cdmEventTypeMessageString, (PairingEvent) cdmEventType);
        else if (cdmEventType instanceof NonflyEvent)
            return new NonflyCDMMessage(cdmEventTypeMessageString, (NonflyEvent) cdmEventType);
        else if (cdmEventType instanceof FlightEvent)
            return new FlightCDMMessage(cdmEventTypeMessageString, (FlightEvent) cdmEventType);
            throw new IllegalArgumentException();
    }

    /**
     * This method received the xmlMessage in XML format and parse it to CdmEventType
     *
     * @param xmlMessage
     * @return cdmEventType
     */
    private static CdmEventType parseToCdmEventType(final String xmlMessage) {
        CdmEventType cdmEventType;
        try {
            InputStream inputStream = new ByteArrayInputStream(xmlMessage.getBytes());
            cdmEventType = (CdmEventType) jaxbContext.createUnmarshaller().unmarshal(inputStream);
        } catch (JAXBException e) {
            String errorMessage = "There was a problem parsing the XML: " + xmlMessage;
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage, e);
        }
        return cdmEventType;
    }
}
