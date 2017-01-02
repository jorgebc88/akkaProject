package com.swacorp.service.akka.actors;

import akka.camel.javaapi.UntypedConsumerActor;

public class ConsumerActor extends UntypedConsumerActor {

    @Override
    public String getEndpointUri() {
        return null;
    }

    @Override
    public void onReceive(Object o) throws Exception {

    }
}