package org.raptor.core.nodes.workers;

/**
 * Created by Anant on 11-07-2015.
 */
public abstract class AbstractReaderNode extends AbstractWorkerNode {

    public void start() {
        this.init();
        logger.info(setting.getId());
        this.read();
    }

    public abstract void read();

    public <T> void process(T message) {
        for (String receiverId : setting.getRoutes().getSend())
            vertx.eventBus().send(receiverId, message);
    }
}
