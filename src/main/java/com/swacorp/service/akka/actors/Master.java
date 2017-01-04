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

    /**
     *
     * @param message
     */
    private void processMessage(final Object message) {
        if(message instanceof Worker) {
            Worker worker = (Worker) message;
            this.processWorker(worker);
        }else if(message instanceof Work){
            Work work = (Work) message;
            this.startWorkProcessing(work);
        }else this.unhandled(message);
    }

    private void processWorker(Worker worker) {
        if(!worker.isRegistered()){

        }else if(worker.requestWork()){

        }
    }

    private void startWorkProcessing(final Work work) {
        if(work.isDoneWork()){

        }else if(work.isFailedWork()){

        }else if(work.isPendingWork()){

        }
    }
}
