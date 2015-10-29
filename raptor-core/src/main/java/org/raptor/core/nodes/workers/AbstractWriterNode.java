package org.raptor.core.nodes.workers;

import io.vertx.core.eventbus.Message;

/**
 * Created by Anant on 11-07-2015.
 */
public abstract class AbstractWriterNode extends AbstractWorkerNode {
    public void start() {
        this.init();
        ;
        logger.info(setting.getId());
        this.read();

    }

    public void read() {
        vertx.eventBus().consumer(setting.getId(), message -> {
            this.write(message);
        });
    }

    public abstract void write(Message message);
}