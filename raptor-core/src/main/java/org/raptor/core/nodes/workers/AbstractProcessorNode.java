package org.raptor.core.nodes.workers;

import io.vertx.core.eventbus.Message;

/**
 * Created by Anant on 11-07-2015.
 */
public abstract class AbstractProcessorNode extends AbstractWorkerNode {
    public void start() {
        this.init();

        // log start
        logger.info(setting.getId());

        this.read();
    }

    public void read() {
        vertx.eventBus().consumer(setting.getId(), this::process);
    }

    public abstract void process(Message message);

    public <T> void write(T message) {
        for (String receiverId : setting.getRoutes().getSend())
            vertx.eventBus().send(receiverId, message);
    }
}