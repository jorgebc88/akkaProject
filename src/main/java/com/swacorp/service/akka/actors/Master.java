package com.swacorp.service.akka.actors;

import akka.actor.UntypedActor;

/**
 * Created by equi_ on 1/2/2017.
 */
public class Master extends UntypedActor {

    @Override
    public void onReceive(final Object message) throws Exception {
        this.processMessage(message);
    }

    private void processMessage(final Object message) {

    }
}
