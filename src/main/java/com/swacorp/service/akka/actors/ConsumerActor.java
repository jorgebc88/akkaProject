package com.swacorp.service.akka.actors;

import akka.actor.ActorRef;
import akka.camel.Ack;
import akka.camel.CamelMessage;
import akka.camel.javaapi.UntypedConsumerActor;
import akka.contrib.pattern.DistributedPubSubExtension;
import akka.contrib.pattern.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.swacorp.service.messages.CrewCDMMessageFactory;
import com.swacorp.service.messages.IncomingMessage;

public class ConsumerActor extends UntypedConsumerActor {

    private final LoggingAdapter LOGGER = Logging.getLogger(getContext().system(), this);

    /**
     * Sends our work to the master cluster singleton. 
     */
    private final ActorRef mediator = DistributedPubSubExtension.get(this.getContext().system()).mediator();

    private final String CAMEL_REDELIVERED = "CamelRedelivered";


    /**
     * For UntypedConsumerActor this is actually the first part of the Camel route definition. In
     * jmsContext.xml many other options are set on the JmsComponent.
     *
     * @return String camel route info
     */
    @Override
    public String getEndpointUri() {
        return null;
    }

    /**
     * Entrance method for the messages sent to this Actor
     * @param message
     */
    @Override
    public void onReceive(final Object message) {

        if(message instanceof CamelMessage){
            LOGGER.info("The Consumer Actor received a Camel Message. Sending back an ACK.");
            this.getSender().tell(Ack.getInstance(), this.getSelf());
            final CamelMessage camelMessage = (CamelMessage) message;
            String xmlMessage = camelMessage.body().toString();
            IncomingMessage incomingMessage = CrewCDMMessageFactory.getCrewCDMMessage(xmlMessage);
            LOGGER.info("The message was parsed correctly.");

            String eventKey = incomingMessage.getEventKey();
            String eventId = incomingMessage.getEventId();
            StringBuilder stringBuilder = new StringBuilder("[");
            stringBuilder.append(eventId).
                    append(", ").
                    append(eventKey).
                    append("] Message is a Camel Redeliver, performing the ACK.");

            Work work = new Work(this.getSelf(), incomingMessage);

            if(!this.isMessageRedelivered(camelMessage)){
                this.mediator.tell(new DistributedPubSubMediator.SendToAll("/user/master/active", work, false),
                        this.getSelf());
            }else{
                this.LOGGER.info(stringBuilder.toString());
                this.getSender().tell(Ack.getInstance(), this.getSelf());
            }
        }
    }

    /**
     * This method checks if the message received is a redelivered message.
     * @param camelMessage
     * @return True if it is a redelivered message, false if not.
     */
    private boolean isMessageRedelivered(final CamelMessage camelMessage) {
        Object camelRedelivered = camelMessage.getHeaders().get(CAMEL_REDELIVERED);
        if(camelRedelivered != null && camelRedelivered instanceof  Boolean){
            return (Boolean) camelRedelivered;
        }
        return false;
    }


}